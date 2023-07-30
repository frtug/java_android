package com.example.farmanalyticav2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.farmanalyticav2.databinding.FragmentApplicationBinding;
import com.example.farmanalyticav2.databinding.FragmentCreatingScheduleBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreatingSchedule extends Fragment {

    EditText chooseDate;
    Calendar calendar;
    FragmentCreatingScheduleBinding binding;

    Button createSched, backtoAppCS;

    public CreatingSchedule() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCreatingScheduleBinding.inflate(inflater, container, false);


        createSched = binding.getRoot().findViewById(R.id.createSched);
        binding.createSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the bottom sheet dialog
                showBottomSheetDialogCreateSchedule();
            }
        });

        backtoAppCS = binding.getRoot().findViewById(R.id.backtoAppCS);
        binding.backtoAppCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application Application = new Application();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, Application);
                fragmentTransaction.commit();
            }
        });


        chooseDate = binding.getRoot().findViewById(R.id.chooseDate);

        // Initialize the calendar
        calendar = Calendar.getInstance();

        // Set an OnClickListener for the EditText field
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the DatePickerDialog when the EditText is clicked
                showDatePicker();
            }
        });

        return binding.getRoot();
    }

    private void showDatePicker() {
        // Get the current date from the calendar
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog and set the current date as the initial selection
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the calendar with the selected date
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Update the EditText field with the selected date
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String selectedDate = dateFormat.format(calendar.getTime());
                        chooseDate.setText(selectedDate);
                    }
                },
                year,
                month,
                dayOfMonth
        );

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    private void showBottomSheetDialogCreateSchedule() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_createschedsuccess, null);
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(view);

        Button backToLandingFromCS = dialog.findViewById(R.id.backToLandingFromCS);
        backToLandingFromCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), landing.class);
                startActivity(intent);
            }
        });

        dialog.show();
    }
}