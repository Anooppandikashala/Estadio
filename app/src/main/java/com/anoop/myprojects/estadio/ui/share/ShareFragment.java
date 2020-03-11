package com.anoop.myprojects.estadio.ui.share;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anoop.myprojects.estadio.CustomAdapter;
import com.anoop.myprojects.estadio.CustomAdapterBookingList;
import com.anoop.myprojects.estadio.DataModels.TurfBookingModel;
import com.anoop.myprojects.estadio.DataModels.TurfModel;
import com.anoop.myprojects.estadio.DatabaseHelper;
import com.anoop.myprojects.estadio.R;
import com.anoop.myprojects.estadio.session_manager.Session;
import com.anoop.myprojects.estadio.ui.home.HomeFragment;

import java.util.ArrayList;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<TurfBookingModel> data_booking;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
//        final TextView textView = root.findViewById(R.id.text_share);
//        shareViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        ifPlayer(root);

        return root;
    }

    void ifPlayer(View root)
    {

        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data_booking = new ArrayList<TurfBookingModel>();

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        Session session = new Session(getContext());

        int id = Integer.parseInt(session.getId());
        data_booking = databaseHelper.getAllTurfBookingsForUser(id);

        if(data_booking.size() > 0)
        {
            System.out.println("Owner id :"+data_booking.get(0).toString());

        }
        adapter = new CustomAdapterBookingList(data_booking,getContext());
        recyclerView.setAdapter(adapter);
    }
}