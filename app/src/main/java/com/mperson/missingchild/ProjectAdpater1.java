package com.mperson.missingchild;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public  class ProjectAdpater1 extends  RecyclerView.Adapter<ProjectAdpater1.ViewHolder>{
    List<MissingDetails> list;
    Context context1;
    selectedListeners listener;

    public ProjectAdpater1(Context context, List<MissingDetails> list, selectedListeners listener) {
        this.list = list;
        this.context1 = context;
        this.listener=listener;
       /* this.newArrayListfull = list;
        this.list = new ArrayList<>(newArrayListfull);*/
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context1).inflate(R.layout.activity_items1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MissingDetails model=list.get(position);

        Picasso.get().load(model.getImage()).placeholder(R.drawable.avatar).into(holder.imageView);
        holder.Name.setText(model.getName());
        holder.Age.setText(model.getAge());
        holder.Location.setText(model.getLocation());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(context1,SingleUserProfile1.class);
                intent1.putExtra("SingleImg",model.getImage());
                intent1.putExtra("Mperson",model.getName());
                intent1.putExtra("Mage",model.getAge());
                intent1.putExtra("Mheight",model.getHeight());
                intent1.putExtra("Mloc",model.getLocation());
                intent1.putExtra("MtypePerson",model.getType_of_Person());
                intent1.putExtra("Mname",model.getAlias_Name());
                intent1.putExtra("Mcomp",model.getComplexion());
                intent1.putExtra("Mface",model.getFace_Type());
                intent1.putExtra("Meye",model.getEye_Type());
                intent1.putExtra("cdate",model.getFir_Date());
                intent1.putExtra("firno",model.getFir_Number());
                intent1.putExtra("Cname",model.getContact_Per_Name());
                intent1.putExtra("relation",model.getRelationship_Type());
                intent1.putExtra("cphone",model.getContact_Phone_Number());
                intent1.putExtra("pstation",model.getPstation());
                intent1.putExtra("position",model.getClass());
                intent1.putExtra("UID",model.getUID());
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context1.startActivity(intent1);



            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


   public  void filteredlist(List<MissingDetails> filteredList) {
        list = (ArrayList<MissingDetails>) filteredList;
        notifyDataSetChanged();

    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView Name, Age,Location;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.name2);
            Age = itemView.findViewById(R.id.age2);
            Location = itemView.findViewById(R.id.loc1);
            imageView = itemView.findViewById(R.id.img2);

        }
    }
}
