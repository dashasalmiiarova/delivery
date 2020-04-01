package com.example.dostawa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverActivity extends AppCompatActivity {
    EditText cEmail, cPassword;
    Button cLogin, cRegistration, cRecoverP;
    //    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    FirebaseAuth cAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        cAuth = FirebaseAuth.getInstance();
        cEmail = (EditText) findViewById(R.id.cemail);
        cPassword = (EditText) findViewById(R.id.cpassword);

        cLogin = (Button) findViewById(R.id.clogin);
        cRegistration = (Button) findViewById(R.id.cregistration);
        cRecoverP = (Button) findViewById(R.id.recoverpasscustomer);
        cAuth = FirebaseAuth.getInstance();


        cRegistration.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                cAuth.createUserWithEmailAndPassword(cEmail.getText().toString(),
                        cPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(DriverActivity.this, "Registration is sucsessfull", Toast.LENGTH_SHORT).show();

                                } else  {
                                    Toast.makeText(DriverActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

        cLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cAuth.signInWithEmailAndPassword(cEmail.getText().toString(),
                        cPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(DriverActivity.this, "Login is sucsessfull", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(DriverActivity.this, SecondCActivity.class));
                                }
                                else{
                                    Toast.makeText(DriverActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        cRecoverP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DriverActivity.this, RecoverPassCActivity.class));
            }
        });

    }
}
