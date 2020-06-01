package com.example.dostawa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button BCutomer, BCourier, Bblog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BCutomer = (Button) findViewById(R.id.b_customer);
        BCourier = (Button) findViewById(R.id.b_courier);
        //guzik bez logowania
        Bblog = (Button) findViewById(R.id.u_bezlogowania);

        BCutomer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, CustomerActivity.class));
                Intent intent = new Intent(MainActivity.this, CustomerActivity.class);
                 startActivity(intent);
                 finish();
                 return;
            }
        });

        BCourier.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DriverActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        Bblog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestN.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}
