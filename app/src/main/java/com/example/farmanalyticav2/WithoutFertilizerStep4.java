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
import android.widget.Toast;

import com.example.farmanalyticav2.databinding.FragmentWithoutFertilizerStep4Binding;

public class WithoutFertilizerStep4 extends Fragment {

    FragmentWithoutFertilizerStep4Binding binding;

    Button nextWOFS5;

    Button backtoWOFS3;

    Spinner seasonDropdown;

    LinearLayout seasonContainer;

    CheckBox seasonCheckbox;

    TextView showSeason;

    public WithoutFertilizerStep4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithoutFertilizerStep4Binding.inflate(inflater, container, false);

        // Find views by their IDs
        nextWOFS5 = binding.getRoot().findViewById(R.id.nextWOFS5);
        binding.nextWOFS5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithoutFertilizerStep5 WithoutFertilizerStep5 = new WithoutFertilizerStep5();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithoutFertilizerStep5);
                fragmentTransaction.commit();
            }
        });

        backtoWOFS3 = binding.getRoot().findViewById(R.id.backtoWOFS3);

        // Set on click listener for "Next" button
        binding.backtoWOFS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithoutFertilizerStep3 WithoutFertilizerStep3 = new WithoutFertilizerStep3();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithoutFertilizerStep3);
                fragmentTransaction.commit();
            }
        });

        seasonContainer = binding.getRoot().findViewById(R.id.seasonContainer);
        showSeason = binding.getRoot().findViewById(R.id.showSeason);
        seasonCheckbox = binding.getRoot().findViewById(R.id.seasonCheckbox);
        // Set click listener for "Use Current Location" button
        seasonCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                seasonContainer.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });

        seasonDropdown = binding.getRoot().findViewById(R.id.seasonDropdown);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.season_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seasonDropdown.setAdapter(adapter);

        seasonDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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