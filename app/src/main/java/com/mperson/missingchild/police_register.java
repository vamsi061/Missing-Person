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

public class police_register extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText pcName,pcID,pcNum,pcEmail,pcPwd;
     String pc_Name,pc_ID,pc_Num,pc_Email,pc_Pwd;
     private  FirebaseDatabase db=FirebaseDatabase.getInstance();
     private  DatabaseReference root=db.getReference().child("Police_Info");
    private Button btnpcreg;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_register);
        auth = FirebaseAuth.getInstance();
        pcName= findViewById(R.id.pcName);
        pcID = findViewById(R.id.pcID);
        pcNum = findViewById(R.id.pcNum);
        pcEmail = findViewById(R.id.pcEmail);
        pcPwd = findViewById(R.id.pcPwd);
        btnpcreg=findViewById(R.id.btnpc_reg);

        btnpcreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pc_Name=pcName.getText().toString();
                pc_ID=pcID.getText().toString();
                pc_Num=pcNum.getText().toString();
                pc_Email=pcEmail.getText().toString();
                pc_Pwd=pcPwd.getText().toString();
                if(pc_Name.isEmpty()){
                    pcName.setError("Name Needed");
                    return;
                }
                if(pc_ID.isEmpty()){
                    pcID.setError("Enter ID");
                    return;
                }
                if(pc_Num.isEmpty()){
                    pcNum.setError("Number Needed");
                    return;
                }
                if(pc_Email.isEmpty()){
                    pcEmail.setError("Enter Email");
                    return;
                }
                if(pc_Pwd.isEmpty()){
                    pcPwd.setError("Please Enter password");
                    return;
                }
                registerpolice(pc_Email,pc_Pwd);

            }

        });
    }


    private void registerpolice(String pc_Email, String pc_Pwd) {
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        auth.createUserWithEmailAndPassword(pc_Email,pc_Pwd)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser user=auth.getCurrentUser();
                            updateUi(user,pc_Email);
                            Toast.makeText(police_register.this,"Registration Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(police_register.this,pcmainpage.class));
                            finish();
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(police_register.this,"Registration Failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    private void updateUi(FirebaseUser user, String pc_email) {
        HashMap<String,Object> map =new HashMap<>();
        map.put("pc_Name",pcName.getText().toString());
        map.put("pc_ID",pcID.getText().toString());
        map.put("pc_Num",pcNum.getText().toString());
        map.put("pc_Email",pc_Email);
        root.push().setValue(map);

    }

}