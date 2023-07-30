package com.example.farmanalyticav2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmanalyticav2.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Fragment {

    FragmentProfileBinding binding;
    ProgressBar progressBar;
    TextView actualEmail, actualFullName, actualMobileNumber, actualProvince, actualMunicipality, actualFarmArea, actualSoilType;
    Button buttonToDisplayProfileInformation;

    DatabaseReference reference;
    FirebaseAuth mAuth;


    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        // Find views by their IDs
        actualEmail = view.findViewById(R.id.actualEmail);
        actualFullName = view.findViewById(R.id.actualFullName);
        actualMobileNumber = view.findViewById(R.id.actualMobileNumber);
        actualProvince = view.findViewById(R.id.actualProvince);
        actualMunicipality = view.findViewById(R.id.actualMunicipality);
        actualFarmArea = view.findViewById(R.id.actualFarmArea);
        actualSoilType = view.findViewById(R.id.actualSoilType);
        buttonToDisplayProfileInformation = view.findViewById(R.id.buttonToDisplayProfileInformation);

        binding.buttonToDisplayProfileInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uniqueDisplayName = binding.myFullName.getText().toString();
                fetchUserInformation(uniqueDisplayName);
            }
        });

        binding.buttonToEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfile EditProfile = new EditProfile();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, EditProfile);
                fragmentTransaction.commit();
            }
        });



        binding.buttonToLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingIndicator();
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(), "Successfully logged out!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().finish(); // Optional: Close the current activity to prevent the user from going back
                hideLoadingIndicator();

                // Show the bottom sheet dialog
                //showBottomSheetDialogLogout();
            }
        });

        return view;
    }



    private void showLoadingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoadingIndicator() {
        progressBar.setVisibility(View.GONE);
    }

    //private void showBottomSheetDialogLogout() {
       // View view = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_logoutsuccess, null);
       // BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
      //  dialog.setContentView(view);

     //   Button navigateBackToLogin = dialog.findViewById(R.id.navigateBackToLogin);
     //   navigateBackToLogin.setOnClickListener(new View.OnClickListener() {
           // @Override
         //   public void onClick(View v) {

           // }
       // });

      //  dialog.show();


  //  }

    private void fetchUserInformation(String uniqueDisplayName) {
        reference = FirebaseDatabase.getInstance().getReference("users");
        reference.child(uniqueDisplayName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(getActivity(), "Data is successfully read!", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String email = String.valueOf(dataSnapshot.child("email").getValue());
                        String fullName = String.valueOf(dataSnapshot.child("fullName").getValue());
                        String mobileNumber = String.valueOf(dataSnapshot.child("mobileNumber").getValue());
                        String state_name = String.valueOf(dataSnapshot.child("stateName").getValue());
                        String district_name = String.valueOf(dataSnapshot.child("districtName").getValue());
                        String areaFarm = String.valueOf(dataSnapshot.child("areaFarm").getValue());
                        String soilType = String.valueOf(dataSnapshot.child("soilType").getValue());

                        binding.actualEmail.setText(email);
                        binding.actualFullName.setText(fullName);
                        binding.actualMobileNumber.setText(mobileNumber);
                        binding.actualProvince.setText(state_name);
                        binding.actualMunicipality.setText(district_name);
                        binding.actualFarmArea.setText(areaFarm);
                        binding.actualSoilType.setText(soilType);
                    }
                } else {
                    Toast.makeText(getActivity(), "Oh no, this user doesn't exist!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}






