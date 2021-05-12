package com.hfad.food.Activity.User.User_Authentication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.hfad.food.Activity.MainActivity;
import com.hfad.food.R;

@RequiresApi(api = Build.VERSION_CODES.M)
class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private Context context;
    public boolean LogIN = false;


    public FingerprintHandler(Context context) {
        this.context = context;
    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal, 0, this, null);
    }



    @Override
    public void onAuthenticationFailed() {
        this.update("failed", false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("error2"+ helpString, false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("sax lava", true);
    }

    private void update(String s, boolean b) {
        TextView paraLabel = ((Activity)context).findViewById(R.id.paraLabel);
        paraLabel.setText(s);

        if (!b){
            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
        else{

            Intent intent = new Intent(this.context, MainActivity.class);
            ((Activity)context).startActivity(intent);
            LogIN = true;
            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            ((Activity)context).finish();

        }
    }


}
