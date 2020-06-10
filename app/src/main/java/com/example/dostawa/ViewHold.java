package com.example.dostawa;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHold extends RecyclerView.ViewHolder {
    View mview;

    public ViewHold(@NonNull View itemView){
        super(itemView);

        mview = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view,getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view,getAdapterPosition());
                return true;
            }
        });
    }

    public void setDetails(Context ctx, String name, String image, String price){
        TextView mTitle = mview.findViewById(R.id.menu_title);
        ImageView mImage = mview.findViewById(R.id.menu_image);
        TextView mPrice = mview.findViewById(R.id.txt_food_price);
        mTitle.setText(name);
        mPrice.setText(price);
        Picasso.get().load(image).into(mImage);
    }
    private ViewHold.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(ViewHold.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
