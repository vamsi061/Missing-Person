package com.mperson.missingchild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class PublicLogin extends AppCompatActivity {
    private EditText pbEmail,pbPwd;
    String email,password;
    private Button btn_lg;
    private TextView button,fgpb;
    private FirebaseAuth auth;
    private FirebaseUser user;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_login);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        pbEmail = findViewById(R.id.pb_Email);
        pbPwd = findViewById(R.id.pb_Pwd);
        btn_lg = findViewById(R.id.btnpb_lg);
        button=findViewById(R.id.btn_pb_rg);
        fgpb = findViewById(R.id.fgpb);
        progressDialog = new ProgressDialog(this);
        startApp();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PublicLogin.this,public_register.class);
                startActivity(intent);
            }
        });

        fgpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublicLogin.this,chng_pwd1.class);
                startActivity(intent);
            }
        });

        btn_lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 email = pbEmail.getText().toString();
                 password = pbPwd.getText().toString();
                if(TextUtils.isEmpty((email))){
                    pbEmail.setError("Please enter your email id");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    pbPwd.setError("Please enter valid Password");
                    return;
                }
                SignIn(email,password);
            }
        });
    }

    private void startApp() {
        if(user!=null){
            Toast.makeText(PublicLogin.this,"Login Success",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(PublicLogin.this,pbmainpage.class));
            finish();

        }
    }


    private void SignIn(String email, String password){
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(PublicLogin.this,"Login Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PublicLogin.this,pbmainpage.class));
                            finish();

                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(PublicLogin.this,"Login Failed "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}