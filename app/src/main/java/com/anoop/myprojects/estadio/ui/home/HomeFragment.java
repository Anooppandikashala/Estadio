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
import com.anoop.myprojects.estadio.DataModels.TurfModel;
import com.anoop.myprojects.estadio.DatabaseHelper;
import com.anoop.myprojects.estadio.R;
import com.anoop.myprojects.estadio.TurfBookings;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    //public static View.OnClickListener myOnClickListener;
    private HomeViewModel homeViewModel;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<TurfModel> data;
    public static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;

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
        return root;
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
}