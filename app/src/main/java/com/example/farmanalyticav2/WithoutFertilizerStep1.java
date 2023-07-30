package com.example.farmanalyticav2;

import android.os.Bundle;
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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.farmanalyticav2.databinding.FragmentWithFertilizerStep1Binding;
import com.example.farmanalyticav2.databinding.FragmentWithFertilizerStep1Binding;
import com.example.farmanalyticav2.databinding.FragmentWithoutFertilizerStep1Binding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;



public class WithoutFertilizerStep1 extends Fragment {

    FragmentWithoutFertilizerStep1Binding binding;

    CheckBox locationCheckbox;

    LinearLayout currentLocationLayout;
    TextView locationTextView;
    TextInputLayout locationInputLayout;
    Button fillButton;
    Button buttonToFertilizerRecommendation, predictButton, theBackArrow;

    TextInputEditText temperatureTextView, humidityTextView, precipitationTextView, nitrogenInput, phosphorusInput, potassiumInput;

    TextInputEditText municipalityEditText;

    private CurrentWeather currentWeather;

    Spinner provinceDropdown, municipalityDropdown;

    ArrayAdapter<CharSequence> provinceAdapter, municipalityAdapter;

    private static final String BASE_URL = "https://api.weatherapi.com/v1/";
    private static final String API_KEY = "a3742f0932b641619fd111204230306"; // Replace with your actual API key

    private static final String FLASK_API_URL = "http://your-domain.com/recommendCrop"; // Replace with your Flask API URL

    public WithoutFertilizerStep1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWithoutFertilizerStep1Binding.inflate(inflater, container, false);
        View view = binding.getRoot();


        // ...
      //  temperatureTextView = view.findViewById(R.id.temperatureTextView);
      //  humidityTextView = view.findViewById(R.id.humidityTextView);
      //  precipitationTextView = view.findViewById(R.id.precipitationTextView);
     //   municipalityEditText = view.findViewById(R.id.yourMunicipalityEditTextId);
        // ...

        // Find views by their IDs
        currentLocationLayout = view.findViewById(R.id.dropdownContainer);
       // locationTextView = view.findViewById(R.id.locationTextView);
      //  locationInputLayout = view.findViewById(R.id.locationInputLayout);
      //  fillButton = view.findViewById(R.id.fillButton);
        buttonToFertilizerRecommendation = view.findViewById(R.id.buttonToStep2);

        locationCheckbox = view.findViewById(R.id.locationCheckbox);

        theBackArrow = view.findViewById(R.id.theBackArrow);
        binding.theBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application Application = new Application();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, Application);
                fragmentTransaction.commit();
            }
        });


        // ...
       // predictButton = view.findViewById(R.id.predictButton);
       // nitrogenInput = view.findViewById(R.id.nitrogenInput);
      //  phosphorusInput = view.findViewById(R.id.phosphorusInput);
       // potassiumInput = view.findViewById(R.id.potassiumInput);
        // ...

        // For Province spinner
        provinceDropdown = view.findViewById(R.id.provinceDropdown);
        municipalityDropdown = view.findViewById(R.id.municipalityDropdown);

        provinceAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.array_default_provinces, R.layout.region_spinner_layout);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceDropdown.setAdapter(provinceAdapter);

        provinceDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String selectedProvince = parent.getItemAtPosition(i).toString();

                ArrayAdapter<CharSequence> municipalityAdapter = getMunicipalityAdapter(selectedProvince);
                municipalityDropdown.setAdapter(municipalityAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // ...


        // Set click listener for "Use Current Location" button
        locationCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentLocationLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });

        /*
        // Set click listener for "Fill" button
        binding.fillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String municipality = municipalityEditText.getText().toString().trim();
                if (!municipality.isEmpty()) {
                    fetchWeatherData(municipality);
                } else {
                    // Handle empty municipality field error
                }
            }
        });
        */

        // Set on click listener for "Next" button
        binding.buttonToStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithoutFertilizerStep2 WithoutFertilizerStep2 = new WithoutFertilizerStep2();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, WithoutFertilizerStep2);
                fragmentTransaction.commit();
            }
        });

        /*
        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */

        return binding.getRoot();
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

    private void fetchWeatherData(String municipality) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApiService apiService = retrofit.create(WeatherApiService.class);
        Call<WeatherResponse> call = apiService.getCurrentWeather(API_KEY, municipality);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    updateWeatherUI(weatherResponse);
                } else {
                    // Handle API response error
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                // Handle network failure or API call failure
            }
        });
    }

    private void updateWeatherUI(WeatherResponse weatherResponse) {
        if (weatherResponse != null && weatherResponse.current != null) {
            double temperature = weatherResponse.current.temp_c;
            int humidity = weatherResponse.current.humidity;
            double precipitation = weatherResponse.current.precip_mm;

            temperatureTextView.setText(String.valueOf(weatherResponse.current.temp_c));
            humidityTextView.setText(String.valueOf(weatherResponse.current.humidity));
            precipitationTextView.setText(String.valueOf(weatherResponse.current.precip_mm));
        }
    }


    // ...

    interface WeatherApiService {
        @GET("current.json")
        Call<WeatherResponse> getCurrentWeather(
                @Query("key") String apiKey,
                @Query("q") String location
        );
    }

    // ...

    static class WeatherResponse {
        Location location;
        CurrentWeather current;
    }

    static class Location {
        String name;
        String region;
        String country;
    }

    static class CurrentWeather {
        double temp_c;
        int humidity;
        double precip_mm;
        // Add more weather data fields as needed
    }

    // ...

}
