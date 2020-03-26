package com.example.dostawa;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.net.NetworkInfo;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText cEmail, cPassword;
    private Button cLogin, cRegistration;

    private FirebaseAuth cAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        cAuth = FirebaseAuth.getInstance();
        cEmail = (EditText) findViewById(R.id.cemail);
        cPassword = (EditText) findViewById(R.id.cpassword);
//        findViewById(R.id.clogin).setOnClickListener(this);
        findViewById(R.id.clogin).setOnClickListener(this);
        findViewById(R.id.cregistration).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registration){
            Registracja(cEmail.getText().toString(), cPassword.getText().toString());
        }else if (v.getId() == R.id.login){
            Login(cEmail.getText().toString(), cPassword.getText().toString());
        }
    }
    public void Login(String email, String password){
        cAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CustomerLoginActivity.this, "Sing in sucsessfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CustomerLoginActivity.this, SecondCActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CustomerLoginActivity.this, "Sign in is not sucessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Registracja (String email, String password){
        cAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CustomerLoginActivity.this, "Registration is sucsessfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CustomerLoginActivity.this, SecondCActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CustomerLoginActivity.this, "Registration is not sucsessfull", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
