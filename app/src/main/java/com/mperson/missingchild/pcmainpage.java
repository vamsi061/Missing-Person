package com.mperson.missingchild;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class pcmainpage extends AppCompatActivity {

    private long Timeback;
    BottomNavigationView bottomNavigation;
    FloatingActionButton fbutton;
    BottomAppBar bottomapp;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcmainpage);
        bottomNavigation=findViewById(R.id.bottomNavigationView);
        bottomNavigation.setBackground(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container,new HomeFragment()).commit();
       bottomNavigation.setSelectedItemId(R.id.home);

       recyclerView = findViewById(R.id.userList);

       bottomNavigation.setOnNavigationItemSelectedListener(item -> {
           Fragment fragement=null;
           switch (item.getItemId()){
               case R.id.home:
                   fragement = new HomeFragment();
                   break;
               case R.id.search:
                   /*Intent intent=new Intent(pcmainpage.this,userslist.class);
                   startActivity(intent);*/
                   fragement = new SearchFragment();
                   break;
               case R.id.Profile:
                   /*Intent intent=new Intent(pcmainpage.this,Profile.class);
                   startActivity(intent);*/
                   fragement =new ProfileFragment();
                   break;
               case R.id.Exit:
                   if(true){
                       AlertDialog.Builder builder=new AlertDialog.Builder(pcmainpage.this);
                       builder.setMessage("Do you want to Exit?");
                       builder.setCancelable(true);
                       builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               finishAffinity();
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
                   return true;

           }
           getSupportFragmentManager().beginTransaction().replace(R.id.body_container,fragement).commit();
           return true;
       });




        fbutton =findViewById(R.id.fab);
        bottomapp = findViewById(R.id.bottomAppBar);

        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(pcmainpage.this,"Register a Compliant",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(pcmainpage.this,compliant.class);
                startActivity(intent);
            }
        });


        bottomNavigation.setSelectedItemId(R.id.home);


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