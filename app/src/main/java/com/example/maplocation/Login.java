package com.example.maplocation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Login extends AppCompatActivity {

    private EditText edtUserName;
    private EditText edtPassword;
    private Button btnLogin;

    private AlertDialog myAlertDialogue;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edtUserName = findViewById(R.id.idEdtUserName);
        edtPassword = findViewById(R.id.idEdtPassword);
        btnLogin = findViewById(R.id.idBtnLogin);

        myAlertDialogue = new AlertDialog.Builder(getApplicationContext()).create();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String pass = edtPassword.getText().toString();

                //DEMANDE DE REQUETTE pou check le Login==================
                MyAsyncTask myAsyncTAsk = new MyAsyncTask(Login.this);
                String command = "login";
                myAsyncTAsk.execute(command,userName,pass);
                //===========================

            }
        });
    }


    public void sendToRegister(View view) {
        Intent i = new Intent(getApplicationContext(),Register.class);
        startActivity(i);
    }
}
