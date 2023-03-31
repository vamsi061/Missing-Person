package com.mperson.missingchild;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    private long Timeback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button_police);
        Button button2 = findViewById(R.id.button_public);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PoliceLogin.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PublicLogin.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - Timeback >1000 ){
            Timeback = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"Press again to exit",Toast.LENGTH_SHORT).show();
            return;
        }
        finishAffinity();
        super.onBackPressed();
    }
}