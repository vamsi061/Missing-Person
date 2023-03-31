package com.mperson.missingchild;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class pbprofilefragment extends Fragment {
    private Button mpage,logout;
    private Button btn2,btn3;
    private TextView police;
    FirebaseUser user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pbprofilefragment, container, false);
        mpage=v.findViewById(R.id.btn1);
        btn2=v.findViewById(R.id.btn2);
        btn3=v.findViewById(R.id.btn3);
        police=v.findViewById(R.id.police);
        user= FirebaseAuth.getInstance().getCurrentUser();
        police.setText(user.getEmail());

        mpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),pbviewprofile.class);
                startActivity(intent);
            }
        });



        logout = v.findViewById(R.id.btn4);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(),"Logged Out Successfully ",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Change Password",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),chng_pwd1.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Language",Toast.LENGTH_SHORT).show();
                showLangDialog();
            }
        });
        return v;
    }

    private void showLangDialog() {
        final String languages[] = {"English", "Telugu", "Hindi"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    setLocale("");
                    getActivity().recreate();
                } else if (which == 1) {
                    setLocale("te");
                    getActivity().recreate();
                } else if (which == 2) {
                    setLocale("hi");
                    getActivity().recreate();
                }

            }
        });
        mBuilder.create();
        mBuilder.show();
    }
    private void setLocale(String Language) {
        Locale locale = new Locale(Language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getActivity().getBaseContext().getResources().updateConfiguration(configuration, getActivity().getBaseContext()
                .getResources().getDisplayMetrics());

    }
}