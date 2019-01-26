package com.example.usama.adhaan;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText U;
    EditText P;
    String u, p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth= FirebaseAuth.getInstance();


    }


    public void signup(View view) {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }

    public void login1(View view){
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }

    public void login(View view) {
        U = findViewById(R.id.username);
        P = findViewById(R.id.password);
        u = U.getText().toString();
        p = P.getText().toString();


        if (u.equals("") || p.equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Invalid Credentials!");
            alertDialog.show();

        }
        else
        {
            firebaseAuth.signInWithEmailAndPassword(u, p)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                ProgressDialog progress = new ProgressDialog(login.this);
                                progress.setMessage("Loging In...");
                                progress.show();

                                Intent intent = new Intent(login.this, dashboard.class);
                                startActivity(intent);

                                finish();

                            }
                            else {

                                Toast.makeText(login.this, "Login not Successful try again", Toast.LENGTH_SHORT).show();
                            }
                        }
            });
        }
    }


}




