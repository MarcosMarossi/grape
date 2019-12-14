package com.example.grapeproject.Models;

import com.example.grapeproject.Help.Base64;
import com.example.grapeproject.Help.Custom;
import com.example.grapeproject.Help.SettingsDB;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Values {

    private String date;
    private int quantity;
    private double price;
    private double value;
    private String type;
    private String custumer;
    private double kg;
    private String key;


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

    public double getKg() {
        return kg;
    }

    public void setKg(double kg) {
        this.kg = kg;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void salvar(String dateSave){

        FirebaseAuth autenticacao = SettingsDB.getFireBaseAuth();
        String id = Base64.codeBase( autenticacao.getCurrentUser().getEmail() );
        String dataEscolhida = Custom.mesAno( dateSave );
        DatabaseReference firebase = SettingsDB.getDataBaseReference();
        firebase.child( "value" )
                .child( id )
                .child( dataEscolhida )
                .push()
                .setValue( this );
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
