package com.mperson.missingchild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    /*RecyclerView recyclerView;
    ArrayList<MissingDetails> recyclelist;
    FirebaseDatabase firebaseDatabase;*/
    RecyclerView recyclerView;
    DatabaseReference database;
    ProjectAdpater myAdapter;
    ArrayList<MissingDetails> list;
    SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);






        recyclerView = findViewById(R.id.userList);
       searchView = findViewById(R.id.serachview);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("Missing_Info");
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        list = new ArrayList<>();
        //myAdapter = new ProjectAdpater(this, list, this.getApplicationContext());
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    MissingDetails user = dataSnapshot.getValue(MissingDetails.class);
                    list.add(user);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void filterList(String text) {
        List<MissingDetails> filterelist=new ArrayList<>();
        for( MissingDetails item : list){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filterelist.add(item);
            }
        }
        //ProjectAdpater.filteredlist(filterelist);
    }
   // @Override

    public void onItemClicked(MissingDetails model) {

        Intent intent = new Intent(Search.this, SingleUserProfile.class);
        intent.putExtra("SingleImg", model.getImage());
        intent.putExtra("Mperson", model.getName());
        intent.putExtra("Mage", model.getAge());
        intent.putExtra("Mheight", model.getHeight());
        intent.putExtra("Mloc", model.getLocation());
        intent.putExtra("MtypePerson", model.getType_of_Person());
        intent.putExtra("Mname", model.getAlias_Name());
        intent.putExtra("Mcomp", model.getComplexion());
        intent.putExtra("Mface", model.getFace_Type());
        intent.putExtra("Meye", model.getEye_Type());
        intent.putExtra("cdate", model.getFir_Date());
        intent.putExtra("firno", model.getFir_Number());
        intent.putExtra("Cname", model.getContact_Per_Name());
        intent.putExtra("relation", model.getRelationship_Type());
        intent.putExtra("cphone", model.getContact_Phone_Number());
        intent.putExtra("cloc", model.getLocation());
        this.startActivity(intent);


    }

   /* private void filterList(String text) {
        List<MissingDetails> filteredlist=new ArrayList<>();
        for( MissingDetails item : list){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(item);
            }
        }
        //ProjectAdpater.filteredlist(filteredlist);
    }*/

    /*public void onItemClicked(MissingDetails model) {

        Intent intent = new Intent(getActivity(),SingleUserProfile.class);
        intent.putExtra("SingleImg",model.getImage());
        intent.putExtra("Mperson",model.getName());
        intent.putExtra("Mage",model.getAge());
        intent.putExtra("Mheight",model.getHeight());
        intent.putExtra("Mloc",model.getLocation());
        intent.putExtra("MtypePerson",model.getType_of_Person());
        intent.putExtra("Mname",model.getAlias_Name());
        intent.putExtra("Mcomp",model.getComplexion());
        intent.putExtra("Mface",model.getFace_Type());
        intent.putExtra("Meye",model.getEye_Type());
        intent.putExtra("cdate",model.getFir_Date());
        intent.putExtra("firno",model.getFir_Number());
        intent.putExtra("Cname",model.getContact_Per_Name());
        intent.putExtra("relation",model.getRelationship_Type());
        intent.putExtra("cphone",model.getContact_Phone_Number());
        intent.putExtra("cloc",model.getLocation());
        getActivity().startActivity(intent);

    }*/

    }