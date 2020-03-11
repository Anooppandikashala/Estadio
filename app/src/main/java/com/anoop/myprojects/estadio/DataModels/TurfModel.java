package com.anoop.myprojects.estadio.DataModels;

public class TurfModel {
    String name;
    String version;
    int id_;
    int image;
    int owner_id;

    public TurfModel()
    {}

    public TurfModel(String name, String version, int id_, int image, int owner_id) {
        this.name = name;
        this.version = version;
        this.id_ = id_;
        this.image=image;
        this.owner_id = owner_id;
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

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "TurfModel{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", id_=" + id_ +
                ", image=" + image +
                ", owner_id=" + owner_id +
                '}';
    }
}