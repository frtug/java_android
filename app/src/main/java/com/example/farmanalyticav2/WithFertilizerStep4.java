package com.example.farmanalyticav2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.farmanalyticav2.databinding.FragmentWithFertilizerStep4Binding;
import com.example.farmanalyticav2.databinding.FragmentWithoutFertilizerStep4Binding;

public class WithFertilizerStep4 extends Fragment {

    FragmentWithFertilizerStep4Binding binding;
    Button nextWFS5;

    Button backtoWFS3;

    Spinner soilTypeDropdown;

    LinearLayout soilTypeContainer;

    CheckBox soilTypeCheckbox;
    public WithFertilizerStep4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithFertilizerStep4Binding.inflate(inflater, container, false);

        // Find views by their IDs
        nextWFS5 = binding.getRoot().findViewById(R.id.nextWFS5);
        binding.nextWFS5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithoutFertilizerStep5 WithoutFertilizerStep5 = new WithoutFertilizerStep5();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithoutFertilizerStep5);
                fragmentTransaction.commit();
            }
        });

        backtoWFS3 = binding.getRoot().findViewById(R.id.backtoWFS3);
        binding.backtoWFS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithFertilizerStep3 WithFertilizerStep3 = new WithFertilizerStep3();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithFertilizerStep3);
                fragmentTransaction.commit();
            }
        });

        soilTypeContainer = binding.getRoot().findViewById(R.id.soilTypeContainer);
        soilTypeCheckbox = binding.getRoot().findViewById(R.id.soilTypeCheckbox);
        // Set click listener for "Use Current Location" button
        soilTypeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                soilTypeContainer.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });

        soilTypeDropdown = binding.getRoot().findViewById(R.id.soilTypeDropdown);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.season_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        soilTypeDropdown.setAdapter(adapter);

        soilTypeDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSoilType = parent.getItemAtPosition(position).toString();
                // Perform actions based on the selected season
                if (selectedSoilType.equals("Choose Soil Type")) {
                    // Handle "Choose Season" selection
                    // For example, display a toast message
                } else if (selectedSoilType.equals("Loamy")) {
                    // Handle "Dry" selection
                    // For example, update the selectedSeason variable or call a method
                } else if (selectedSoilType.equals("Clayey")) {
                    // Handle "Wet" selection
                    // For example, update the selectedSeason variable or call a method
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case when no item is selected
            }
        });


        return binding.getRoot();
    }
}