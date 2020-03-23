package com.example.dostawa;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomerLoginActivity extends AppCompatActivity {
    private EditText cEmail, cPassword;
    private Button cLogin, cRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        cLogin = (Button) findViewById(R.id.login);
        cRegistration = (Button) findViewById(R.id.registration);

        cEmail = (EditText) findViewById(R.id.email);
        cPassword = (EditText) findViewById(R.id.password);

    }

}
