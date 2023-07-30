package com.example.farmanalyticav2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.farmanalyticav2.databinding.FragmentWithoutFertilizerStep2Binding;

public class WithoutFertilizerStep2 extends Fragment {

    FragmentWithoutFertilizerStep2Binding binding;

    Button buttonToNextStep;

    Button theBackArrow;

    public WithoutFertilizerStep2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithoutFertilizerStep2Binding.inflate(inflater, container, false);

        // Find views by their IDs
        buttonToNextStep = binding.getRoot().findViewById(R.id.buttonToNextStep);

        theBackArrow = binding.getRoot().findViewById(R.id.theBackArrow);

        // Set on click listener for "Next" button
        binding.buttonToNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithoutFertilizerStep3 WithoutFertilizerStep3 = new WithoutFertilizerStep3();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithoutFertilizerStep3);
                fragmentTransaction.commit();
            }
        });

        binding.theBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithoutFertilizerStep1 WithoutFertilizerStep1 = new WithoutFertilizerStep1();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithoutFertilizerStep1);
                fragmentTransaction.commit();
            }
        });

        return binding.getRoot();
    }
}