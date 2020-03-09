package com.anoop.myprojects.estadio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anoop.myprojects.estadio.DataModels.TurfListItem;
import com.anoop.myprojects.estadio.DataModels.TurfModel;
import com.anoop.myprojects.estadio.ui.home.HomeFragment;
import com.anoop.myprojects.estadio.ui.tools.ToolsFragment;

import java.util.ArrayList;

public class CustomAdapterTurfList extends RecyclerView.Adapter<CustomAdapterTurfList.MyViewHolder> {
    private ArrayList<TurfListItem> dataSet;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView phone;
        ImageView view,delete;
        //ImageView imageViewIcon;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.id = (TextView) itemView.findViewById(R.id.id);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.phone = itemView.findViewById(R.id.mob);
            this.view = itemView.findViewById(R.id.view);
            this.delete = itemView.findViewById(R.id.delete);
            //this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
    public CustomAdapterTurfList(ArrayList<TurfListItem> data) {
        this.dataSet = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.turf_list, parent, false);

        //view.setOnClickListener(HomeFragment.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textId = holder.id;
        TextView name = holder.name;
        TextView phone = holder.phone;
        ImageView view = holder.view;
        ImageView delete = holder.delete;
        //ImageView imageView = holder.imageViewIcon;
        textId.setText(String.valueOf(dataSet.get(listPosition).getTableId()));
        name.setText(dataSet.get(listPosition).getName());
        phone.setText(dataSet.get(listPosition).getPhone());
        view.setImageResource(dataSet.get(listPosition).getView());
        view.setOnClickListener(ToolsFragment.viewOnClickListener);

        delete.setImageResource(dataSet.get(listPosition).getDelete());
        delete.setOnClickListener(ToolsFragment.deleteOnClickListener);
        //imageView.setImageResource(dataSet.get(listPosition).getImage());
    }
    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}