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

import com.example.farmanalyticav2.databinding.FragmentWithoutFertilizerStep3Binding;

public class WithoutFertilizerStep3 extends Fragment {

    FragmentWithoutFertilizerStep3Binding binding;

    Button nextWOFS3;

    Button backtoWOFS2;

    CheckBox areaCheckbox;

    EditText areaInSqM;

    LinearLayout areaContainer;

    public WithoutFertilizerStep3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithoutFertilizerStep3Binding.inflate(inflater, container, false);

        // Find views by their IDs
        nextWOFS3 = binding.getRoot().findViewById(R.id.nextWOFS3);
        binding.nextWOFS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithoutFertilizerStep4 WithoutFertilizerStep4 = new WithoutFertilizerStep4();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithoutFertilizerStep4);
                fragmentTransaction.commit();
            }
        });

        backtoWOFS2 = binding.getRoot().findViewById(R.id.backtoWOFS2);

        // Set on click listener for "Next" button
        binding.backtoWOFS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithoutFertilizerStep2 WithoutFertilizerStep2 = new WithoutFertilizerStep2();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithoutFertilizerStep2);
                fragmentTransaction.commit();
            }
        });

        areaContainer = binding.getRoot().findViewById(R.id.areaContainer);
        areaCheckbox = binding.getRoot().findViewById(R.id.areaCheckbox);
        // Set click listener for "Use Current Location" button
        areaCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                areaContainer.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });

        return binding.getRoot();
    }
}