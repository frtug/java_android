package com.example.farmanalyticav2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.farmanalyticav2.databinding.FragmentApplicationBinding;

public class Application extends Fragment {

    FragmentApplicationBinding binding;

    public Application() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentApplicationBinding.inflate(inflater, container, false);

        binding.approachOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WithFertilizerStep1 WithFertilizerStep1 = new WithFertilizerStep1();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithFertilizerStep1);
                fragmentTransaction.commit();
            }
        });

        binding.approachTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WithoutFertilizerStep1 WithoutFertilizerStep1 = new WithoutFertilizerStep1();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithoutFertilizerStep1);
                fragmentTransaction.commit();
            }
        });

        binding.schedulePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatingSchedule CreatingSchedule = new CreatingSchedule();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, CreatingSchedule);
                fragmentTransaction.commit();
            }
        });
        return binding.getRoot();
    }
}