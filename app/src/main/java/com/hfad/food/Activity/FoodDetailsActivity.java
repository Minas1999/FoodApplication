package com.hfad.food.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hfad.food.R;

public class FoodDetailsActivity extends AppCompatActivity {

    int count = 1;
    String name;
    String price;
    String Description;
    int imageUrl;
    TextView food_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        ImageView imageViewCompany = findViewById(R.id.imageViewCompany);
        imageViewCompany.setImageResource(R.drawable.coca_cola_logo_icon512);
        food_description = findViewById(R.id.food_description);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        imageUrl = intent.getIntExtra("image", R.drawable.ic_settings);
        Description = intent.getStringExtra("desc");



        ImageView imageView = findViewById(R.id.imageView5);
        TextView itemName = findViewById(R.id.name);
        TextView itemPrice = findViewById(R.id.price1);

        itemName.setText(name);
        itemPrice.setText(price + " Դրամ");
        food_description.setText(Description);
        Glide.with(getApplicationContext()).load(imageUrl).into(imageView);



        ImageView imageView1 = (ImageView) findViewById(R.id.imageView2);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageViewMinus = findViewById(R.id.imageViewMinus);
        ImageView imageViewPlus  = findViewById(R.id.imageViewPlus);
        TextView textViewCount   = findViewById(R.id.textViewCount);
        Button buttonAddToBasket = findViewById(R.id.buttonAddToBasket);


        imageViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                textViewCount.setText(Integer.toString(count));
            }
        });

        imageViewMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 1){
                    count--;
                }

                textViewCount.setText(Integer.toString(count));
            }
        });

        buttonAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FoodDetailsActivity.this, "added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
