package com.anoop.myprojects.estadio.DataModels;

public class UserModel {

    String name;
    int id;
    String address;
    String username;
    String password;
    String email;
    String phone;

    public UserModel()
    {}

    public UserModel(String name, int id, String address, String username, String password, String email, String phone) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
