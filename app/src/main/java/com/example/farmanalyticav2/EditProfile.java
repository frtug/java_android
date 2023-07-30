package com.example.farmanalyticav2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.farmanalyticav2.databinding.FragmentEditProfileBinding;
import com.example.farmanalyticav2.databinding.FragmentWithoutFertilizerStep1Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditProfile extends Fragment {

    FragmentEditProfileBinding binding;

    Button saveUpdatedInfoButton;

    Button backToProfile;

    private String selectedRegion = "";
    private String selectedProvince = "";
    private String selectedMunicipality = "";

    Spinner updateRegionSpinner, updateProvinceSpinner, updateMunicipalitySpinner;

    ArrayAdapter<CharSequence> regionAdapter, provinceAdapter, municipalityAdapter;

    EditText updateEmail, updateFN, updateMN, updateFarmArea, updateSoilType;

    String newEmail, newFN, newMN, newFarmArea, newSoilType;

    DatabaseReference reference;

    public EditProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        saveUpdatedInfoButton = view.findViewById(R.id.saveUpdatedInfoButton);

        backToProfile = view.findViewById(R.id.backToProfile);
        binding.backToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile Profile = new Profile();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, Profile);
                fragmentTransaction.commit();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("users");

        updateEmail = view.findViewById(R.id.updateEmail);
        updateFN = view.findViewById(R.id.updateFN);
        updateMN = view.findViewById(R.id.updateMN);
        updateFarmArea = view.findViewById(R.id.updateFarmArea);
        updateSoilType = view.findViewById(R.id.updateSoilType);

        // For Province spinner
        updateProvinceSpinner = view.findViewById(R.id.updateProvinceSpinner);
        updateMunicipalitySpinner = view.findViewById(R.id.updateMunicipalitySpinner);

        provinceAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.array_default_provinces, R.layout.region_spinner_layout);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updateProvinceSpinner.setAdapter(provinceAdapter);

        updateProvinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String selectedProvince = parent.getItemAtPosition(i).toString();

                ArrayAdapter<CharSequence> municipalityAdapter = getMunicipalityAdapter(selectedProvince);
                updateMunicipalitySpinner.setAdapter(municipalityAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.saveUpdatedInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.updateEmail.getText().toString();
                String fullName = binding.updateFN.getText().toString();
                String mobileNumber = binding.updateMN.getText().toString();
                String state_name = binding.updateProvinceSpinner.getSelectedItem().toString();
                String district_name = binding.updateMunicipalitySpinner.getSelectedItem().toString();
                String areaFarm = binding.updateFarmArea.getText().toString();
                String soilType = binding.updateSoilType.getText().toString();

                updateData(email, fullName, mobileNumber, state_name, district_name, areaFarm, soilType);
            }
        });
        return view;
    }

    private ArrayAdapter<CharSequence> getMunicipalityAdapter(String province) {
        int municipalityArrayResourceId;
        switch (province) {
            case "Select your Province":
                municipalityArrayResourceId = R.array.array_default_municipalities;
                break;
            case "Ilocos Norte":
                municipalityArrayResourceId = R.array.array_ilocosnorte_municipalities;
                break;
            case "Ilocos Sur":
                municipalityArrayResourceId = R.array.array_ilocossur_municipalities;
                break;
            case "La Union":
                municipalityArrayResourceId = R.array.array_launion_municipalities;
                break;
            case "Pangasinan":
                municipalityArrayResourceId = R.array.array_pangasinan_municipalities;
                break;
            case "Batanes":
                municipalityArrayResourceId = R.array.array_batanes_municipalities;
                break;
            case "Cagayan":
                municipalityArrayResourceId = R.array.array_cagayan_municipalities;
                break;
            case "Isabela":
                municipalityArrayResourceId = R.array.array_isabela_municipalities;
                break;
            case "Nueva Vizcaya":
                municipalityArrayResourceId = R.array.array_nuevavizcaya_municipalities;
                break;
            case "Quirino":
                municipalityArrayResourceId = R.array.array_quirino_municipalities;
                break;
            case "Aurora":
                municipalityArrayResourceId = R.array.array_aurora_municipalities;
                break;
            case "Bulacan":
                municipalityArrayResourceId = R.array.array_bulacan_municipalities;
                break;
            case "Nueva Ecija":
                municipalityArrayResourceId = R.array.array_nuevaecija_municipalities;
                break;
            case "Pampanga":
                municipalityArrayResourceId = R.array.array_pampanga_municipalities;
                break;
            case "Tarlac":
                municipalityArrayResourceId = R.array.array_tarlac_municipalities;
                break;
            case "Zambales":
                municipalityArrayResourceId = R.array.array_zambales_municipalities;
                break;
            case "Bataan":
                municipalityArrayResourceId = R.array.array_bataan_municipalities;
                break;
            case "Antique":
                municipalityArrayResourceId = R.array.array_antique_municipalities;
                break;
            case "Capiz":
                municipalityArrayResourceId = R.array.array_capiz_municipalities;
                break;
            case "Iloilo":
                municipalityArrayResourceId = R.array.array_iloilo_municipalities;
                break;
            case "Guimaras":
                municipalityArrayResourceId = R.array.array_guimaras_municipalities;
                break;
            case "Negros Occidental":
                municipalityArrayResourceId = R.array.array_negocci_municipalities;
                break;
            case "Negros Oriental":
                municipalityArrayResourceId = R.array.array_negori_municipalities;
                break;
            case "Aklan":
                municipalityArrayResourceId = R.array.array_aklan_municipalities;
                break;
            case "Bohol":
                municipalityArrayResourceId = R.array.array_bohol_municipalities;
                break;
            case "Cebu":
                municipalityArrayResourceId = R.array.array_cebu_municipalities;
                break;
            case "Siquijor":
                municipalityArrayResourceId = R.array.array_siquijor_municipalities;
                break;
            case "Biliran":
                municipalityArrayResourceId = R.array.array_biliran_municipalities;
                break;
            case "Eastern Samar":
                municipalityArrayResourceId = R.array.array_eastsamar_municipalities;
                break;
            case "Leyte":
                municipalityArrayResourceId = R.array.array_leyte_municipalities;
                break;
            case "Northern Samar":
                municipalityArrayResourceId = R.array.array_northsamar_municipalities;
                break;
            case "West Samar":
                municipalityArrayResourceId = R.array.array_westsamar_municipalities;
                break;
            case "Southern Leyte":
                municipalityArrayResourceId = R.array.array_southleyte_municipalities;
                break;
            case "Zamboanga del Norte":
                municipalityArrayResourceId = R.array.array_zamnorte_municipalities;
                break;
            case "Zamboanga del Sur":
                municipalityArrayResourceId = R.array.array_zamsur_municipalities;
                break;
            case "Zamboanga Sibugay":
                municipalityArrayResourceId = R.array.array_zamsibugay_municipalities;
                break;
            case "Bukidnon":
                municipalityArrayResourceId = R.array.array_bukidnon_municipalities;
                break;
            case "Camiguin":
                municipalityArrayResourceId = R.array.array_camiguin_municipalities;
                break;
            case "Lanao del Norte":
                municipalityArrayResourceId = R.array.array_lanaodelnorte_municipalities;
                break;
            case "Misamis Occidental":
                municipalityArrayResourceId = R.array.array_misamisocci_municipalities;
                break;
            case "Misamis Oriental":
                municipalityArrayResourceId = R.array.array_misamisori_municipalities;
                break;
            case "Compostela Valley":
                municipalityArrayResourceId = R.array.array_compostelavalley_municipalities;
                break;
            case "Davao del Norte":
                municipalityArrayResourceId = R.array.array_davaonorte_municipalities;
                break;
            case "Davao del Sur":
                municipalityArrayResourceId = R.array.array_davaosur_municipalities;
                break;
            case "Davao Occidental":
                municipalityArrayResourceId = R.array.array_davaoocci_municipalities;
                break;
            case "Davao Oriental":
                municipalityArrayResourceId = R.array.array_davaoori_municipalities;
                break;
            case "South Cotabato":
                municipalityArrayResourceId = R.array.array_southcotabato_municipalities;
                break;
            case "North Cotabato":
                municipalityArrayResourceId = R.array.array_cotabato_municipalities;
                break;
            case "Sarangani":
                municipalityArrayResourceId = R.array.array_sarangani_municipalities;
                break;
            case "Agusan del Norte":
                municipalityArrayResourceId = R.array.array_agusannorte_municipalities;
                break;
            case "Agusan del Sur":
                municipalityArrayResourceId = R.array.array_agusansur_municipalities;
                break;
            case "Surigao del Norte":
                municipalityArrayResourceId = R.array.array_surigaonorte_municipalities;
                break;
            case "Surigao del Sur":
                municipalityArrayResourceId = R.array.array_surigaosur_municipalities;
                break;
            case "Basilan":
                municipalityArrayResourceId = R.array.array_basilan_municipalities;
                break;
            case "Lanao del Sur":
                municipalityArrayResourceId = R.array.array_lanaonorte_municipalities;
                break;
            case "Maguindanao":
                municipalityArrayResourceId = R.array.array_maguindanao_municipalities;
                break;
            case "Sulu":
                municipalityArrayResourceId = R.array.array_sulu_municipalities;
                break;
            case "Tawi-Tawi":
                municipalityArrayResourceId = R.array.array_tawitawi_municipalities;
                break;
            default:
                municipalityArrayResourceId = R.array.array_default_municipalities;
                break;
        }
        ArrayAdapter<CharSequence> municipalityAdapter = ArrayAdapter.createFromResource(getActivity(), municipalityArrayResourceId, R.layout.region_spinner_layout);
        municipalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return municipalityAdapter;
    }

    public void updateData(String email, String fullName, String mobileNumber, String state_name, String district_name, String areaFarm, String soilType) {

        HashMap User = new HashMap();
        User.put("email", email);
        User.put("mobileNumber", mobileNumber);
        User.put("state_name", state_name);
        User.put("district_name", district_name);
        User.put("areaFarm", areaFarm);
        User.put("soilType", soilType);

        reference = FirebaseDatabase.getInstance().getReference("users");
        reference.child(fullName).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    binding.updateEmail.setText("");
                    binding.updateFN.setText("");
                    binding.updateMN.setText("");
                    binding.updateFarmArea.setText("");
                    binding.updateSoilType.setText("");

                    // Set the selection of updateProvinceSpinner
                    setSpinnerSelection(binding.updateProvinceSpinner, state_name);

                    // Set the selection of updateMunicipalitySpinner
                    setSpinnerSelection(binding.updateMunicipalitySpinner, district_name);

                    Toast.makeText(getActivity(), "Data is successfully updated!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "Unsuccessful data update!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setSpinnerSelection(Spinner spinner, String selectedItem) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();

        for (int i = 0; i < adapter.getCount(); i++) {
            String item = adapter.getItem(i);
            if (item != null && item.equals(selectedItem)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}