package com.mperson.missingchild;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class pbmainpage extends AppCompatActivity {
    private long Timeback;
    BottomAppBar bottomapp;
    BottomNavigationView bottomNavigation;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbmainpage);

        bottomNavigation=findViewById(R.id.bottomNavigationView);
        bottomNavigation.setBackground(null);

        bottomNavigation.setSelectedItemId(R.id.home);
       getSupportFragmentManager().beginTransaction().replace(R.id.body_container,new pbhomefragment()).commit();

        recyclerView = findViewById(R.id.userList);


        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            Fragment fragement=null;
            switch (item.getItemId()){
                case R.id.home:
                    fragement = new pbhomefragment();
                    break;
                case R.id.search:
                   /*Intent intent=new Intent(pcmainpage.this,userslist.class);
                   startActivity(intent);*/
                    fragement = new SearchFragment1();
                    break;
                case R.id.Profile:
                   /*Intent intent=new Intent(pcmainpage.this,Profile.class);
                   startActivity(intent);*/
                    fragement =new pbprofilefragment();
                    break;
                case R.id.Exit:
                    if(true){
                        AlertDialog.Builder builder=new AlertDialog.Builder(pbmainpage.this);
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


        bottomapp = findViewById(R.id.bottomAppBar);

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