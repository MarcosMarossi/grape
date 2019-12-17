package com.example.grapeproject.Panel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.grapeproject.Adapter.AdapterValues;
import com.example.grapeproject.Help.Base64;
import com.example.grapeproject.Help.SettingsDB;
import com.example.grapeproject.Models.Users;
import com.example.grapeproject.Models.Values;
import com.example.grapeproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    private Double totalProfit = 0.0, average = 0.0;
    private int totalQuantity;
    private DatabaseReference reference = SettingsDB.getDataBaseReference();
    private FirebaseAuth auth =  SettingsDB.getFireBaseAuth();
    private DatabaseReference usuarioref, movimentacaoref = SettingsDB.getDataBaseReference();
    private ValueEventListener valEventListUser, valEventListValue;
    private int iBoxes, iRedBoxes, iYellowBoxes, iPlasticBoxes;
    private Double dProfit, dAverage;
    private TextView txtBoxes, txtRedBoxes, txtYellowBoxes, txtPlasticBoxes, txtProfit, txtAverage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        txtBoxes = findViewById(R.id.txtBoxes);
        txtRedBoxes = findViewById(R.id.txtRedBoxes);
        txtYellowBoxes = findViewById(R.id.txtYellowBoxes);
        txtPlasticBoxes = findViewById(R.id.txtPlasticBoxes);
        txtProfit = findViewById(R.id.txtProfit);
        txtAverage = findViewById(R.id.txtAvg);

        recuperarResumo();
    }

    public void recuperarResumo() {

        String email = auth.getCurrentUser().getEmail();
        String id = Base64.codeBase(email);
        usuarioref = reference.child("users").child(id);

        valEventListUser = usuarioref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Users users = dataSnapshot.getValue(Users.class);

                iBoxes = users.getTotalBoxes();
                txtBoxes.setText(""+iBoxes);

                iRedBoxes = users.getTotalRedBox();
                txtRedBoxes.setText(""+iRedBoxes);

                iYellowBoxes = users.getTotalYellowBox();
                txtYellowBoxes.setText(""+iYellowBoxes);

                iPlasticBoxes = users.getTotalPlasticBox();
                txtPlasticBoxes.setText(""+iPlasticBoxes);

                dProfit = users.getTotalProfit();
                Double average =  dProfit / iBoxes;

                DecimalFormat decimal = new DecimalFormat("0.##");
                String avg = decimal.format(average);
                String profit = decimal.format(dProfit);

                txtAverage.setText(avg);
                txtProfit.setText(profit);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}
