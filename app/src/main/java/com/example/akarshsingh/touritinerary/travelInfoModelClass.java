package com.example.akarshsingh.touritinerary;

public class travelInfoModelClass {

    public static final String TABLE_NAME = "travelInfo";
    public static final String COLUMN_FROM = "fromplace";
    public static final String COLUMN_TINUM = "tino";
    public static final String COLUMN_TO = "toplace";
    public static final String COLUMN_TRAVELMODE = "travelmode";
    public static final String COLUMN_PURPOSE = "purpose";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_ID = "id";

    private String from;
    private String to;
    private int travelmode;
    private String purpose;
    private String date;
    private String time;
    String ti_number;
    private int id;

    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_TINUM + " TEXT,"
            +COLUMN_FROM + " TEXT,"
            +COLUMN_TO + " TEXT,"
            +COLUMN_TRAVELMODE + " INTEGER,"
            +COLUMN_DATE + " TEXT,"
            +COLUMN_TIME + " TEXT,"
            +COLUMN_PURPOSE + " TEXT"
            +")";

    public travelInfoModelClass()
    {

    }

    public travelInfoModelClass(int id,String ti_number,String from, String to, int travelmode, String date, String time, String purpose)
    {
        this.id = id;
        this.ti_number =  ti_number;
        this.from = from;
        this.to = to;
        this.travelmode = travelmode;
        this.date = date;
        this.time = time;
        this.purpose = purpose;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTi_number() {
        return ti_number;
    }

    public void setTi_number(String ti_number) {
        this.ti_number = ti_number;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public int getTravelmode() {
        return travelmode;
    }

    public String getTime() {
        return time;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setTravelmode(int travelmode) {
        this.travelmode = travelmode;
    }
}
