package com.example.menu_and_listview;

import java.io.Serializable;
import java.util.UUID;

public class taskModel implements Serializable {
    private String id;
    private String stt = "";
    private String title = "";
    private String desc = "";
    private String date = "";

    private String isDone = "";


    public taskModel()
    {
        id = UUID.randomUUID().toString();
    }
    public taskModel(String id, String title, String desc, String date, String isDone)
    {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.isDone = isDone;
    }
    public taskModel(String title, String desc, String date, String isDone)
    {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.isDone = isDone;
    }

    public String getId() {
        return id;
    }
    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String isDone() {
        return isDone;
    }

    public void setDone(String done) {
        isDone = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
