package com.example.farmanalyticav2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.farmanalyticav2.databinding.FragmentWithFertilizerStep2Binding;
import com.example.farmanalyticav2.databinding.FragmentWithFertilizerStep3Binding;

public class WithFertilizerStep3 extends Fragment {

    FragmentWithFertilizerStep3Binding binding;
    Button nextWFS4, backtoWFS2;

    public WithFertilizerStep3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithFertilizerStep3Binding.inflate(inflater, container, false);

        nextWFS4 = binding.getRoot().findViewById(R.id.nextWFS4);
        binding.nextWFS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithFertilizerStep4 WithFertilizerStep4 = new WithFertilizerStep4();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithFertilizerStep4);
                fragmentTransaction.commit();
            }
        });

        backtoWFS2 = binding.getRoot().findViewById(R.id.backtoWFS2);
        binding.backtoWFS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithFertilizerStep2 WithFertilizerStep2 = new WithFertilizerStep2();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithFertilizerStep2);
                fragmentTransaction.commit();
            }
        });

        return binding.getRoot();
    }
}