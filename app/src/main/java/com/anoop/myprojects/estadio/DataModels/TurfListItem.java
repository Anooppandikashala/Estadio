package com.anoop.myprojects.estadio.DataModels;

import android.widget.ImageView;

public class TurfListItem {

    int id;
    int tableId;
    String name,phone;
    int view,delete;

    public TurfListItem(int id,int tableId, String name, String phone, int view, int delete) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.view = view;
        this.delete = delete;
        this.tableId = tableId;
    }

    public TurfListItem()
    {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
}
