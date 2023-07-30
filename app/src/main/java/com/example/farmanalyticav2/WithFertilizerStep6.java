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

import com.example.farmanalyticav2.databinding.FragmentWithFertilizerStep6Binding;
import com.example.farmanalyticav2.databinding.FragmentWithoutFertilizerStep4Binding;

public class WithFertilizerStep6 extends Fragment {

    FragmentWithFertilizerStep6Binding binding;

    Button nextWFS7;

    Button backtoWFS5;

    Spinner seasonDropdownWF;

    LinearLayout seasonContainerWF;

    CheckBox seasonCheckboxWF;

    public WithFertilizerStep6() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithFertilizerStep6Binding.inflate(inflater, container, false);

        // Find views by their IDs
        nextWFS7 = binding.getRoot().findViewById(R.id.nextWFS7);
        binding.nextWFS7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithFertilizerStep7 WithFertilizerStep7 = new WithFertilizerStep7();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithFertilizerStep7);
                fragmentTransaction.commit();
            }
        });

        backtoWFS5 = binding.getRoot().findViewById(R.id.backtoWFS5);
        binding.backtoWFS5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithFertilizerStep5 WithFertilizerStep5 = new WithFertilizerStep5();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithFertilizerStep5);
                fragmentTransaction.commit();
            }
        });

        seasonContainerWF = binding.getRoot().findViewById(R.id.seasonContainerWF);
        seasonCheckboxWF = binding.getRoot().findViewById(R.id.seasonCheckboxWF);
        // Set click listener for "Use Current Location" button
        seasonCheckboxWF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                seasonContainerWF.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });

        seasonDropdownWF = binding.getRoot().findViewById(R.id.seasonDropdownWF);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.season_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seasonDropdownWF.setAdapter(adapter);

        seasonDropdownWF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSeason = parent.getItemAtPosition(position).toString();
                // Perform actions based on the selected season
                if (selectedSeason.equals("Choose Season")) {
                    // Handle "Choose Season" selection
                    // For example, display a toast message
                } else if (selectedSeason.equals("Dry")) {
                    // Handle "Dry" selection
                    // For example, update the selectedSeason variable or call a method
                } else if (selectedSeason.equals("Wet")) {
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