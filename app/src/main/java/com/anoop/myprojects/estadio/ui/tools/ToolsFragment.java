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

    ImageView addTurf;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
//        final TextView textView = root.findViewById(R.id.text_tools);
//        toolsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });



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

        data = databaseHelper.getAllTurfsList();
//        data.add(new TurfModel(
//                "Turf",
//                "dhjvskdjhgdshdsvd dsvkjdsvdhds",
//                1,
//                123
//        ));

        removedItems = new ArrayList<Integer>();
        adapter = new CustomAdapterTurfList(data);
        recyclerView.setAdapter(adapter);
        return root;
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
            //
        }
    }
}