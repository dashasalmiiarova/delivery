package com.example.dostawa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText dEmail, dPassword;
    private Button cLogin, cRegistration;

    private FirebaseAuth dAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        dAuth = FirebaseAuth.getInstance();
        dEmail = (EditText) findViewById(R.id.email);
        dPassword = (EditText) findViewById(R.id.password);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.registration).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registration){
            Registracja(dEmail.getText().toString(), dPassword.getText().toString());
        }else if (v.getId() == R.id.login){
            Login(dEmail.getText().toString(), dPassword.getText().toString());
        }
    }
    public void Login(String email, String password){
        dAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(DriverLoginActivity.this, "Sing in sucsessfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DriverLoginActivity.this, SecondCActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(DriverLoginActivity.this, "Sign in is not sucessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Registracja (String email, String password){
        dAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(DriverLoginActivity.this, "Registration is sucsessfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DriverLoginActivity.this, SecondCActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(DriverLoginActivity.this, "Registration is not sucsessfull", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
