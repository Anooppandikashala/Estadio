package com.anoop.myprojects.estadio;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anoop.myprojects.estadio.DataModels.TurfBookingModel;
import com.anoop.myprojects.estadio.DataModels.TurfModel;
import com.anoop.myprojects.estadio.DataModels.UserModel;
import com.anoop.myprojects.estadio.session_manager.Session;
import com.anoop.myprojects.estadio.ui.home.HomeFragment;

import java.util.ArrayList;

public class CustomAdapterBooking extends RecyclerView.Adapter<CustomAdapterBooking.MyViewHolder> {
    private ArrayList<TurfBookingModel> dataSet;
    private Context context;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUser;
        TextView textViewName;
        TextView textViewDate;
        TextView textViewTime;
        TextView textViewPhone;
        Button approve;
        TextView id;
        //ImageView imageViewIcon;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewUser = (TextView) itemView.findViewById(R.id.textViewUser);
            this.textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
            this.textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
            this.textViewPhone = (TextView) itemView.findViewById(R.id.textViewPhone);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.approve = itemView.findViewById(R.id.approve);
            this.id = itemView.findViewById(R.id.turf_id);
            //this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
    public CustomAdapterBooking(ArrayList<TurfBookingModel> data, Context context) {
        this.dataSet = data;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_turfs_approve, parent, false);

        //view.setOnClickListener(HomeFragment.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewUser = holder.textViewUser;
        TextView textViewDate = holder.textViewDate;
        TextView textViewTime = holder.textViewTime;
        TextView textViewPhone = holder.textViewPhone;
        TextView textViewName = holder.textViewName;
        Button approve = holder.approve;
        TextView id = holder.id;

        int userId = dataSet.get(listPosition).getUser_id();

        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        Session session = new Session(context);

        String isOwner = session.getIsOwner();

        boolean isowner = isOwner.equals("player") ? false : true;

        UserModel userModel = databaseHelper.getUser(userId,isowner);

        TurfModel turfModel = databaseHelper.getTurf(dataSet.get(listPosition).getTurf_id());

        textViewUser.setText(userModel.getName());
        textViewDate.setText(dataSet.get(listPosition).getDate());
        textViewTime.setText(dataSet.get(listPosition).getTime());
        textViewPhone.setText(userModel.getPhone());
        textViewName.setText(turfModel.getName());

        int approve_status = dataSet.get(listPosition).getApprove();
        if(approve_status == 0 )
        {
            approve.setText("Approve");
            approve.setTextColor(Color.RED);
        }
        else
        {
            approve.setText("Approved");
            approve.setTextColor(Color.GREEN);

        }

        id.setText(String.valueOf(dataSet.get(listPosition).getId()));

        approve.setOnClickListener(HomeFragment.myOnClickListenerApprove);
        //imageView.setImageResource(dataSet.get(listPosition).getImage());
    }
    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}