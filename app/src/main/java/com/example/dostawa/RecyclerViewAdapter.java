package com.example.dostawa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dostawa.Model.Category;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    List<Category> mCategory;

    public RecyclerViewAdapter(Context mContext, List<Category> mCategory) {
        this.mContext = mContext;
        this.mCategory = mCategory;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.menu_row, parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.m_name.setText(mCategory.get(position).getFood_id_name());
        holder.m_image.setImageResource(mCategory.get(position).getImage());
        holder.m_price.setText(mCategory.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return mCategory.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView m_name;
        private ImageView m_image;
        private TextView m_price;

        public MyViewHolder(View itemView){
            super(itemView);

            m_name = (TextView) itemView.findViewById(R.id.menu_title);
            m_image = (ImageView) itemView.findViewById(R.id.menu_image);
            m_price = (TextView) itemView.findViewById(R.id.txt_food_price);
        }
    }
}
