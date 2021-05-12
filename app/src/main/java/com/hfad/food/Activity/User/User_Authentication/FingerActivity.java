package com.hfad.food.Activity.User.User_Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfad.food.Activity.MainActivity;
import com.hfad.food.R;

public class FingerActivity extends AppCompatActivity {

    private TextView mHeadingLabel;
    private ImageView mFingerPrintImage;
    private TextView mParaLabel;
    private  KeyguardManager keyguardManager;
    private FingerprintManager fingerprintManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger);

        mHeadingLabel = findViewById(R.id.HeadingLabel);
        mFingerPrintImage = findViewById(R.id.fingerPrintImage);
        mParaLabel = findViewById(R.id.paraLabel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Context context;
            fingerprintManager = (FingerprintManager)getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()){
                mParaLabel.setText("not detected");
            }
            else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
                mParaLabel.setText("aaaaaaa");
            }
            else if (!keyguardManager.isKeyguardSecure()){
                mParaLabel.setText("vvvvvvvv");
            }
            else if (!fingerprintManager.hasEnrolledFingerprints()){
                mParaLabel.setText("bbbbb");
            }
            else{
                mParaLabel.setText("sax lava");

                FingerprintHandler fingerprintHandler =
                        new FingerprintHandler(this);

                fingerprintHandler.startAuth(fingerprintManager, null);
                if (fingerprintHandler.LogIN){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }

        }
    }
}
