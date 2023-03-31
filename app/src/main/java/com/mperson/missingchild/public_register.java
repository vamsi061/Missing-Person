package com.mperson.missingchild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class public_register extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText pbName,pbID,pbNum,pbEmail,pbPwd;
    String pb_Name,pb_ID,pb_Num,pb_Email,pb_Pwd;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Public_Info");
    private Button btnpbreg;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_register);
        auth = FirebaseAuth.getInstance();
        pbName= findViewById(R.id.pbName);
        pbID = findViewById(R.id.pbID);
        pbNum = findViewById(R.id.pbNum);
        pbEmail = findViewById(R.id.pbEmail);
        pbPwd = findViewById(R.id.pbPwd);
        btnpbreg=findViewById(R.id.btnpb_reg);
        progressDialog = new ProgressDialog(this);

        btnpbreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb_Name=pbName.getText().toString();
                pb_ID=pbID.getText().toString();
                pb_Num=pbNum.getText().toString();
                pb_Email=pbEmail.getText().toString();
                pb_Pwd=pbPwd.getText().toString();
                if(pb_Name.isEmpty()){
                    pbName.setError("Please Enter your Name");
                    return;
                }
                if(pb_ID.isEmpty()){
                    pbID.setError("Please Enter your Aadhar Number");
                    return;
                }
                if(pb_Num.isEmpty()){
                    pbNum.setError("Please Enter your Number");
                    return;
                }
                if(pb_Email.isEmpty()){
                    pbEmail.setError("Please Enter your Email");
                    return;
                }
                if(pb_Pwd.isEmpty()){
                    pbPwd.setError("Please Enter password");
                    return;
                }
                registerpublic(pb_Email,pb_Pwd);
            }
        });
    }

   private void registerpublic(String pb_Email, String pb_Pwd){
       progressDialog.setTitle("Please Wait...");
       progressDialog.show();
        auth.createUserWithEmailAndPassword(pb_Email,pb_Pwd)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser user=auth.getCurrentUser();
                            updateUi(user,pb_Email);
                            Toast.makeText(public_register.this,"Registration Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(public_register.this,pbmainpage.class));
                            finish();

                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(public_register.this,"Registration Failed "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }
    private void updateUi(FirebaseUser user,String pb_email){
        HashMap<String,Object> map=new HashMap<>();
        map.put("pb_Name",pbName.getText().toString());
        map.put("Aadhar_No",pbID.getText().toString());
        map.put("pb_Num",pbNum.getText().toString());
        map.put("pb_Email",pb_Email);
        root.push().setValue(map);
    }
}