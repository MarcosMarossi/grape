package com.example.grapeproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grapeproject.Help.SettingsDB;
import com.example.grapeproject.Models.Users;
import com.example.grapeproject.Panel.StatusActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btLogin;
    private Users user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById( R.id.etEmail );
        etPassword = findViewById( R.id.etPassword );
        btLogin = findViewById( R.id.btLogin );

        btLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String senha = etPassword.getText().toString();

                if(!email.isEmpty()){
                    if(!senha.isEmpty()){

                        user = new Users();
                        user.setEmail( email );
                        user.setPassword( senha );
                        validarEntrada();

                    } else{
                        Toast.makeText( getApplicationContext(), "Por favor, digite a senha.", Toast.LENGTH_SHORT ).show();
                    }
                } else{
                    Toast.makeText( getApplicationContext(), "Por favor, digite o email.", Toast.LENGTH_SHORT ).show();
                }
            }
        });
    }

    public void validarEntrada(){

        auth = SettingsDB.getFireBaseAuth();
        auth.signInWithEmailAndPassword(
                user.getEmail(),user.getPassword()
        ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity( new Intent( getApplicationContext(), StatusActivity.class ) );
                    finish();
                } else{

                    String excecao = "";

                    try{
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e){
                        excecao = "Usuário não está cadastrado.";
                    }
                    catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "E-mail e senha incompatíveis. Tente novamente.";
                    }
                    catch (Exception e){
                        excecao = "Erro ao cadastrar o usuário.";
                        e.printStackTrace();
                    }
                    Toast.makeText( getApplicationContext() , excecao, Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }
}
