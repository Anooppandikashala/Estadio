package com.anoop.myprojects.estadio;

public class DataModel {
    String name;
    String version;
    int id_;
    int image;

    public DataModel()
    {}

    public DataModel(String name, String version, int id_, int image) {
        this.name = name;
        this.version = version;
        this.id_ = id_;
        this.image=image;
    }
    public String getName() {
        return name;
    }
    public String getVersion() {
        return version;
    }
    public int getImage() {
        return image;
    }
    public int getId() {
        return id_;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public void setImage(int image) {
        this.image = image;
    }
}