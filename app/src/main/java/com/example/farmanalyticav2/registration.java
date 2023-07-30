package com.example.farmanalyticav2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class registration extends AppCompatActivity {

    private static final int MIN_PASSWORD_LENGTH = 8;

    //For Back to Login button
    Button backToLogin;
    // For registration form

    private ProgressBar progressBar;

    Button registerInformation;
    TextInputEditText editTextEmail, editTextFullName, editTextMobileNumber, editTextPassword, editTextConfirmPassword, editAreaFarm, editSoilType;

    TextView requiredEmail, validEmail, requiredFullName, alphaFullName, requiredMobileNum, numMobileNum, requiredProvince, requiredMunicipality, requiredFarmArea, requiredSoilType, requiredPassword, minimumPassword, requiredConfirmPassword, matchConfirmPassword;

    FirebaseDatabase database;
    DatabaseReference reference;
    users users;

    // For dropdown menu
    String selectedProvince, selectedMunicipality;
    Spinner provinceSpinner, municipalitySpinner;
    ArrayAdapter<CharSequence> provinceAdapter, municipalityAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Adjust the window resize mode to make room for the keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_registration);

        //For "Register" button
        editTextEmail = (TextInputEditText) findViewById(R.id.email);
        editTextFullName = (TextInputEditText) findViewById(R.id.fullName);
        editTextMobileNumber = (TextInputEditText) findViewById(R.id.mobileNumber);
        editTextPassword = (TextInputEditText) findViewById(R.id.password);
        editTextConfirmPassword = (TextInputEditText) findViewById(R.id.confirmpassword);
        editAreaFarm = (TextInputEditText) findViewById(R.id.areaFarm);
        editSoilType = (TextInputEditText) findViewById(R.id.soilType);

        registerInformation = findViewById(R.id.registerInformation);

        requiredEmail = findViewById(R.id.requiredEmail);
        validEmail = findViewById(R.id.validEmail);
        requiredFullName = findViewById(R.id.requiredFullName);
        alphaFullName = findViewById(R.id.alphaFullName);
        requiredMobileNum = findViewById(R.id.requiredMobileNum);

        numMobileNum = findViewById(R.id.numMobileNum);
        requiredProvince = findViewById(R.id.requiredProvince);
        requiredMunicipality = findViewById(R.id.requiredMunicipality);
        requiredFarmArea = findViewById(R.id.requiredFarmArea);

        requiredSoilType = findViewById(R.id.requiredSoilType);
        requiredPassword = findViewById(R.id.requiredPassword);
        minimumPassword = findViewById(R.id.minimumPassword);
        requiredConfirmPassword = findViewById(R.id.requiredConfirmPassword);
        matchConfirmPassword = findViewById(R.id.matchConfirmPassword);

        //For "Register" button

        // For Province spinner
        provinceSpinner = findViewById(R.id.provinceSpinner);
        municipalitySpinner = findViewById(R.id.municipalitySpinner);

        provinceAdapter = ArrayAdapter.createFromResource(this, R.array.array_default_provinces, R.layout.region_spinner_layout);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(provinceAdapter);

        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {

                // For municipality spinner
                selectedProvince = provinceSpinner.getSelectedItem().toString();

                int parentIDProvince = parent.getId();
                if (parentIDProvince == R.id.provinceSpinner) {
                    switch (selectedProvince) {
                        case "Select your Province":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_default_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Ilocos Norte":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_ilocosnorte_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Ilocos Sur":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_ilocossur_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "La Union":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_launion_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Pangasinan":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_pangasinan_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Batanes":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_batanes_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Cagayan":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_cagayan_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Isabela":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_isabela_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Nueva Vizcaya":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_nuevavizcaya_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Quirino":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_quirino_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Aurora":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_aurora_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Bataan":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_bataan_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Bulacan":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_bulacan_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Pampanga":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_pampanga_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Tarlac":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_tarlac_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Zambales":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_zambales_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Cavite":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_cavite_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Laguna":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_laguna_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Batangas":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_batangas_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Rizal":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_rizal_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Quezon":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_quezon_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Mindoro Occidental":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_mindoroocc_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Mindoro Oriental":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_mindoroori_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Marinduque":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_marinduque_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Romblon":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_romblon_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Palawan":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_palawan_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Albay":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_albay_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Camarines Norte":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_camnorte_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Camarines Sur":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_camsur_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Catanduanes":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_catanduanes_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Masbate":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_masbate_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Sorsogon":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_sorsogon_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Aklan":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_aklan_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Antique":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_antique_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Capiz":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_capiz_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Iloilo":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_iloilo_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Guimaras":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_guimaras_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Negros Occidental":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_negocci_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Bohol":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_bohol_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Cebu":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_cebu_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Negros Oriental":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_negori_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Siquijor":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_siquijor_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Biliran":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_biliran_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Eastern Samar":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_eastsamar_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Leyte":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_leyte_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Northern Samar":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_northsamar_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Samar Province (Western)":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_westsamar_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Southern Leyte":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_southleyte_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Zamboanga del Norte":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_zamnorte_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Zamboanga del Sur":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_zamsur_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Zamboanga Sibugay":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_zamsibugay_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Bukidnon":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_bukidnon_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Camiguin":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_camiguin_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Lanao del Norte":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_lanaodelnorte_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Misamis Occidental":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_misamisocci_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Compostela Valley":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_compostelavalley_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Davao del Norte":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_davaonorte_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Davao del Sur":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_davaosur_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Davao Occidental":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_davaoocci_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Davao Oriental":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_davaoori_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "South Cotabato":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_southcotabato_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Cotabato":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_cotabato_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Sultan Kudarat":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_sultankudarat_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Sarangani":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_sarangani_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Agusan del Norte":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_agusannorte_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Agusan del Sur":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_agusansur_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Surigao del Norte":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_surigaonorte_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Surigao del Sur":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_surigaosur_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Dinagat Island":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_dinagat_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Metro Manila (Administrative Region)":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_metromanila_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Abra":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_abra_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Apayao":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_apayao_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Benguet":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_benguet_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Ifugao":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_ifugao_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Kalinga-Apayao":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_kalingaapayao_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Mountain Province":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_mountainprovince_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Basilan":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_basilan_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Lanao del Sur":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_lanaonorte_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Maguindanao":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_maguindanao_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Sulu":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_sulu_municipalities, R.layout.region_spinner_layout);
                            break;
                        case "Tawi-Tawi":
                            municipalityAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_tawitawi_municipalities, R.layout.region_spinner_layout);
                            break;
                    }
                }

                municipalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                municipalitySpinner.setAdapter(municipalityAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // For registration and validations .............................................................................................................................

        registerInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference().child("users");

                // get data
                String email = editTextEmail.getText().toString().trim();
                String fullName = editTextFullName.getText().toString().trim();
                String mobileNumber = editTextMobileNumber.getText().toString().trim();
                String state_name = provinceSpinner.getSelectedItem().toString().trim();
                String district_name = municipalitySpinner.getSelectedItem().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confPassword = editTextConfirmPassword.getText().toString().trim();
                String areaFarm = editAreaFarm.getText().toString().trim();
                String soilType = editSoilType.getText().toString().trim();
                String userId = fullName; // Use the firstName as the unique ID

                users users = new users(email, fullName, mobileNumber, state_name, district_name, password, confPassword, areaFarm, soilType);

                // ...

                if (!validateEmail(email)) {
                    return;
                }

                if (!validateFullName(fullName)) {
                    return;
                }

                if (!validateMobileNumber(mobileNumber)) {
                    return;
                }

                if (!validateStateName(state_name)) {
                    return;
                }

                if (!validateDistrictName(district_name)) {
                    return;
                }

                if (!validateAreaFarm(areaFarm)) {
                    return;
                }

                if (!validateSoilType(soilType)) {
                    return;
                }

                if (!validatePassword(password)) {
                    return;
                }

                if (!validateConfirmPassword(password, confPassword)) {
                    return;
                }

                // ...

                else {
                    // Show the ProgressBar
                    showProgressBar();

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(registration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // Hide the ProgressBar
                                    hideProgressBar();

                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                        // send email verification
                                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> verificationTask) {
                                                if (verificationTask.isSuccessful()) {
                                                    //User registered and verification email sent
                                                    reference.child(userId).setValue(users);

                                                    // Show the bottom sheet dialog
                                                    showBottomSheetDialogRegistration();

                                                } else {
                                                    // failed to send verification email
                                                    Toast.makeText(registration.this, "Failed to send verification email.", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                    } else {
                                        // registration failed
                                        Toast.makeText(registration.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        // For registration and validations

        //For "Back to Login"
        backToLogin = findViewById(R.id.backToLogin);

        backToLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registration.this, Login.class);
                startActivity(intent);
            }
        });
        // For "Back to Login"
    }

    private void showProgressBar() {
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showBottomSheetDialogRegistration() {
        BottomSheetDialog dialog = new BottomSheetDialog(registration.this);
        dialog.setContentView(R.layout.bottom_sheet_dialog_registersuccess);

        Button navigateToLoginPage = dialog.findViewById(R.id.navigateToLoginPage);
        navigateToLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registration.this, Login.class);
                startActivity(intent);
            }
        });

        dialog.show();
    }

    private boolean isValidFullName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    private boolean isValidMobileNumber(String number) {
        return number.matches("\\d+");
    }


    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }


    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            editTextEmail.setText("");
            Toast.makeText(registration.this, "Please enter an email.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!isValidEmail(email)) {
            editTextEmail.setError("Invalid email");
            editTextEmail.requestFocus();
            editTextEmail.setText("");
            Toast.makeText(registration.this, "The email you entered is invalid.", Toast.LENGTH_SHORT).show();
            return false;
        }

        requiredEmail.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables = requiredEmail.getCompoundDrawables();
        Drawable leftDrawable = drawables[0];
        leftDrawable.setColorFilter(greenColorFilter);
        requiredEmail.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, drawables[1], drawables[2], drawables[3]);

        validEmail.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter2 = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables2 = validEmail.getCompoundDrawables();
        Drawable leftDrawable2 = drawables[0];
        leftDrawable2.setColorFilter(greenColorFilter2);
        validEmail.setCompoundDrawablesWithIntrinsicBounds(leftDrawable2, drawables2[1], drawables2[2], drawables2[3]);

        return true;
    }


    private boolean validateFullName(String fullName) {
        if (fullName.isEmpty()) {
            editTextFullName.setError("First name is required");
            editTextFullName.requestFocus();
            editTextFullName.setText("");
            Toast.makeText(registration.this, "Please enter your full name.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isValidFullName(fullName)) {
            editTextFullName.setError("Only alphabetic characters are allowed");
            editTextFullName.requestFocus();
            editTextFullName.setText("");
            Toast.makeText(registration.this, "The full name you entered is invalid.", Toast.LENGTH_SHORT).show();
            return false;
        }

        requiredFullName.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables = requiredFullName.getCompoundDrawables();
        Drawable leftDrawable = drawables[0];
        leftDrawable.setColorFilter(greenColorFilter);
        requiredFullName.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, drawables[1], drawables[2], drawables[3]);

        alphaFullName.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter2 = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables2 = alphaFullName.getCompoundDrawables();
        Drawable leftDrawable2 = drawables2[0];
        leftDrawable2.setColorFilter(greenColorFilter2);
        alphaFullName.setCompoundDrawablesWithIntrinsicBounds(leftDrawable2, drawables2[1], drawables2[2], drawables2[3]);

        return true;
    }


    private boolean validateMobileNumber(String mobileNumber) {
        if (mobileNumber.isEmpty()) {
            editTextMobileNumber.setError("Mobile Number is required");
            editTextMobileNumber.requestFocus();
            editTextMobileNumber.setText("");
            Toast.makeText(registration.this, "Please enter your mobile number.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isValidMobileNumber(mobileNumber)) {
            editTextMobileNumber.setError("Only numeric characters are allowed");
            editTextMobileNumber.requestFocus();
            editTextMobileNumber.setText("");
            Toast.makeText(registration.this, "The mobile number you entered is invalid.", Toast.LENGTH_SHORT).show();
            return false;
        }

        requiredMobileNum.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables = requiredMobileNum.getCompoundDrawables();
        Drawable leftDrawable = drawables[0];
        leftDrawable.setColorFilter(greenColorFilter);
        requiredMobileNum.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, drawables[1], drawables[2], drawables[3]);

        numMobileNum.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter2 = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables2 = numMobileNum.getCompoundDrawables();
        Drawable leftDrawable2 = drawables2[0];
        leftDrawable2.setColorFilter(greenColorFilter2);
        numMobileNum.setCompoundDrawablesWithIntrinsicBounds(leftDrawable2, drawables2[1], drawables2[2], drawables2[3]);

        return true;
    }


    private boolean validateStateName(String state_name) {
        if (state_name.equals("Select your Province")) {
            // Highlight the province spinner in red
            ((TextView) provinceSpinner.getSelectedView()).setError("Please select your Province.");
            provinceSpinner.requestFocus();
            Toast.makeText(registration.this, "Please select your province.", Toast.LENGTH_SHORT).show();
            return false;
        }

        requiredProvince.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables = requiredProvince.getCompoundDrawables();
        Drawable leftDrawable = drawables[0];
        leftDrawable.setColorFilter(greenColorFilter);
        requiredProvince.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, drawables[1], drawables[2], drawables[3]);

        return true;
    }


    private boolean validateDistrictName(String district_name) {
        if (district_name.equals("Select your Municipality")) {
            // Highlight the municipality spinner in red
            ((TextView) municipalitySpinner.getSelectedView()).setError("Please select your Municipality.");
            municipalitySpinner.requestFocus();
            Toast.makeText(registration.this, "Please select your district.", Toast.LENGTH_SHORT).show();
            return false;
        }

        requiredMunicipality.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables = requiredMunicipality.getCompoundDrawables();
        Drawable leftDrawable = drawables[0];
        leftDrawable.setColorFilter(greenColorFilter);
        requiredMunicipality.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, drawables[1], drawables[2], drawables[3]);
        // ...
        return true;
    }


    private boolean validateAreaFarm(String areaFarm) {

        if (areaFarm.isEmpty()) {
            editAreaFarm.setError("Farm Area is required.");
            editAreaFarm.requestFocus();
            editAreaFarm.setText("");
            Toast.makeText(registration.this, "Farm Area is required.", Toast.LENGTH_SHORT).show();
            return false;
        }

        requiredFarmArea.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables = requiredFarmArea.getCompoundDrawables();
        Drawable leftDrawable = drawables[0];
        leftDrawable.setColorFilter(greenColorFilter);
        requiredFarmArea.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, drawables[1], drawables[2], drawables[3]);

        return true;
    }


    private boolean validateSoilType(String soilType) {

        if (soilType.isEmpty()) {
            editSoilType.setError("Soil Type is required.");
            editSoilType.requestFocus();
            editSoilType.setText("");
            Toast.makeText(registration.this, "Soil Type is required.", Toast.LENGTH_SHORT).show();
            return false;
        }

        requiredSoilType.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables = requiredSoilType.getCompoundDrawables();
        Drawable leftDrawable = drawables[0];
        leftDrawable.setColorFilter(greenColorFilter);
        requiredSoilType.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, drawables[1], drawables[2], drawables[3]);

        return true;
    }


    private boolean validatePassword(String password) {

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required.");
            editTextPassword.requestFocus();
            editTextPassword.setText("");
            Toast.makeText(registration.this, "Password is required.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            editTextPassword.setError("Password must be at least 8 characters.");
            editTextPassword.requestFocus();
            editTextPassword.setText("");
            Toast.makeText(registration.this, "The password you entered is invalid.", Toast.LENGTH_LONG).show();
            return false;
        }

        requiredPassword.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables = requiredPassword.getCompoundDrawables();
        Drawable leftDrawable = drawables[0];
        leftDrawable.setColorFilter(greenColorFilter);
        requiredPassword.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, drawables[1], drawables[2], drawables[3]);

        minimumPassword.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter2 = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables2 = minimumPassword.getCompoundDrawables();
        Drawable leftDrawable2 = drawables2[0];
        leftDrawable2.setColorFilter(greenColorFilter2);
        minimumPassword.setCompoundDrawablesWithIntrinsicBounds(leftDrawable2, drawables2[1], drawables2[2], drawables2[3]);

        return true;
    }


    private boolean validateConfirmPassword(String password, String confirmPassword) {

        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.setError("Confirm Password is required.");
            editTextConfirmPassword.requestFocus();
            editTextConfirmPassword.setText("");
            Toast.makeText(registration.this, "Confirm Password is required.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("Passwords do not match.");
            editTextConfirmPassword.requestFocus();
            editTextConfirmPassword.setText("");
            Toast.makeText(registration.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return false;
        }

        requiredConfirmPassword.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables = requiredConfirmPassword.getCompoundDrawables();
        Drawable leftDrawable = drawables[0];
        leftDrawable.setColorFilter(greenColorFilter);
        requiredConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, drawables[1], drawables[2], drawables[3]);

        matchConfirmPassword.setTextColor(Color.parseColor("#1DAD68"));
        ColorFilter greenColorFilter2 = new PorterDuffColorFilter(Color.parseColor("#1DAD68"), PorterDuff.Mode.SRC_IN);
        Drawable[] drawables2 = matchConfirmPassword.getCompoundDrawables();
        Drawable leftDrawable2 = drawables2[0];
        leftDrawable2.setColorFilter(greenColorFilter2);
        matchConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(leftDrawable2, drawables2[1], drawables2[2], drawables2[3]);

        return true;
    }
}