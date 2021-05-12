package com.hfad.food.Activity.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hfad.food.Activity.MainActivity;
import com.hfad.food.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity {

    private CircleImageView profileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    CardView cardView;

    TextInputLayout userGmail;
    TextInputLayout userAddress;
    TextInputLayout userPassword;
    TextInputLayout userPhoneNumber;
    Button setInfoButton;
    Button buttonUpdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        profileImage = findViewById(R.id.image111);
        cardView = findViewById(R.id.LogOut);

        userGmail = findViewById(R.id.user_mail);
        userPassword = findViewById(R.id.user_password);
        userAddress = findViewById(R.id.user_address);
        userPhoneNumber = findViewById(R.id.user_phonenumber);
        setInfoButton= findViewById(R.id.setUserInfo);
        buttonUpdate= findViewById(R.id.buttonUpdateUserInfo);

        Toast.makeText(this, "user page", Toast.LENGTH_SHORT).show();
        userGmail.setEnabled(false);
        userPassword.setEnabled(false);
        userAddress.setEnabled(false);
        userPhoneNumber.setEnabled(false);


        setInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userGmail.setEnabled(false);
                userPassword.setEnabled(false);
                userAddress.setEnabled(false);
                userPhoneNumber.setEnabled(false);

                buttonUpdate.setVisibility(View.VISIBLE);
                setInfoButton.setVisibility(View.GONE);
            }
        });


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userGmail.setEnabled(true);
                userPassword.setEnabled(true);
                userAddress.setEnabled(true);
                userPhoneNumber.setEnabled(true);

                buttonUpdate.setVisibility(View.GONE);
                setInfoButton.setVisibility(View.VISIBLE);
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent galery = new Intent();
                galery.setType("image/*");
                galery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(getIntent().createChooser(galery, "select"), PICK_IMAGE);
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }




}
