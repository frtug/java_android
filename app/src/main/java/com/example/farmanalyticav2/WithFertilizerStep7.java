package com.example.farmanalyticav2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.farmanalyticav2.databinding.FragmentWithFertilizerStep6Binding;
import com.example.farmanalyticav2.databinding.FragmentWithFertilizerStep7Binding;
import com.example.farmanalyticav2.databinding.FragmentWithoutFertilizerStep5Binding;

public class WithFertilizerStep7 extends Fragment {

    FragmentWithFertilizerStep7Binding binding;

    Button backToLandingAfterWF;

    Button backtoWFS6;

    public WithFertilizerStep7() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithFertilizerStep7Binding.inflate(inflater, container, false);

        backToLandingAfterWF = binding.getRoot().findViewById(R.id.backToLandingAfterWF);
        binding.backToLandingAfterWF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home Home = new Home();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, Home);
                fragmentTransaction.commit();
            }
        });

        backtoWFS6 = binding.getRoot().findViewById(R.id.backtoWFS6);
        binding.backtoWFS6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithFertilizerStep6 WithFertilizerStep6 = new WithFertilizerStep6();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithFertilizerStep6);
                fragmentTransaction.commit();
            }
        });

        return binding.getRoot();
    }
}