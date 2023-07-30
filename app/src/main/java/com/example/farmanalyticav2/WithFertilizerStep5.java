package com.example.farmanalyticav2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.farmanalyticav2.databinding.FragmentWithFertilizerStep5Binding;
import com.example.farmanalyticav2.databinding.FragmentWithoutFertilizerStep3Binding;

public class WithFertilizerStep5 extends Fragment {

    FragmentWithFertilizerStep5Binding binding;

    Button nextWFS6;

    Button backtoWFS4;

    CheckBox areaCheckboxWF;

    EditText areaInSqM;

    LinearLayout areaContainerWF;

    public WithFertilizerStep5() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithFertilizerStep5Binding.inflate(inflater, container, false);

        // Find views by their IDs
        nextWFS6 = binding.getRoot().findViewById(R.id.nextWFS6);
        binding.nextWFS6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithFertilizerStep6 WithFertilizerStep6 = new WithFertilizerStep6();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithFertilizerStep6);
                fragmentTransaction.commit();
            }
        });

        backtoWFS4 = binding.getRoot().findViewById(R.id.backtoWFS4);

        // Set on click listener for "Next" button
        binding.backtoWFS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithFertilizerStep4 WithFertilizerStep4 = new WithFertilizerStep4();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithFertilizerStep4);
                fragmentTransaction.commit();
            }
        });

        areaContainerWF = binding.getRoot().findViewById(R.id.areaContainerWF);
        areaCheckboxWF = binding.getRoot().findViewById(R.id.areaCheckboxWF);
        // Set click listener for "Use Current Location" button
        areaCheckboxWF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                areaContainerWF.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });

        return binding.getRoot();
    }
}