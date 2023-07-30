package com.example.farmanalyticav2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import android.app.ProgressDialog;
import android.content.Context;


public class Login extends AppCompatActivity {

    //Registration
    Button goToRegistration;
    //Registration

    private ProgressBar progressBar;

    TextInputEditText loginemail, loginpass;
    Button loginInformation;

    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginemail = findViewById(R.id.login_email);
        loginpass = findViewById(R.id.login_password);
        loginInformation = findViewById(R.id.loginInformation);

        mAuth = FirebaseAuth.getInstance();

        // For FORGOT PASSWORD
        // Inside onCreate method
        Button forgotPasswordButton = findViewById(R.id.forgotPasswordButton);
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Forgot Password" button click
                // Implement the logic to reset the user's password
                // This can be done by sending a password reset email or providing a password reset form
                // You can use Firebase Authentication's built-in password reset functionality if you're using Firebase
                // Alternatively, you can create your own password reset mechanism

                // Example: Reset password using Firebase Authentication
                String email = loginemail.getText().toString().trim();
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Password reset email sent successfully
                                    Toast.makeText(Login.this, "Please check your email for a password reset email.", Toast.LENGTH_LONG).show();
                                } else {
                                    // Failed to send password reset email
                                    Toast.makeText(Login.this, "Failed to send the password reset email.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });


        //For "Login" button

        loginInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateEmail() | !validatePass()) {

                } else {
                    checkUser();
                }
            }
        });
        //For "Login" button

        //For "Don't have an account?" text button
        goToRegistration = findViewById(R.id.goToRegistration);
        goToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, registration.class);
                startActivity(intent);
            }
        });
        //For "Don't have an account?" text button

    }

    public Boolean validateEmail() {
        String emailVal = loginemail.getText().toString();
        if (emailVal.isEmpty()) {
            loginemail.setError("Email cannot be empty.");
            return false;
        } else {
            loginemail.setError(null);
            return true;
        }
    }

    public Boolean validatePass() {
        String passVal = loginpass.getText().toString();
        if (passVal.isEmpty()) {
            loginpass.setError("Password cannot be empty.");
            return false;
        } else {
            loginpass.setError(null);
            return true;
        }
    }

    public void checkUser() {

        String userEmail = loginemail.getText().toString().trim();
        String userPass = loginpass.getText().toString().trim();

        // Show the ProgressBar
        showProgressBar();

        mAuth.signInWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Hide the ProgressBar
                        hideProgressBar();

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Login", "Sign in with email and password successful.");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Show the bottom sheet dialog
                            showBottomSheetDialogLogin();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Login", "Sign in with email and password unsuccessful.", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showProgressBar() {
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showBottomSheetDialogLogin() {
        BottomSheetDialog dialog = new BottomSheetDialog(Login.this);
        dialog.setContentView(R.layout.bottom_sheet_dialog_loginsuccess);

        Button navigateToHomePage = dialog.findViewById(R.id.navigateToHomePage);
        navigateToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, landing.class);
                startActivity(intent);
            }
        });

        dialog.show();
    }
}