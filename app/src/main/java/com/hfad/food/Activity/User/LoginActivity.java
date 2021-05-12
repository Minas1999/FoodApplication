package com.hfad.food.Activity.User;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.hfad.food.Activity.Mapping.MapActivity;
import com.hfad.food.R;

import static android.view.View.GONE;

public class LoginActivity extends AppCompatActivity {


    Button buttonLogin;
    TextView inputGmail;
    TextView inputPassword;
    Button btnLogin;
    TextView forgotPassword;
    LinearLayout linearLayout2;
    ImageView googleLogin;
    ImageView facebookLogin;
    TextView gotoRegister;
    EditText inputEmailReg;
    EditText inputPasswordReg;
    EditText inputPhoneNumber3;
    Button mapbtn;
    EditText inputAddress;
    Button btnReg;

    private static final String TAG = "LoginActivity";
    private static final int ERROR_DIALOG = 9001;

    FusedLocationProviderClient fusedLocationProviderClient;
    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        
        inputGmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin1);
        forgotPassword = findViewById(R.id.forgotPassword);
        linearLayout2 = findViewById(R.id.linearLayout2);
        googleLogin = findViewById(R.id.googleLogin);
        facebookLogin = findViewById(R.id.facebookLogin);
        gotoRegister = findViewById(R.id.gotoRegister);
        //**********************************************

        inputEmailReg = findViewById(R.id.inputEmailReg);
        inputPasswordReg = findViewById(R.id.inputPasswordReg);
        inputPhoneNumber3 = findViewById(R.id.inputPhoneNumber);
        mapbtn = findViewById(R.id.mapbtn);
        inputAddress = findViewById(R.id.inputAddress);
        btnReg = findViewById(R.id.btnReg);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputGmail.setVisibility(GONE);
                inputPassword.setVisibility(GONE);
                btnLogin.setVisibility(GONE);
                forgotPassword.setVisibility(GONE);
                linearLayout2.setVisibility(GONE);
                googleLogin.setVisibility(GONE);
                facebookLogin.setVisibility(GONE);
                gotoRegister.setVisibility(GONE);

                inputEmailReg.setVisibility(View.VISIBLE);
                inputPasswordReg.setVisibility(View.VISIBLE);
                inputPhoneNumber3.setVisibility(View.VISIBLE);
                mapbtn.setVisibility(View.VISIBLE);
                inputAddress.setVisibility(View.VISIBLE);
                btnReg.setVisibility(View.VISIBLE);
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();

                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

        if (isServiceOK()) {
            init();
        }
    }



    private void showAlertDialog(int layout){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        View layoutView = getLayoutInflater().inflate(layout, null);
        Button dialogButton = layoutView.findViewById(R.id.btnDialog);
        dialogBuilder.setView(layoutView);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                alertDialog.dismiss();
            }
        });

        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void init() {
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                if (enabled) {
                    Intent intent1 = new Intent(getApplicationContext(), MapActivity.class);
                    startActivity(intent1);
                }
                else{
                    showAlertDialog(R.layout.gps_dialog);
                }
            }
        });
    }

    public boolean isServiceOK() {
        int aviable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(LoginActivity.this);
        if (aviable == ConnectionResult.SUCCESS) {
            Log.d(TAG, "ok");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(aviable)) {
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(LoginActivity.this, aviable, ERROR_DIALOG);
            dialog.show();
        } else {
            Toast.makeText(this, "esim e", Toast.LENGTH_SHORT).show();
        }
        return false;
    }



}
