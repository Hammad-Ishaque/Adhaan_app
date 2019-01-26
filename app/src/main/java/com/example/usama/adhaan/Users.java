package com.example.usama.adhaan;

public class Users {
    String id;
    String username;
    String password;
    String email;
    String city;

    public Users() {


    }

    public Users(String id, String username, String password, String email, String city) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.city = city;
    }

    public Users(String username, String password, String email, String city) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.city = city;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
