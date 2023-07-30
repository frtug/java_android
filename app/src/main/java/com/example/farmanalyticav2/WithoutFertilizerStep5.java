package com.example.farmanalyticav2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.farmanalyticav2.databinding.FragmentWithoutFertilizerStep5Binding;
import com.example.farmanalyticav2.databinding.FragmentWithoutFertilizerStep5Binding;
import com.example.farmanalyticav2.databinding.FragmentWithoutFertilizerStep5Binding;

public class WithoutFertilizerStep5 extends Fragment {

    FragmentWithoutFertilizerStep5Binding binding;

    Button backToLandingAfterSummary;

    Button theBackArrow;


    public WithoutFertilizerStep5() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithoutFertilizerStep5Binding.inflate(inflater, container, false);
        View view = binding.getRoot();

        backToLandingAfterSummary = view.findViewById(R.id.backToLandingAfterSummary);
        binding.backToLandingAfterSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home Home = new Home();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, Home);
                fragmentTransaction.commit();
            }
        });

        theBackArrow = view.findViewById(R.id.theBackArrow);
        binding.theBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithoutFertilizerStep4 WithoutFertilizerStep4 = new WithoutFertilizerStep4();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithoutFertilizerStep4);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}