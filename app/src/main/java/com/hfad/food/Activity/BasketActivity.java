package com.hfad.food.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hfad.food.Adapters.BasketAdapter;
import com.hfad.food.R;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {

    ListView listView;
    public int index;


    ArrayList<String> mTitle = new ArrayList<>();
    ArrayList<String> mDesc = new ArrayList<>();
    ArrayList<String> price = new ArrayList<>();
    ArrayList<Integer> image = new ArrayList<>();
    BasketAdapter adapter;
    TextView price1;
    TextView textViewTotalPriceNumber;
    TextView textViewDeliveryNumber;




    public void InitProducts(){
//        listView = findViewById(R.id.lvlist1);
//        MyAdapter1 adapter  = new MyAdapter1(this, mTitle, mDesc, image, price);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(BasketActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), FoodDetails.class);
//                startActivity(intent);
//            }
//        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        price1 = findViewById(R.id.textViewPriceNumber);
        textViewTotalPriceNumber = findViewById(R.id.textViewTotalPriceNumber);
        textViewDeliveryNumber = findViewById(R.id.textViewDeliveryNumber);






        mTitle.add("1");
        mTitle.add("2");
        mTitle.add("3");
        mTitle.add("4");
        mTitle.add("5");
        mTitle.add("6");

        mDesc.add("1");
        mDesc.add("2");
        mDesc.add("3");
        mDesc.add("4");
        mDesc.add("5");
        mDesc.add("7");

        price.add("100");
        price.add("100");
        price.add("100");
        price.add("100");
        price.add("100");
        price.add("100");

        image.add(R.drawable.bread);
        image.add(R.drawable.bread);
        image.add(R.drawable.bread);
        image.add(R.drawable.bread);
        image.add(R.drawable.bread);
        image.add(R.drawable.bread);


        listView = findViewById(R.id.lvlist1);


        initPrice();

        BasketAdapter adapter  = new BasketAdapter(this, mTitle, mDesc, image, price);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;

                mTitle.remove(position);
                mDesc.remove(position);
                price.remove(position);
                image.remove(position);

                listView.clearChoices();
                adapter.notifyDataSetChanged();
                initPrice();

            }
        });
    }


    @SuppressLint("SetTextI18n")
    public void initPrice(){
        int in = 0;
        for (int i = 0; i < price.size(); i++) {
            in += Integer.parseInt(price.get(i));
        }

            int a = Integer.parseInt((String) textViewDeliveryNumber.getText());
            textViewTotalPriceNumber.setText("" + (a + in));
            price1.setText("" + in);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("aaa", "onResume");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("aaa", "onStart");
    }
}
