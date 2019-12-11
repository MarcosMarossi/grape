package com.example.grapeproject.Models;

import com.example.grapeproject.Help.SettingsDB;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Users {

    private String name;
    private String email;
    private String password;
    private String idUser;

    private Double totalExpense = 0.0;
    private Double totalProfit = 0.0;

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Users() {
    }

    public void salvar(){

        DatabaseReference firebase = SettingsDB.getDataBaseReference();
        firebase.child( "users" )
                .child( this.idUser)
                .setValue( this );
    }

    @Exclude
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
