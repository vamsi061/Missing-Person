package com.mperson.missingchild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PoliceLogin extends AppCompatActivity {
  private EditText pc_Email,pc_pwd;
  private Button btn_lg;
  private TextView button,fgpc;
  String email,password;
  private FirebaseAuth auth;
  private FirebaseUser user,currentuser;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_login);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        button=findViewById(R.id.btn_pc_rg);
        pc_Email = findViewById(R.id.Email);
        pc_pwd = findViewById(R.id.pwd);
        fgpc = findViewById(R.id.fgpc);
        progressDialog = new ProgressDialog(this);

        btn_lg = findViewById(R.id.btn_pc_lg);
        startApp();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PoliceLogin.this,police_register.class);
                startActivity(intent);
            }
        });

        fgpc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PoliceLogin.this,chng_pwd.class);
                startActivity(intent);
            }
        });

        btn_lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 email=pc_Email.getText().toString();
                 password=pc_pwd.getText().toString();

                if(email.isEmpty()){
                    pc_Email.setError("Please Enter Email id");
                    return;
                }
                if(password.isEmpty()){
                    pc_pwd.setError("Please Enter Password");
                    return;
                }

                /*progressBar.setVisibility(View.VISIBLE);
                progressDialog.show();*/
                SignIn(email,password);
            }
        });

    }

    private void startApp() {
        /*progressBar.setVisibility(View.VISIBLE);
        progressDialog.show();*/
        FirebaseUser currentuser=auth.getCurrentUser();
        if(currentuser!=null){
            Toast.makeText(PoliceLogin.this,"Login Success",Toast.LENGTH_SHORT).show();
            updateUI(currentuser);
            startActivity(new Intent(PoliceLogin.this,pcmainpage.class));
            finish();

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        /*progressBar.setVisibility(View.VISIBLE);
        progressDialog.show();*/
        FirebaseUser currentuser=auth.getCurrentUser();
        if(currentuser!=null){
            updateUI(currentuser);

        }
    }

    private void updateUI(FirebaseUser user) {
        FirebaseUser currnetuser=auth.getCurrentUser();
        Intent profileIntent = new Intent(getApplicationContext(), EditProfile.class);
        profileIntent.putExtra("pc_Email", currnetuser.getEmail());
        Log.v("DATA", user.getUid());
        startActivity(profileIntent);

    }

    private  void SignIn(String email,String password){
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener( this,new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            progressDialog.dismiss();


                            Toast.makeText(PoliceLogin.this,"Login Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PoliceLogin.this,pcmainpage.class));
                            finish();
                        }
                        else{

                            progressDialog.dismiss();
                            Toast.makeText(PoliceLogin.this,"Login Failed. "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}