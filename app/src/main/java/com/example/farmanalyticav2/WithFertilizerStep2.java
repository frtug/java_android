package com.example.farmanalyticav2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.farmanalyticav2.databinding.FragmentWithFertilizerStep1Binding;
import com.example.farmanalyticav2.databinding.FragmentWithFertilizerStep2Binding;

public class WithFertilizerStep2 extends Fragment {

    FragmentWithFertilizerStep2Binding binding;
    Button nextWFS3, backtoWFS1;

    public WithFertilizerStep2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithFertilizerStep2Binding.inflate(inflater, container, false);

        nextWFS3 = binding.getRoot().findViewById(R.id.nextWFS3);
        binding.nextWFS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithFertilizerStep3 WithFertilizerStep3 = new WithFertilizerStep3();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithFertilizerStep3);
                fragmentTransaction.commit();
            }
        });

        backtoWFS1 = binding.getRoot().findViewById(R.id.backtoWFS1);
        binding.backtoWFS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithFertilizerStep1 WithFertilizerStep1 = new WithFertilizerStep1();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithFertilizerStep1);
                fragmentTransaction.commit();
            }
        });

        return binding.getRoot();
    }
}