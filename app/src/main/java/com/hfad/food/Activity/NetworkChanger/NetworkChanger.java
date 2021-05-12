package com.hfad.food.Activity.NetworkChanger;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.hfad.food.Activity.MainActivity;
import com.hfad.food.Adapters.MainAdapter;
import com.hfad.food.R;

public class NetworkChanger extends BroadcastReceiver {
    private static final String LOG = "MainActivity";
    private MainActivity mainActivity;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Common.IsConnectedInter(context))
        {
            Log.i(LOG, "NetworkChanger");
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout = LayoutInflater.from(context).inflate(R.layout.gps_dialog, null);
            builder.setView(layout);

            Button dialogButton = layout.findViewById(R.id.btnDialog);

            AlertDialog dialog  = builder.create();
            dialog.show();
            dialog.setCancelable(false);
            dialog.getWindow().setGravity(Gravity.CENTER);

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    onReceive(context, intent);
                }
            });

            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        else {
            mainActivity.init();
        }
    }
}
