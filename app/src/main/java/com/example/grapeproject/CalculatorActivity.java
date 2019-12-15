package com.example.grapeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bAdd,
            bSub, bDiv, bMul, bClear, bBack, bDec, bRes;
    private TextView txt;
    private double v1,v2;
    boolean add,sub,div,mul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        getSupportActionBar().setElevation(0);

        b0 = findViewById(R.id.b0);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        bAdd = findViewById(R.id.bAdd);
        bDiv = findViewById(R.id.bDiv);
        bSub = findViewById(R.id.bSub);
        bMul = findViewById(R.id.bMul);
        bDec = findViewById(R.id.bDec);
        bRes = findViewById(R.id.bRes);
        txt = findViewById(R.id.texto);
        bClear = findViewById(R.id.bClear);
        bBack = findViewById(R.id.bBack);

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(txt.getText() + "0");
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(txt.getText() + "1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(txt.getText() + "2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(txt.getText() + "3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(txt.getText() + "4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(txt.getText() + "5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(txt.getText() + "6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(txt.getText() + "7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(txt.getText() + "8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(txt.getText() + "9");
            }
        });
        bDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txt.getText().toString().contains(".")) {

                } else {
                    txt.setText(txt.getText() + ".");
                }
            }
        });

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    v1 = Double.parseDouble(txt.getText() + "");
                    add = true;
                    txt.setText(null);
                } catch (Exception e){
                    System.out.println(e);
                }
            }
        });

        bSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    v1 = Double.parseDouble(txt.getText() + "");
                    sub = true;
                    txt.setText(null);
                } catch (Exception e){
                    System.out.println(e);
                }

            }
        });

        bDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    v1 = Double.parseDouble(txt.getText() + "");
                    div = true;
                    txt.setText(null);

                } catch (Exception e){
                    System.out.println(e);
                }

            }
        });

        bMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    v1 = Double.parseDouble(txt.getText() + "");
                    mul = true;
                    txt.setText(null);

                } catch (Exception e){
                    System.out.println(e);
                }

            }
        });

        bRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    v2 =  Double.parseDouble(txt.getText().toString());
                    if(add==true){
                        txt.setText(v1+v2+"");
                        add = false;
                    }
                    if(sub==true){
                        txt.setText(v1-v2+"");
                        sub = false;
                    }
                    if(div==true){
                        txt.setText(v1/v2+"");
                        div = false;
                    }
                    if(mul==true){
                        txt.setText(v1*v2+"");
                        mul = false;
                    }

                } catch (Exception e){
                    System.out.println(e);
                }
            }
        });

        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("");
            }
        });

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String str = txt.getText().toString();
                    String sub_str = str.substring(0, (str.length() - 1));

                    txt.setText(sub_str);
                } catch (Exception e){
                    System.out.println(e);
                }
            }
        });
    }
}
