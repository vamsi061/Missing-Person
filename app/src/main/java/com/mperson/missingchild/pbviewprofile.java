package com.mperson.missingchild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class pbviewprofile extends AppCompatActivity {
    private TextView tv1,tv2,tv3,tv4;
    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Map<String, String> userMap;
    private static final String USERS = "Public_Info";
    private FirebaseUser user;
    private String email;
    private  String mail;
    private String userid;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbviewprofile);
        back = findViewById(R.id.back2);

        user= FirebaseAuth.getInstance().getCurrentUser();
        userid=user.getUid();
        mail=user.getEmail();

        Intent intent = getIntent();
        email = intent.getStringExtra("pc_Email");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(USERS);
        Log.v("USERID",userRef.getKey());

        tv1=findViewById(R.id.tv12);
        tv2=findViewById(R.id.tv22);
        tv3=findViewById(R.id.tv32);
        tv4=findViewById(R.id.tv42);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(pbviewprofile.this,pbmainpage.class);
                startActivity(intent1);
            }
        });






        userRef.addValueEventListener(new ValueEventListener() {
            String email,name,id,num;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId: snapshot.getChildren()) {
                    if (keyId.child("pb_Email").getValue().equals(mail)) {
                        //mail = keyId.child("Police_Info").child(userid).child("pc_Email").getValue(String.class);
                        email=keyId.child("pb_Email").getValue(String.class);
                        name = keyId.child("pb_Name").getValue(String.class);
                        id = keyId.child("Aadhar_No").getValue(String.class);
                        num = keyId.child("pb_Num").getValue(String.class);
                        break;
                    }


                }
                tv1.setText(email);
                tv2.setText(name);
                tv3.setText(id);
                tv4.setText(num);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(pbviewprofile.this, "Failed to read the values", Toast.LENGTH_SHORT).show();

            }
        });


    }
}