package com.example.myapplication.Pojo;

public class Contact {

    int id ;


    String name;
    String phoneno;

    public Contact(){

    }

    public Contact(int id, String name, String phoneno){
        this.id = id;
        this.name = name;
        this.phoneno = phoneno;
    }

    public Contact(String name, String phoneno){
        this.name = name;
        this.phoneno = phoneno;
    }

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

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }


}
