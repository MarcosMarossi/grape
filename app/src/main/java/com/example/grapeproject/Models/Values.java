package com.example.grapeproject.Models;

import com.example.grapeproject.Help.Base64;
import com.example.grapeproject.Help.Custom;
import com.example.grapeproject.Help.SettingsDB;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Values implements Comparable<Values> {

    private String date;
    private int quantity;
    private Double price;
    private double value;
    private String type;
    private String custumer;
    private String key;
    private Date d1,d2;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    public Values () {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCustumer() {
        return custumer;
    }

    public void setCustumer(String custumer) {
        this.custumer = custumer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void salvar(String dateSave){

        FirebaseAuth fireBaseAuth = SettingsDB.getFireBaseAuth();
        String id = Base64.codeBase( fireBaseAuth.getCurrentUser().getEmail() );
        String dateSelected = Custom.mesAno( dateSave );
        DatabaseReference firebase = SettingsDB.getDataBaseReference();
        firebase.child( "value" )
                .child( id )
                .child( dateSelected )
                .push()
                .setValue( this );
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int compareTo(Values values) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = format.parse(this.getDate());
            d2 = format.parse(values.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d1.after(d2) ? -1 : 1;
    }
}
