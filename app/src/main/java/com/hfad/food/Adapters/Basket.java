package com.hfad.food.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.food.Adapters.Adapter_Models.Departments;
import com.hfad.food.R;

import java.util.ArrayList;

public class Basket  extends RecyclerView.Adapter<Basket.ViewHolder> {
    ArrayList<Departments> depModels;
    Context context;

    public Basket(ArrayList<Departments> depModels, Context context) {
        this.depModels = depModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(depModels.get(position).image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + depModels.get(position).departmentName, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, CategoriesActivity.class);
//                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return depModels.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.deleteFromBasket1);

        }
    }
}
