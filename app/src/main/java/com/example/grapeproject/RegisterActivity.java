package com.example.grapeproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grapeproject.Help.Base64;
import com.example.grapeproject.Help.SettingsDB;
import com.example.grapeproject.Models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword;
    private Button btRegister;
    private FirebaseAuth auth;
    private Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById( R.id.etName);
        etEmail = findViewById( R.id.etEmail );
        etPassword = findViewById( R.id.etPassword);
        btRegister = findViewById( R.id.btRegister);


        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = etName.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etPassword.getText().toString();

                if(!nome.isEmpty()){
                    if(!email.isEmpty()){
                        if(!senha.isEmpty()){

                            user = new Users();
                            user.setName( nome );
                            user.setEmail( email );
                            user.setPassword( senha );
                            cadastrarUsuario();


                        } else{
                            Toast.makeText( getApplicationContext(), "Por favor, digite a senha.", Toast.LENGTH_SHORT ).show();
                        }
                    } else{
                        Toast.makeText( getApplicationContext(), "Por favor, digite o email.", Toast.LENGTH_SHORT ).show();
                    }
                } else{
                    Toast.makeText( getApplicationContext(), "Por favor, digite o nome.", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

    public void cadastrarUsuario(){
        auth = SettingsDB.getFireBaseAuth();
        auth.createUserWithEmailAndPassword(
                user.getEmail(), user.getPassword()
        ).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String idUsuario = Base64.codeBase( user.getEmail() );
                    user.setIdUser( idUsuario );
                    user.salvar();
                    finish();
                }
                else{

                    String excecao = "";
                    try{
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha forte";
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Por favor, digite um email válido!";
                    } catch (FirebaseAuthUserCollisionException e){
                        excecao = "Conta já cadastrada!";
                    } catch (Exception e){
                        excecao = "Erro ao cadastrar o usuário.";
                        e.printStackTrace();
                    }
                    Toast.makeText( getApplicationContext(), excecao , Toast.LENGTH_SHORT ).show();
                    Log.w("ERRO", "createUserWithEmail:failure", task.getException());
                }
            }
        } );
    }
}
