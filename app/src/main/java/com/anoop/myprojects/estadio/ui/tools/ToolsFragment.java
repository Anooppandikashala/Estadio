package com.anoop.myprojects.estadio.ui.tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anoop.myprojects.estadio.AddTurf;
import com.anoop.myprojects.estadio.CustomAdapter;
import com.anoop.myprojects.estadio.CustomAdapterTurfList;
import com.anoop.myprojects.estadio.DataModels.TurfListItem;
import com.anoop.myprojects.estadio.DataModels.TurfModel;
import com.anoop.myprojects.estadio.DatabaseHelper;
import com.anoop.myprojects.estadio.R;
import com.anoop.myprojects.estadio.session_manager.Session;
import com.anoop.myprojects.estadio.ui.home.HomeFragment;

import java.util.ArrayList;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<TurfListItem> data;
    public static View.OnClickListener viewOnClickListener,deleteOnClickListener;
    private static ArrayList<Integer> removedItems;

//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    ImageView addTurf;

    View root_;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        root_ = inflater.inflate(R.layout.fragment_tools, container, false);
//        final TextView textView = root.findViewById(R.id.text_tools);
//        toolsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        initFragment(root_);

        return root_;
    }

//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    public void onStart() {

        initFragment(root_);
        super.onStart();
    }

    @Override
    public void onResume() {
        initFragment(root_);
        super.onResume();
    }

    public  void initFragment(View root)
    {
        addTurf = root.findViewById(R.id.editTurfs);

        Session session = new Session(getContext());

        String isOwner = session.getIsOwner();

//        System.out.println(isOwner);

        if(isOwner.equals("player"))
        {
            addTurf.setVisibility(ImageView.GONE);
        }
        else
        {
            addTurf.setVisibility(ImageView.VISIBLE);
        }

        addTurf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTurf.class);
                getActivity().startActivity(intent);
            }
        });


        viewOnClickListener = new ToolsFragment.ViewOnClickListener(getContext());
        deleteOnClickListener = new ToolsFragment.DeleteOnClickListener(getContext());

        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<TurfListItem>();

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());



        data = databaseHelper.getAllTurfsList(Integer.parseInt(session.getId()));

//        System.out.println(data.get(0).toString());
//        data.add(new TurfModel(
//                "Turf",
//                "dhjvskdjhgdshdsvd dsvkjdsvdhds",
//                1,
//                123
//        ));

        removedItems = new ArrayList<Integer>();
        adapter = new CustomAdapterTurfList(data);
        recyclerView.setAdapter(adapter);
    }

    private static class ViewOnClickListener implements View.OnClickListener {
        private final Context context;
        private ViewOnClickListener(Context context) {
            this.context = context;
        }
        @Override
        public void onClick(View v) {
            String selectedId="Hello";

            ViewGroup parentView = (ViewGroup)v.getParent();
            TextView disciplinaNome =(TextView)parentView.findViewById(R.id.id);
            selectedId = disciplinaNome.getText().toString();
            Toast.makeText(context,selectedId,Toast.LENGTH_SHORT).show();
            //
        }
    }

    private static class DeleteOnClickListener implements View.OnClickListener {
        private final Context context;
        private DeleteOnClickListener(Context context) {
            this.context = context;
        }
        @Override
        public void onClick(View v) {
            String selectedId="Hello";

            ViewGroup parentView = (ViewGroup)v.getParent();
            TextView disciplinaNome =(TextView)parentView.findViewById(R.id.id);
            selectedId = disciplinaNome.getText().toString();
            Toast.makeText(context,selectedId,Toast.LENGTH_SHORT).show();
            DatabaseHelper databaseHelper = new DatabaseHelper(context);

            databaseHelper.deleteTurf(Integer.parseInt(selectedId));

            int selectedItemId = -1;
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getId()  == Integer.parseInt(selectedId)) {
                    selectedItemId = i;
                    break;
                }
            }
            if(selectedItemId != -1)
            {
                Session session = new Session(context);
                data = databaseHelper.getAllTurfsList(Integer.parseInt(session.getId()));
                adapter = new CustomAdapterTurfList(data);
                recyclerView.setAdapter(adapter);



            }

            //removeItem(v);
            //
        }
    }

    private static void removeItem(View v) {

        int selectedItemPosition = recyclerView.getChildLayoutPosition(v);


//        RecyclerView.ViewHolder viewHolder
//                = recyclerView.findViewHolderForLayoutPosition(selectedItemPosition);
//        TextView textViewName
//                = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
//        String selectedName = (String) textViewName.getText();
//        int selectedItemId = -1;
//        for (int i = 0; i < MyData.nameArray.length; i++) {
//            if (selectedName.equals(MyData.nameArray[i])) {
//                selectedItemId = MyData.id_[i];
//            }
//        }
//        removedItems.add(selectedItemId);

    }
}