package com.bean;

public class Tuser {
    private String name;
    private String password;

    public Tuser() {
    }

    public Tuser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Tuser{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
