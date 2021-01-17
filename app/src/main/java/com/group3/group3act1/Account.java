package com.group3.group3act1;

public class Account {

    private String username;
    private String password;
    private String name;

    public Account(String username, String password, String name) {
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

