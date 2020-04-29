package com.example.dostawa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
    private ProgressDialog progressDialog;

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
                String email = cEmail.getText().toString();
                String password = cPassword.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(DriverActivity.this, "Puste pola!", Toast.LENGTH_SHORT).show();
                } else {
                    //даем пользователю знать что происходит
                    progressDialog.setTitle("Registracja!");
                    progressDialog.setMessage("Poczekaj dopóki stworzymy twój profil");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    cAuth.createUserWithEmailAndPassword(cEmail.getText().toString(),
                            cPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        cAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(DriverActivity.this, "Registration is successfully. Please check your email for verification", Toast.LENGTH_SHORT).show();
                                                    if (cAuth.getCurrentUser().isEmailVerified()) {

                                                        //перевод на другую активность, если логин успешен, делаем так чтобы нельзя было вернуться на старую страницу(логина)
                                                        Intent intent = new Intent(DriverActivity.this, SecondCActivity.class);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                                                        startActivity(intent);
//                                                        finish();
                                                    }
                                                } else {
                                                    Toast.makeText(DriverActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                                    } else {
                                        Toast.makeText(DriverActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });

        cLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Logowanie!");
                progressDialog.setMessage("Sprawdzamy dane, poczekaj chwilkę!");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                cAuth.signInWithEmailAndPassword(cEmail.getText().toString(),
                        cPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(DriverActivity.this, "Login is sucsessfull", Toast.LENGTH_SHORT).show();

                                    //перевод на другую активность, если логин успешен, делаем так чтобы нельзя было вернуться на старую страницу(логина)
                                    Intent intent = new Intent(DriverActivity.this, SecondCActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                                    startActivity(intent);
//                                    finish();
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
                Intent intent = new Intent(DriverActivity.this, SecondCActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                startActivity(intent);
            }
        });

    }
}
