package com.example.project_studentmanagement_android.Model;

public class User {
    String Id;
    String username;
    String password;
    String Role;

    public User() {

    }



    public User(String id) {
        Id = id;
    }

    public User(String id, String username, String password, String role) {
        Id = id;
        this.username = username;
        this.password = password;
        Role = role;
    }

    public String getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return Role;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        Role = role;
    }
}
