package com.blogspot.nithinchalakkal.schoolvoiceapplication.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Calendar;
import java.util.Date;



public class Data {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    String name;
    @DatabaseField
    String itemID;
    @DatabaseField
    Date time;
    @DatabaseField
    int view_Like; // view=0 Like =1


    public Data() {
    }

    public Data(String itemID,String name, int view_Like) {
        super();

        this.itemID=itemID;
        this.name = name;
        this.time = (Calendar.getInstance().getTime());
        this.view_Like = view_Like;
    }


    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itemID='" + itemID + '\'' +
                ", time=" + time +
                ", view_Like=" + view_Like +
                '}';
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

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getView_Like() {
        return view_Like;
    }

    public void setView_Like(int view_Like) {
        this.view_Like = view_Like;
    }
}
