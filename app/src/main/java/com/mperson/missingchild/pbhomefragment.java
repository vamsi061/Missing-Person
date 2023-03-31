package com.mperson.missingchild;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class pbhomefragment extends Fragment {

    private Button about_us,help;
    private Dialog dialog,dialog1;
    private  Button btn11,btn21;
    private Button btn12,btn22;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pbhomefragment, container, false);
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(getActivity(),R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        dialog1 = new Dialog(getActivity());
        dialog1.setContentView(R.layout.custom_dialog1_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog1.getWindow().setBackgroundDrawable(getDrawable(getActivity(),R.drawable.custom_dialog1_background));
        }
        dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.setCancelable(false); //Optional
        dialog1.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        //ImageCarousel carousel = v.findViewById(R.id.carousel23);

        Button Okay = dialog.findViewById(R.id.btn_okay);
        Button Cancel = dialog.findViewById(R.id.btn_cancel);
        Button Okay1 = dialog1.findViewById(R.id.btn_okay);
        Button Cancel1 = dialog1.findViewById(R.id.btn_cancel);
        about_us = v.findViewById(R.id.about_us);

        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Okay",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Okay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Okay",Toast.LENGTH_SHORT).show();
                dialog1.dismiss();
            }
        });

        Cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });


        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show(); // Showing the dialog here
            }
        });

       // ImageCarousel carousel = v.findViewById(R.id.carousel);

        /*carousel.registerLifecycle(getLifecycle());
        List<CarouselItem> list = new ArrayList<>();

        list.add(new CarouselItem(R.drawable.modi));
        list.add(new CarouselItem("https://i.ytimg.com/vi/ZAldf56YM4c/maxresdefault.jpg"));
        list.add(new CarouselItem("https://cachar.gov.in/sites/default/files/swf_utility_folder/departments/cachar_epr_amtron_in_oid_2/this_comm/childline%20(1).jpg"));
        carousel.setData(list);*/

        btn11 = v.findViewById(R.id.btn12);
        btn21 = v.findViewById(R.id.kids);
        btn12 = v.findViewById(R.id.btn12);

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SearchFragment1();
            }
        });

        about_us = v.findViewById(R.id.about_us);
        help = v.findViewById(R.id.help);

        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.show();
            }
        });

        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new SearchFragment1();
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn12)
                {
                    Toast.makeText(getActivity(),"Enter Location to search",Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    String loca = "5";
                    bundle.putString("location",loca);
                    SearchFragment1 searchFragment = new SearchFragment1();
                    searchFragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.body_container,searchFragment).commit();
                }
            }
        });

        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.kids)
                {
                    Toast.makeText(getActivity(),"Enter kid Age to search",Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    String kids = "5";
                    bundle.putString("kids",kids);
                    SearchFragment searchFragment = new SearchFragment();
                    searchFragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.body_container,searchFragment).commit();
                }
            }
        });



        return  v;
    }
}