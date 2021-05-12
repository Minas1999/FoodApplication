package com.hfad.food.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.hfad.food.Adapters.CategoryAdapter;
import com.hfad.food.Adapters.MyAdapter;
import com.hfad.food.Adapters.Adapter_Models.Category;
import com.hfad.food.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    private static final String LOG = "CategoriesActivity";
    List<Category> categoryList;
    RecyclerView categoryRecyclerView;
    CategoryAdapter categoryAdapter;
    ListView listView;

    ArrayList<String> mTitle = new ArrayList<>();
    ArrayList<String> mDesc = new ArrayList<>();
    ArrayList<String> price = new ArrayList<>();
    ArrayList<Integer> image = new ArrayList<>();
    ImageView back;
    ImageView goToBasket;

    public void InitProducts(){
        listView = findViewById(R.id.lvList);
        MyAdapter adapter  = new MyAdapter(this, mTitle, mDesc, image, price);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(), FoodDetailsActivity.class);
                i.putExtra("name", mTitle.get(position));
                i.putExtra("price", price.get(position));
                i.putExtra("image", image.get(position));
                i.putExtra("desc", mDesc.get(position));
                Log.i(LOG, mDesc.get(position) + " | " + mTitle.get(position));
                startActivity(i);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        mTitle.add("1");
        mTitle.add("2");
        mTitle.add("3");
        mTitle.add("4");
        mTitle.add("5");

        mDesc.add("11");
        mDesc.add("12");
        mDesc.add("13");
        mDesc.add("14");
        mDesc.add("15");

        image.add(R.drawable.bread);
        image.add(R.drawable.bread);
        image.add(R.drawable.bread);
        image.add(R.drawable.bread);
        image.add(R.drawable.bread);

        price.add("100");
        price.add("200");
        price.add("300");
        price.add("400");
        price.add("500");

        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        goToBasket = findViewById(R.id.imageView3);
        back = findViewById(R.id.imageView33);

        goToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, R.drawable.burger));
        categoryList.add(new Category(2, R.drawable.ic_settings));
        categoryList.add(new Category(3, R.drawable.ic_settings));
        categoryList.add(new Category(4, R.drawable.ic_settings));
        categoryList.add(new Category(5, R.drawable.ic_settings));
        categoryList.add(new Category(6, R.drawable.ic_settings));
        categoryList.add(new Category(7, R.drawable.ic_settings));
        categoryList.add(new Category(8, R.drawable.ic_settings));
        setCategoryRecycler(categoryList);

        InitProducts();



    }

    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }


}
