package com.example.grapeproject.Panel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.grapeproject.Help.Base64;
import com.example.grapeproject.Help.Custom;
import com.example.grapeproject.Help.SettingsDB;
import com.example.grapeproject.Models.Users;
import com.example.grapeproject.Models.Values;
import com.example.grapeproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ProfitActivity extends AppCompatActivity {

    private EditText etValue, etData, etQuantity;
    private Values value;
    private String type, custumer;
    private Spinner spType, spCustumer;
    private Double totalProfit;
    private int totalBox, totalRedBox, totalYellowBox, totalPlacticBox;
    private DatabaseReference reference = SettingsDB.getDataBaseReference();
    private FirebaseAuth auth =  SettingsDB.getFireBaseAuth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit);

        getSupportActionBar().hide();

        etValue = findViewById( R.id.etPrice );
        etData = findViewById( R.id.etData );
        etQuantity = findViewById( R.id.etQuantity );

        etData.setText( Custom.dataAtual() );

        spType = findViewById(R.id.spType);
        ArrayAdapter<CharSequence> adaptertype = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        adaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(adaptertype);

        spCustumer = findViewById(R.id.spCustumer);
        ArrayAdapter<CharSequence> adaptercustumer = ArrayAdapter.createFromResource(this, R.array.custumer, android.R.layout.simple_spinner_item);
        adaptercustumer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCustumer.setAdapter(adaptercustumer);

        recuperarReceitaTotal();
    }

    public void addReceita(View v){

        if(validar()){
            value = new Values();

            String data = etData.getText().toString().trim().substring(0, 10);

            Double price = Double.parseDouble( etValue.getText().toString().trim() );
            int quantity = Integer.parseInt( etQuantity.getText().toString().trim() );

            type = spType.getSelectedItem().toString();
            custumer = spCustumer.getSelectedItem().toString();

            value.setDate( data );
            value.setPrice( price );
            value.setQuantity( quantity );
            value.setCustumer( custumer );
            value.setType( type );

            Double dailyRevenue = price * quantity;
            value.setValue(dailyRevenue);

            Double currentRevenue = totalProfit + (price * quantity);
            int totalBoxes = totalBox + quantity;

            updateProfit( currentRevenue );
            updateBoxes(totalBoxes);

            switch (type){
                case "Cx. Amarela":
                    int totalYellow = totalYellowBox + quantity;
                    updateYellowBox(totalYellow);
                    break;
                case "Cx. Vermelha":
                    int totalRed = totalRedBox + quantity;
                    updateRedBoxes(totalRed);
                    break;
                case "Cx. Plástica":
                    int totalPlastic = totalPlacticBox + quantity;
                    updatePlasticBox(totalPlastic);
                    break;
            }

            value.salvar(data);

            Toast.makeText( this, R.string.profit_moviment_sucess , Toast.LENGTH_SHORT ).show();
            finish();

        } else {
            Toast.makeText( this, R.string.profit_moviment_error , Toast.LENGTH_SHORT ).show();
        }
    }

    public Boolean validar(){

        String sValor = etValue.getText().toString();
        String sData = etData.getText().toString();
        String sQuantity = etQuantity.getText().toString();
        String sType = spType.getSelectedItem().toString();
        String sCustumer = spCustumer.getSelectedItem().toString();

        if(!sValor.isEmpty() && !sData.isEmpty() && !sQuantity.isEmpty()
                && !sType.contains("Selecione") && !sCustumer.contains("Selecione")){
        } else {
            Toast.makeText( this, R.string.profit_validation , Toast.LENGTH_SHORT ).show();
            return false;
        }

        return true;
    }

    public void recuperarReceitaTotal(){

        String email = auth.getCurrentUser().getEmail();
        String id = Base64.codeBase( email );
        DatabaseReference usuarioReferencia = reference.child( "users" ).child( id );

        usuarioReferencia.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                totalProfit = user.getTotalProfit();
                totalBox = user.getTotalBoxes();
                totalRedBox = user.getTotalRedBox();
                totalPlacticBox = user.getTotalPlasticBox();
                totalYellowBox = user.getTotalYellowBox();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void updateProfit(Double receita){

        String email = auth.getCurrentUser().getEmail();
        String id = Base64.codeBase( email );
        DatabaseReference usuarioReferencia = reference.child( "users" )
                .child( id );

        usuarioReferencia.child( "totalProfit" ).setValue( receita );
    }

    public void updateBoxes(int quantity){

        String email = auth.getCurrentUser().getEmail();
        String id = Base64.codeBase( email );
        DatabaseReference usuarioReferencia = reference.child( "users" )
                .child( id );

        usuarioReferencia.child( "totalBoxes" ).setValue( quantity );
    }

    public void updateRedBoxes(int quantity){

        String email = auth.getCurrentUser().getEmail();
        String id = Base64.codeBase( email );
        DatabaseReference usuarioReferencia = reference.child( "users" )
                .child( id );

        usuarioReferencia.child( "totalRedBox" ).setValue( quantity );
    }

    public void updatePlasticBox(int quantity){

        String email = auth.getCurrentUser().getEmail();
        String id = Base64.codeBase( email );
        DatabaseReference usuarioReferencia = reference.child( "users" )
                .child( id );

        usuarioReferencia.child( "totalPlasticBox" ).setValue( quantity );
    }

    public void updateYellowBox(int quantity){

        String email = auth.getCurrentUser().getEmail();
        String id = Base64.codeBase( email );
        DatabaseReference usuarioReferencia = reference.child( "users" )
                .child( id );

        usuarioReferencia.child( "totalYellowBox" ).setValue( quantity );
    }
}
