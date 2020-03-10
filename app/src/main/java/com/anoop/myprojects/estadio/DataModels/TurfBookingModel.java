package com.anoop.myprojects.estadio.DataModels;

public class TurfBookingModel {

    int id, user_id, turf_id;
    String date;
    String time_from,time_to;
    int owner_id;

    public TurfBookingModel() {
    }

    public TurfBookingModel(int id, int user_id, int turf_id, String date, String time_from, String time_to) {
        this.id = id;
        this.user_id = user_id;
        this.turf_id = turf_id;
        this.date = date;
        this.time_from = time_from;
        this.time_to = time_to;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTurf_id() {
        return turf_id;
    }

    public void setTurf_id(int turf_id) {
        this.turf_id = turf_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_from() {
        return time_from;
    }

    public void setTime_from(String time_from) {
        this.time_from = time_from;
    }

    public String getTime_to() {
        return time_to;
    }

    public void setTime_to(String time_to) {
        this.time_to = time_to;
    }

    public String getTime()
    {
        String ret = time_from + " to " + time_to;
        return ret;
    }

    @Override
    public String toString() {
        return "TurfBookings{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", turf_id=" + turf_id +
                ", date='" + date + '\'' +
                ", time_from='" + time_from + '\'' +
                ", time_to='" + time_to + '\'' +
                '}';
    }
}
