package com.example.usama.adhaan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    EditText U, P, E, C;
    Spinner spinner;
    Button L;
    String u, p, e, c, b;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        myRef= database.getReference("Users");
        U = findViewById(R.id.username);
        P = findViewById(R.id.password);
        E = findViewById(R.id.email);
        C = findViewById(R.id.confirmPass);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,getResources().getStringArray(R.array.cities));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void login(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void signup(View view) {

        final String username = U.getText().toString();
        final String email = E.getText().toString();
        final String pass= P.getText().toString();
        final String confirm = C.getText().toString();
        final String city= spinner.getSelectedItem().toString();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getApplicationContext(), "Enter Username!", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(email) || !isEmailValid(email)) {
            Toast.makeText(getApplicationContext(), "Invalid Email address!", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();

        }
        else if (!pass.equals(confirm)){
            Toast.makeText(getApplicationContext(), "Password doesn't match!", Toast.LENGTH_SHORT).show();
        }
        else if (pass.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
        }
        else if(city.equals("Choose your City"))
        {
            Toast.makeText(getApplicationContext(), "Choose your city!", Toast.LENGTH_SHORT).show();
        }
        else{

            firebaseAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        ProgressDialog progress = new ProgressDialog(signup.this);
                        progress.setMessage("Signing Up...");
                        progress.show();

                        Intent intent = new Intent(signup.this, login.class);
                        startActivity(intent);

                        finish();
//                        String id = myRef.push().getKey();
//                        String uid = firebaseAuth.getInstance().getCurrentUser().getUid();
//                        Users user = new Users(id, username, pass, email, city);
//                        Users user1=new Users(username, pass, email, city);
//
//                        myRef.child(uid).setValue(user1);
                    }
                    else {
                        Toast.makeText(signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            });

        }
    }

    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }
}




