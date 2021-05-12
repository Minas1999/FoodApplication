package com.hfad.food.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hfad.food.R;

import java.util.ArrayList;

public class BasketAdapter extends ArrayAdapter<String> {
    private Context context1;
    private ArrayList<String> rTitle1;
    private ArrayList<String> rDescription1;
    private ArrayList<Integer> rImg1;
    private ArrayList<String> price1;

    public BasketAdapter(Context c1, ArrayList<String> title1, ArrayList<String> d1, ArrayList<Integer> ima1, ArrayList<String> pr1){
        super(c1, R.layout.row1, R.id.TextView_1, title1);
        this.context1 = c1;
        this.rTitle1 = title1;
        this.rDescription1= d1;
        this.rImg1 = ima1;
        this.price1 = pr1;
        Toast.makeText(c1, "adapter1", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context1.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row1, parent, false);
        ImageView images = row.findViewById(R.id.imageMenu);
        TextView myTitle = row.findViewById(R.id.TextView_1);
        TextView myDesc = row.findViewById(R.id.TextView_2);
        TextView myPrice = row.findViewById(R.id.TextView_3);

        images.setImageResource(rImg1.get(position));
        myTitle.setText(rTitle1.get(position));
        myDesc.setText(rDescription1.get(position));
        myPrice.setText(String.valueOf(price1.get(position)));
        Log.i("myLog", "  "+myPrice);

        return row;
    }
}