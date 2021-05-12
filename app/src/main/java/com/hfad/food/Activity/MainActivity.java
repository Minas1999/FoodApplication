package com.hfad.food.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.hfad.food.Retorfit.API_Models.API_Departments;
import com.hfad.food.Retorfit.API_Models.API_Food;
import com.hfad.food.Activity.NetworkChanger.NetworkChanger;
import com.hfad.food.Activity.User.LoginActivity;
import com.hfad.food.Adapters.MainAdapter;
import com.hfad.food.Adapters.MyAdapter;
import com.hfad.food.Retorfit.Interfaces.HolderApi;
import com.hfad.food.Adapters.Adapter_Models.Departments;
import com.hfad.food.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String LOG = "MainActivity";
    RecyclerView recyclerView;
    ArrayList<Departments> models;
    MainAdapter mainAdapter;

    NetworkChanger NetworkChanger = new NetworkChanger();


    ListView listView;

    ArrayList<String> mTitle = new ArrayList<>();
    ArrayList<String> mDesc = new ArrayList<>();
    ArrayList<String> price = new ArrayList<>();
    ArrayList<Integer> image = new ArrayList<>();


    public void InitProducts() {
        listView = findViewById(R.id.lvList);
        MyAdapter adapter = new MyAdapter(this, mTitle, mDesc, image, price);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), FoodDetailsActivity.class);
                i.putExtra("name", mTitle.get(position));
                i.putExtra("price", price.get(position));
                i.putExtra("image", image.get(position));
                i.putExtra("desc", mDesc.get(position));
                startActivity(i);
            }
        });
    }


    public void init()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://minassandadze-001-site1.gtempurl.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HolderApi api = retrofit.create(HolderApi.class);
        Call<List<API_Food>> call = api.getFoods();
        Call<List<API_Departments>> call1 = api.getDepartments();

        call.enqueue(new Callback<List<API_Food>>() {
            @Override
            public void onResponse(Call<List<API_Food>> call, Response<List<API_Food>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                    Log.i("MyLOG", "   " + response.toString());
                    return;
                }



                List<API_Food> APIFoods = response.body();

                Log.i(LOG, Thread.currentThread().getId() + " | " + Thread.currentThread().getName());

                /*for (Food i : foods) {
                    Log.i("MyLOG", i.getIdd() + " | " + i.getFood_id() + " | " + i.getNamee() + " | " + i.getPrice() + " | " + i.getDescription() + " | " + i.getImg_url());
                }*/

                for (API_Food i : APIFoods) {

                    InitProducts();

                    mTitle.add(i.getNamee());
                    mDesc.add(i.getDescription());
                    price.add(i.getPrice() + "");
                }

                image.add(R.drawable.bread);
                image.add(R.drawable.bread);
                image.add(R.drawable.bread);
                image.add(R.drawable.bread);
                image.add(R.drawable.bread);
                image.add(R.drawable.bread);
                image.add(R.drawable.bread);
                image.add(R.drawable.bread);
                image.add(R.drawable.bread);
                image.add(R.drawable.bread);
            }

            @Override
            public void onFailure(Call<List<API_Food>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        call1.enqueue(new Callback<List<API_Departments>>() {
            @Override
            public void onResponse(Call<List<API_Departments>> call, Response<List<API_Departments>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                    Log.i("MyLOG", "   " + response.toString());
                    return;
                }

                recyclerView = findViewById(R.id.recycler_view);

                List<API_Departments> foods = response.body();

                for (API_Departments i : foods) {
                    Log.i("minas", i.getDep_name());
                }

                Integer[] logo = {
                        R.drawable.c,
                        R.drawable.f,
                        R.drawable.d,
                        R.drawable.c,
                        R.drawable.f,
                        R.drawable.d
                };

                String[] name = new String[foods.size()];

                for (int i = 0; i < foods.size(); i++) {
                    assert response.body() != null;
                    name[i] = response.body().get(i).getDep_name();
                }


                models = new ArrayList<>();
                for (int i = 0; i < logo.length; i++) {
                    Departments departments = new Departments(logo[i], name[i]);
                    models.add(departments);
                }

                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                mainAdapter = new MainAdapter(models, MainActivity.this);
                recyclerView.setAdapter(mainAdapter);
            }

            @Override
            public void onFailure(Call<List<API_Departments>> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG, Thread.currentThread().getId() + " | " + Thread.currentThread().getName());

        init();


        ImageView imageView1 = (ImageView)findViewById(R.id.imageView2);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        ImageView basket = (ImageView) findViewById(R.id.MainBasket);
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                startActivity(intent);
            }
        });

        ImageView settings = findViewById(R.id.settings_id);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "settings", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG, "Start");
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(NetworkChanger, filter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG, "resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG, "pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG, "stop");
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        unregisterReceiver(NetworkChanger);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG, "restart");
    }
}
