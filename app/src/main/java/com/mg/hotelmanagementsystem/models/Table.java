package com.mg.hotelmanagementsystem.models;

import com.mg.surblime.BaseModel;

/**
 * Created by moses on 7/12/18.
 */

public class Table extends HotelBaseModel{

    private String tableName = "";

    public Table(){

    }

    public Table(String tableName){
        this.setTableName(tableName);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String getTitle() {
        return tableName;
    }
}
