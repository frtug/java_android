package com.example.farmanalyticav2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.farmanalyticav2.databinding.FragmentDataInputBinding;
import com.example.farmanalyticav2.databinding.FragmentDataInputBinding;

public class DataInput extends Fragment {

    FragmentDataInputBinding binding;

    Button afterEnterGoToHome;

    public DataInput() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDataInputBinding.inflate(inflater, container, false);

        // Find views by their IDs
        afterEnterGoToHome = binding.getRoot().findViewById(R.id.afterEnterGoToHome);

        // Set on click listener for "Next" button
        binding.afterEnterGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home Home = new Home();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, Home);
                fragmentTransaction.commit();
            }
        });
        return binding.getRoot();
    }
}