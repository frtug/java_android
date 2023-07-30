package com.example.farmanalyticav2;

import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.farmanalyticav2.databinding.ActivityLandingBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class landing extends AppCompatActivity {

    ActivityLandingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for Navbar
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Home());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
           // if (item.getItemId() == R.id.applicationFragment) {
           //     showApplicationSubMenu();
           //     return false;
         //   }

            switch (item.getItemId()) {

                case R.id.homeFragment:
                    replaceFragment(new Home());
                    break;
                case R.id.applicationFragment:
                    replaceFragment(new Application());
                    break;
                case R.id.profileFragment:
                    replaceFragment(new Profile());
                    break;
                case R.id.aboutFragment:
                    replaceFragment(new About());
                    break;
                case R.id.cropInfoFragment:
                    replaceFragment(new Crops());
                    break;
            }
            return true;
        });
        //for Navbar
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

   // private void showApplicationSubMenu() {
       // PopupMenu popupMenu = new PopupMenu(this, binding.bottomNavigationView);
      //  popupMenu.getMenuInflater().inflate(R.menu.application_sub_menu, popupMenu.getMenu());

      //  popupMenu.setOnMenuItemClickListener(item -> {
       //     switch (item.getItemId()) {
        //        case R.id.dataInputFragment:
        //            replaceFragment(new DataInput());
            //        return true;
         //       case R.id.predictionRecommendationFragment:
           //         replaceFragment(new WithoutFertilizerStep1());
           //         return true;
          //  }

        //    return false;
     //   });

      //  popupMenu.show();
  //  }
}