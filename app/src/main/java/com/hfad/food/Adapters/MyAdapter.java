package com.hfad.food.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hfad.food.R;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> rTitle;
    private ArrayList<String> rDescription;
    private ArrayList<Integer> rImg;
    private ArrayList<String> price;

    public MyAdapter(Context c, ArrayList<String> title, ArrayList<String> d, ArrayList<Integer> ima, ArrayList<String> pr){
        super(c, R.layout.row, R.id.TextView_1, title);
        this.context = c;
        this.rTitle = title;
        this.rDescription= d;
        this.rImg = ima;
        this.price = pr;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row, parent, false);
        ImageView images = row.findViewById(R.id.imageMenu);
        TextView myTitle = row.findViewById(R.id.TextView_1);
        TextView myDesc = row.findViewById(R.id.TextView_2);
        TextView myPrice = row.findViewById(R.id.TextView_3);

        Log.i("image1", rImg.size()+" | " + rTitle.size() + " | " + price.size() + " | " + rDescription.size());

        myTitle.setText(rTitle.get(position));
        myDesc.setText(rDescription.get(position));
        myPrice.setText(String.valueOf(price.get(position)));
        images.setImageResource(rImg.get(position));

        return row;
    }
}