package com.mperson.missingchild;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SingleUserProfile extends AppCompatActivity {

    ImageView SingleImg;
    String phn;
    String name;
    String Age;
    String Loc;
    String img;
    String pos;
    String UID;
    TextView Mperson,Mage,Mheight,Mloc,MtypePerson,Mname,Mcomp,Mface,Meye,cdate,firno,Cname,relation,cphone,cloc;
    FloatingActionButton call, share, delete;
    FirebaseUser user;
    private  FirebaseDatabase db=FirebaseDatabase.getInstance();
    private  DatabaseReference ref=db.getReference().child("Missing_Info");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user_profile);

        call = findViewById(R.id.fab_call);
        share = findViewById(R.id.fab_share);
        delete = findViewById(R.id.fab_fab);
        Mperson = findViewById(R.id.Mperson);
        Mage = findViewById(R.id.Mage);
        Mheight = findViewById(R.id.Mheight);
        Mloc = findViewById(R.id.Mloc);
        MtypePerson = findViewById(R.id.MtypePerson);
        Mname = findViewById(R.id.Mname);
        Mcomp = findViewById(R.id.Mcomp);
        Mface = findViewById(R.id.Mface);
        Meye = findViewById(R.id.Meye);
        cdate = findViewById(R.id.cdate);
        firno = findViewById(R.id.firno);
        Cname = findViewById(R.id.Cname);
        relation = findViewById(R.id.relation);
        cphone = findViewById(R.id.cphone);
        cloc = findViewById(R.id.cloc);


        SingleImg = findViewById(R.id.imageView2);

        Mperson.setText(getIntent().getStringExtra("Mperson"));
        Mage.setText(getIntent().getStringExtra("Mage"));
        Mheight.setText(getIntent().getStringExtra("Mheight"));
        Mloc.setText(getIntent().getStringExtra("Mloc"));
        MtypePerson.setText(getIntent().getStringExtra("MtypePerson"));
        Mname.setText(getIntent().getStringExtra("Mname"));
        Mcomp.setText(getIntent().getStringExtra("Mcomp"));
        Mface.setText(getIntent().getStringExtra("Mface"));
        Meye.setText(getIntent().getStringExtra("Meye"));
        cdate.setText(getIntent().getStringExtra("cdate"));
        firno.setText(getIntent().getStringExtra("firno"));
        Cname.setText(getIntent().getStringExtra("Cname"));
        relation.setText(getIntent().getStringExtra("relation"));
        cphone.setText(getIntent().getStringExtra("cphone"));
        cloc.setText(getIntent().getStringExtra("pstation"));



        phn = getIntent().getStringExtra("cphone");
        name = getIntent().getStringExtra("Mperson");
        Age = getIntent().getStringExtra("Mage");
        Loc = getIntent().getStringExtra("Mloc");
        img =getIntent().getStringExtra("SingleImg");
        pos = getIntent().getStringExtra("position");
        UID=getIntent().getStringExtra("UID");




        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callingintent = new Intent(Intent.ACTION_DIAL);
                callingintent.setData(Uri.parse(("tel:"+phn)));
                startActivity(callingintent );
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Check out this Details of a Missing Person");
                intent.putExtra(Intent.EXTRA_TEXT,"*Chek out the Details of Missing Person*\n"+"Name: "+name+"\n"+"Age: "+Age+"\n"+"Location: "+Loc+"\nImage: "+img);
                startActivity(Intent.createChooser(intent,"Share Via"));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(SingleUserProfile.this);
                builder.setMessage("Do you want to Delete is item?");
                builder.setCancelable(true);
                builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference rem=FirebaseDatabase.getInstance().getReference("Missing_Info").child(UID);
                        rem.removeValue();
                        Toast.makeText(SingleUserProfile.this,"Data Deleted Successfully:",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(SingleUserProfile.this,pcmainpage.class);
                        startActivity(intent);

                    }
                });
                builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();


            }
        });


        Picasso.get().load(getIntent().getStringExtra("SingleImg"))
                .placeholder(R.drawable.avatar)
                .into(SingleImg);

    }



}