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

public  class ProjectAdpater extends  RecyclerView.Adapter<ProjectAdpater.ViewHolder>{
    List<MissingDetails> list;
    Context context;
    selectedListeners listener;

    public ProjectAdpater(Context context, List<MissingDetails> list, selectedListeners listener) {
        this.list = list;
        this.context = context;
        this.listener=listener;
       /* this.newArrayListfull = list;
        this.list = new ArrayList<>(newArrayListfull);*/
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_items,parent,false);
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
                listener.onItemClicked(list.get(position));
               Intent intent = new Intent(context,SingleUserProfile.class);
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
                intent.putExtra("pstation",model.getPstation());
                intent.putExtra("position",model.getClass());
                intent.putExtra("UID",model.getUID());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

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
