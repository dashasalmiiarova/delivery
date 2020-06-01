package com.example.dostawa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dostawa.Model.Food;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolderN>{
    private Context context;
    private List<Food> foodModel;

    public FoodAdapter(Context context, List<Food> foodModel) {
        this.context = context;
        this.foodModel = foodModel;
    }

    @NonNull
    @Override
    public MyViewHolderN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.menu_row, parent, false);
//        return new MyViewHolderN(view);
        return new MyViewHolderN(LayoutInflater.from(context)
                .inflate(R.layout.menu_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderN holder, int position) {

        Glide.with(context).load(foodModel.get(position).getImage()).into(holder.menu_image);
        holder.menu_title.setText(new StringBuilder("")
            .append(foodModel.get(position).getName()));
    }


    @Override
    public int getItemCount() {
        return foodModel == null? 0: foodModel.size();
    }

    public class MyViewHolderN extends RecyclerView.ViewHolder{
        @BindView(R.id.menu_title)
        TextView menu_title;
        @BindView(R.id.menu_image)
        ImageView menu_image;

        Unbinder unbinder;

        public MyViewHolderN(@NonNull View itemView){
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}

//public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
//    Context context;
//    List<Food> foodList;
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        Unbinder unbinder;
//        @BindView(R.id.menu_title)
//        TextView txt_menu_title;
//        @BindView(R.id.menu_image)
//        ImageView img_food;
//
//        public MyViewHolder(@NonNull View itemView){
//            super(itemView);
//            unbinder = ButterKnife.bind(this,itemView);
//        }
//    }
//
//    public FoodAdapter(Context context, List<Food> foodList) {
//        this.context = context;
//        this.foodList = foodList;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new MyViewHolder(LayoutInflater.from(context)
//                .inflate(R.layout.menu_row,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Glide.with(context).load(foodList.get(position).getImage())
//                .into(holder.img_food);
//        holder.txt_menu_title.setText(foodList.get(position).getName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return foodList.size();
//    }
//
//
//}
