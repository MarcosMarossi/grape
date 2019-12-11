package com.example.grapeproject.Help;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsDB {

    private static FirebaseAuth auth;
    private static DatabaseReference firebase;


    public static DatabaseReference getDataBaseReference(){
        if(firebase == null){
            firebase = FirebaseDatabase.getInstance().getReference();
        }
        return firebase;
    }

    public static FirebaseAuth getFireBaseAuth(){

        if(auth ==null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
