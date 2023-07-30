package com.example.farmanalyticav2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.farmanalyticav2.databinding.FragmentApplicationBinding;
import com.example.farmanalyticav2.databinding.FragmentCropsBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class Crops extends Fragment {

    FragmentCropsBinding binding;

    Button openRiceDialog, openSweetPotatoDialog, openPotatoDialog, openTomatoDialog, openStringbeansDialog, openCornDialog;

    public Crops() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCropsBinding.inflate(inflater, container, false);

        openRiceDialog = binding.getRoot().findViewById(R.id.openRiceDialog);
        openRiceDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialogRice();
            }
        });

        openSweetPotatoDialog = binding.getRoot().findViewById(R.id.openSweetPotatoDialog);
        openSweetPotatoDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialogSweetPotato();
            }
        });

        openPotatoDialog = binding.getRoot().findViewById(R.id.openPotatoDialog);
        openPotatoDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialogPotato();
            }
        });

        openTomatoDialog = binding.getRoot().findViewById(R.id.openTomatoDialog);
        openTomatoDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialogTomato();
            }
        });

        openStringbeansDialog = binding.getRoot().findViewById(R.id.openStringbeansDialog);
        openStringbeansDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialogStringbeans();
            }
        });

        openCornDialog = binding.getRoot().findViewById(R.id.openCornDialog);
        openCornDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialogCorn();
            }
        });

        return binding.getRoot();
    }

    private void showBottomSheetDialogRice() {
        View view = getLayoutInflater().inflate(R.layout.bottom_dialog_layout_rice, null);
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(view);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button doneRice = view.findViewById(R.id.doneRice);
        doneRice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog when the "done" button is clicked
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showBottomSheetDialogSweetPotato() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_sweetpotato, null);
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(view);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button doneSweetPotato = view.findViewById(R.id.doneSweetPotato);
        doneSweetPotato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog when the "done" button is clicked
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showBottomSheetDialogPotato() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_potato, null);
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(view);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button donePotato = view.findViewById(R.id.donePotato);
        donePotato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog when the "done" button is clicked
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showBottomSheetDialogTomato() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_tomato, null);
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(view);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button doneTomato = view.findViewById(R.id.doneTomato);
        doneTomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog when the "done" button is clicked
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showBottomSheetDialogStringbeans() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_stringbeans, null);
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(view);

        Button doneStringbeans = view.findViewById(R.id.doneStringbeans);
        doneStringbeans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog when the "done" button is clicked
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showBottomSheetDialogCorn() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_corn, null);
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(view);

        Button doneCorn = view.findViewById(R.id.doneCorn);
        doneCorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog when the "done" button is clicked
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}