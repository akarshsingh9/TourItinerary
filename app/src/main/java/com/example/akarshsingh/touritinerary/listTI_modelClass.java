package com.example.akarshsingh.touritinerary;

public class listTI_modelClass {

    private String officer_name;
    private String ti_no;
    private String status_ti;

    listTI_modelClass(String officer_name,String ti_no,String status_ti)
    {
        this.officer_name = officer_name;
        this.ti_no = ti_no;
        this.status_ti = status_ti;
    }

    public String getOfficer_name() {
        return officer_name;
    }

    public String getStatus_ti() {
        return status_ti;
    }

    public String getTi_no() {
        return ti_no;
    }
}
