package com.anoop.myprojects.estadio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.anoop.myprojects.estadio.DataModels.TurfModel;
import com.anoop.myprojects.estadio.ui.home.HomeFragment;

import java.util.ArrayList;
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<TurfModel> dataSet;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewVersion;
        Button bookNow;
        //ImageView imageViewIcon;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.bookNow = itemView.findViewById(R.id.booknow);
            //this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
    public CustomAdapter(ArrayList<TurfModel> data) {
        this.dataSet = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_turfs, parent, false);

        //view.setOnClickListener(HomeFragment.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        //ImageView imageView = holder.imageViewIcon;
        textViewName.setText(dataSet.get(listPosition).getName());
        textViewVersion.setText(dataSet.get(listPosition).getVersion());
        Button bookNow = holder.bookNow;
        bookNow.setOnClickListener(HomeFragment.myOnClickListener);
        //imageView.setImageResource(dataSet.get(listPosition).getImage());
    }
    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}