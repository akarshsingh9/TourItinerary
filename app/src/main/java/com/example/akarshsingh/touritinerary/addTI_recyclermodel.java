package com.example.akarshsingh.touritinerary;

public class addTI_recyclermodel {

    String from_place;
    String to_place;
    String ti_number;
    int travelmode_img;
    //String date_dept;
    //String time_dept;

    addTI_recyclermodel(String ti_number, String from_place, String to_place, int travelmode_img/*, String date_dept, String time_dept*/)
    {
        this.ti_number = ti_number;
        this.from_place = from_place;
        this.to_place = to_place;
        this.travelmode_img = travelmode_img;
        //this.date_dept = date_dept;
        //this.time_dept = time_dept;
    }
/*
    public String getDate_dept() {
        return date_dept;
    }
*/
    public String getFrom_place() {
        return from_place;
    }

    public String getTi_number() {
        return ti_number;
    }
/*
    public String getTime_dept() {
        return time_dept;
    }
*/
    public String getTo_place() {
        return to_place;
    }

    public int getTravelmode_img() {
        return travelmode_img;
    }
}
