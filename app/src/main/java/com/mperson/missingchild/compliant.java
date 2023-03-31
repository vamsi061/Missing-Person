package com.mperson.missingchild;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ActivityNavigatorDestinationBuilderKt;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class compliant extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mstorage;
    ImageButton imageButton;
    TextInputEditText name, age,height,district,aliName,face,eye,cname,relationame,pnum,pstation,firnum,loc;
    EditText firdate;
    Button submit;
    String value,values;
    private static final int Gallery_Code = 1;
    Uri imageUri = null;
    ProgressDialog progressDialog;
    private ActivityNavigatorDestinationBuilderKt CropImage;
    private Object CropImageView;
    private Spinner spinner,spinner2;
    private Button m1;
    EditText dateformat;
    int year;
    int month;
    int day;
    ArrayAdapter<String> arrayAdapter;
    String[] persons = {"Missing Boy", "Missing Girl", "Missing Men", "Missing Women"};
    String[] comp={"Fair","Wheatish","Dark","Sallow","Very fair"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compliant);

        name=findViewById(R.id.in1);
        imageButton = findViewById(R.id.img1);
        age = findViewById(R.id.in2);
        submit = findViewById(R.id.m1);
        m1 = findViewById(R.id.m1);
        height = findViewById(R.id.in15);
        district = findViewById(R.id.in3);
        aliName = findViewById(R.id.in10);
        face = findViewById(R.id.in4);
        eye = findViewById(R.id.in5);
        cname = findViewById(R.id.in6);
        relationame = findViewById(R.id.in9);
        pnum = findViewById(R.id.in7);
        pstation = findViewById(R.id.in16);
        firnum = findViewById(R.id.in13);
        firdate = findViewById(R.id.dateformat);
        loc = findViewById(R.id.in14);

        spinner = findViewById(R.id.spin1);
        spinner2 = findViewById(R.id.spin2);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(compliant.this,android.R.layout.simple_spinner_item,persons);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 value=parent.getItemAtPosition(position).toString();
                Toast.makeText(compliant.this,value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(compliant.this,android.R.layout.simple_spinner_item,comp);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 values=parent.getItemAtPosition(position).toString();
                Toast.makeText(compliant.this,values, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dateformat = findViewById(R.id.dateformat);
        Calendar calendar=Calendar.getInstance();
        dateformat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(compliant.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String dateString = day + "/"+ month + "/" + year;
                        dateformat.setText(dateString);

                    }
                }, year,month,day);
                datePickerDialog.show();

            }
        });

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Missing_Info");
        mstorage = FirebaseStorage.getInstance();

        progressDialog = new ProgressDialog(this);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Gallery_Code && resultCode == RESULT_OK )
        {
            imageUri = data.getData();
            imageButton.setImageURI(imageUri);

        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn = name.getText().toString();
                String ag = age.getText().toString();
                String hg = height.getText().toString();
                String dt = district.getText().toString();
                String anaame = aliName.getText().toString();
                String fc = face.getText().toString();
                String ey = eye.getText().toString();
                String cnam =cname.getText().toString();
                String relation =relationame.getText().toString();
                String pn =  pnum.getText().toString();
                String pst = pstation.getText().toString();
                String finn = firnum.getText().toString();
                String fd =firdate.getText().toString();
                String pc = loc.getText().toString();
                String fird = firdate.getText().toString();
                String psta = pstation.getText().toString();
                if(!(fn.isEmpty() && ag.isEmpty() && imageUri!=null && hg.isEmpty() && dt.isEmpty() && anaame.isEmpty() && fc.isEmpty() && ey.isEmpty() && cnam.isEmpty() && relation.isEmpty() && pn.isEmpty() && pst.isEmpty() && finn.isEmpty() && pc.isEmpty() && fird.isEmpty()))
                {
                    progressDialog.setTitle("Uploading data...");
                    progressDialog.show();

                    StorageReference filepath = mstorage.getReference().child("ImagePost").child(imageUri.getLastPathSegment());
                    filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           Task<Uri> downladuri = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult().toString();
                                    DatabaseReference newPost = mRef.push();
                                    String uid = newPost.getKey();

                                    newPost.child("Name").setValue(fn);
                                    newPost.child("Type_of_Person").setValue(value);
                                    newPost.child("Age").setValue(ag);
                                    newPost.child("Height").setValue(hg);
                                    newPost.child("Alias_Name").setValue(anaame);
                                    newPost.child("Face_Type").setValue(fc);
                                    newPost.child("Eye_Type").setValue(ey);
                                    newPost.child("Complexion").setValue(values);
                                    newPost.child("Contact_Per_Name").setValue(cnam);
                                    newPost.child("Relationship_Type").setValue(relation);
                                    newPost.child("Contact_Phone_Number").setValue(pn);
                                    newPost.child("Pstation").setValue(psta);
                                    newPost.child("Fir_Date").setValue(fd);
                                    newPost.child("Fir_Number").setValue(finn);
                                    newPost.child("Location").setValue(pc);
                                    newPost.child("UID").setValue(uid);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();
                                    Toast.makeText(compliant.this,"Data uploaded Successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(compliant.this,pcmainpage.class);
                                    startActivity(intent);
                                }
                            });




                        }
                    });

                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(compliant.this,"Data upload failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}