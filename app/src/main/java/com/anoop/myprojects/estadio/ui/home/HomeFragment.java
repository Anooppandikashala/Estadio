package com.anoop.myprojects.estadio.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anoop.myprojects.estadio.CustomAdapter;
import com.anoop.myprojects.estadio.CustomAdapterBooking;
import com.anoop.myprojects.estadio.DataModels.TurfBookingModel;
import com.anoop.myprojects.estadio.DataModels.TurfModel;
import com.anoop.myprojects.estadio.DatabaseHelper;
import com.anoop.myprojects.estadio.R;
import com.anoop.myprojects.estadio.TurfBookings;
import com.anoop.myprojects.estadio.session_manager.Session;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    //public static View.OnClickListener myOnClickListener;
    private HomeViewModel homeViewModel;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<TurfModel> data;
    private static ArrayList<TurfBookingModel> data_booking;
    public static View.OnClickListener myOnClickListener;
    public static View.OnClickListener myOnClickListenerApprove;
    private static ArrayList<Integer> removedItems;

    int ID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        Session session = new Session(getContext());

        ID = Integer.parseInt(session.getId());

        System.out.println(session.getId());
        if(session.getIsOwner().equals("player"))
        {
            ifPlayer(root);
        }
        else
        {
            ifOwner(root);
        }

        return root;
    }

    void ifPlayer(View root)
    {
        myOnClickListener = new MyOnClickListener(getContext());
        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<TurfModel>();

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        data = databaseHelper.getAllTurfs();
//        data.add(new TurfModel(
//                "Turf",
//                "dhjvskdjhgdshdsvd dsvkjdsvdhds",
//                1,
//                123
//        ));

        removedItems = new ArrayList<Integer>();
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    void ifOwner(View root)
    {

        System.out.println("Hi");
        myOnClickListenerApprove = new MyOnClickListenerApprove(getContext());
        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data_booking = new ArrayList<TurfBookingModel>();

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        data_booking = databaseHelper.getAllTurfBookingsForOwner(ID);

        data_booking = databaseHelper.getTurfBooking(5);

        System.out.println("Owner id :"+data_booking.get(0).getOwner_id());
//        data.add(new TurfModel(
//                "Turf",
//                "dhjvskdjhgdshdsvd dsvkjdsvdhds",
//                1,
//                123
//        ));

        //System.out.println(data_booking.get(0).toString());

        removedItems = new ArrayList<Integer>();
        adapter = new CustomAdapterBooking(data_booking,getContext());
        recyclerView.setAdapter(adapter);
    }

    private static class MyOnClickListener implements View.OnClickListener {
        private final Context context;
        private MyOnClickListener(Context context) {
            this.context = context;
        }
        @Override
        public void onClick(View v) {
            String selectedName="Hello";

            ViewGroup parentView = (ViewGroup)v.getParent();
            TextView disciplinaNome =(TextView)parentView.findViewById(R.id.textViewName);
            TextView id_ = parentView.findViewById(R.id.turf_id);

            selectedName = disciplinaNome.getText().toString();
            Toast.makeText(context,selectedName,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, TurfBookings.class);

            intent.putExtra("ID",id_.getText().toString());

            System.out.println(id_.getText());

            intent.putExtra("NAME",selectedName);

            context.startActivity(intent);
            //
        }
//        private void removeItem(View v) {
//            int selectedItemPosition = recyclerView.getChildPosition(v);
//            RecyclerView.ViewHolder viewHolder
//                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
//            TextView textViewName
//                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
//            String selectedName = (String) textViewName.getText();
//            int selectedItemId = -1;
//            for (int i = 0; i < MyData.nameArray.length; i++) {
//                if (selectedName.equals(MyData.nameArray[i])) {
//                    selectedItemId = MyData.id_[i];
//                }
//            }
//            removedItems.add(selectedItemId);
//            data.remove(selectedItemPosition);
//            adapter.notifyItemRemoved(selectedItemPosition);
//        }
    }


    private static class MyOnClickListenerApprove implements View.OnClickListener {
        private final Context context;

        private MyOnClickListenerApprove(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            String selectedName = "Hello";

            ViewGroup parentView = (ViewGroup) v.getParent();
            TextView disciplinaNome = (TextView) parentView.findViewById(R.id.textViewName);
            TextView id_ = parentView.findViewById(R.id.turf_id);

            selectedName = disciplinaNome.getText().toString();
            Toast.makeText(context, selectedName, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, TurfBookings.class);

            intent.putExtra("ID", id_.getText().toString());

            System.out.println(id_.getText());

            intent.putExtra("NAME", selectedName);

            context.startActivity(intent);
            //
        }
    }
}