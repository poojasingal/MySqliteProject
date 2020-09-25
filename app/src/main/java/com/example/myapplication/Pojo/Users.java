package com.example.myapplication.Pojo;

public class Users {

    String name, loc, desg;

    public Users(String name, String loc, String desg){
        this.name = name;
        this.loc = loc;
        this.desg = desg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getDesg() {
        return desg;
    }

    public void setDesg(String desg) {
        this.desg = desg;
    }

}
