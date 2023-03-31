package com.mperson.missingchild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class chng_pwd extends AppCompatActivity {

    private Button forgetbtn;
    private TextView bac;
    private EditText textEmail;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chng_pwd);
        auth = FirebaseAuth.getInstance();
        forgetbtn = findViewById(R.id.chng_pwd);
        textEmail = findViewById(R.id.Email);

        bac = findViewById(R.id.back);

        forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                validateData();
            }
        });

        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chng_pwd.this,PoliceLogin.class);
                startActivity(intent);
            }
        });

    }


    private void validateData() {
        email = textEmail.getText().toString();
        if (email.isEmpty()) {
            textEmail.setError("Required");
        } else {
            forgetPass();
        }
    }

    private void forgetPass() {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(chng_pwd.this,"Check your Mail",Toast.LENGTH_SHORT).show();
                    Toast.makeText(chng_pwd.this,"Don't Forget Check in Spam",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(chng_pwd.this,PoliceLogin.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(chng_pwd.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}