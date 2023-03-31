package com.mperson.missingchild;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment1 extends Fragment implements selectedListeners {
    RecyclerView recyclerView;
    DatabaseReference database;
    ProjectAdpater1 ProjectAdpater1;
    List<MissingDetails> itemlist;
    SearchView searchView;
    FloatingActionButton fbutton;
    String loca;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search1, container, false);


        recyclerView = view.findViewById(R.id.userList);
        searchView = view.findViewById(R.id.serachview);
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

        fbutton = view.findViewById(R.id.fab);

        recyclerView = view.findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("Missing_Info");
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        itemlist = new ArrayList<>();
        ProjectAdpater1 = new ProjectAdpater1(getActivity(), itemlist, model -> onItemClicked(model));
        recyclerView.setAdapter(ProjectAdpater1);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MissingDetails user = dataSnapshot.getValue(MissingDetails.class);
                    itemlist.add(user);
                }
                ProjectAdpater1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }



    private void filterList(String text) {
        List<MissingDetails> filterelist=new ArrayList<>();
        for( MissingDetails item : itemlist){
            if((item.getName().toLowerCase().contains(text.toLowerCase())) || (item.getAge().toLowerCase().contains(text.toLowerCase()) || (item.getLocation().toLowerCase().contains(text.toLowerCase())) ||  (item.getType_of_Person().toLowerCase().contains(text.toLowerCase())) || (item.getType_of_Person()=="Missing Boy") || (item.getType_of_Person()=="Missing Girl") ) ){
                filterelist.add(item);
            }
        }
        if(filterelist.isEmpty()){
            Toast.makeText(getActivity(),"No data Found",Toast.LENGTH_SHORT).show();

        }
        else {
            ProjectAdpater1.filteredlist(filterelist);
        }
    }
    @Override

    public int onItemClicked(MissingDetails model) {

        Intent intent = new Intent(getActivity(), SingleUserProfile1.class);
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
        getActivity().startActivity(intent);


        return 0;
    }
}