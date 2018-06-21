package com.example.akarshsingh.touritinerary;

public class Search {

    String roname;
    String roid;
    String roGrade;
    Search(String roid, String roname, String rograde)
    {
        this.roid = roid;
        this.roname = roname;
        this.roGrade = rograde;

    }

    public String getRoGrade() {
        return roGrade;
    }

    public String getRoid() {
        return roid;
    }

    public String getRoname() {
        return roname;
    }
}
