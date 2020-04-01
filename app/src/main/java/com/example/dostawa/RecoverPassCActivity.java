package com.example.dostawa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecoverPassCActivity extends AppCompatActivity {
    private Button resetPasswordCust;
    private EditText resetEmailCust;
    private FirebaseAuth crAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_pass_c);


        crAuth = FirebaseAuth.getInstance();

        resetPasswordCust = (Button) findViewById(R.id.buttonResetC);
        resetEmailCust = (EditText) findViewById(R.id.emailResetC);

        resetPasswordCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crAuth.sendPasswordResetEmail(resetEmailCust.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(RecoverPassCActivity.this, "Check your mail", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RecoverPassCActivity.this, CustomerActivity.class));
                                }
                                else{
                                    Toast.makeText(RecoverPassCActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

//        resetPasswordCust.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//
//                String customerEmail = resetEmailCust.getText().toString();
//
//                if (TextUtils.isEmpty(customerEmail)){
//
//                    Toast.makeText(RecoverPassCActivity.this, "Wpisz email", Toast.LENGTH_SHORT).show();
//
//                }
//                else
//                    {
//                    crAuth.sendPasswordResetEmail(customerEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful())
//                            {
//                                Toast.makeText(RecoverPassCActivity.this, "Sprawdz skryńkę pocztową...", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(RecoverPassCActivity.this, CustomerLoginActivity.class));
//                            }
//                            else{
//                                String message = task.getException().getMessage();
//                                Toast.makeText(RecoverPassCActivity.this, "Stał się błąd: " + message, Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }
//            }
//        });
    }
}
