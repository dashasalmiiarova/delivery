//package com.example.dostawa.ViewHolder;
//
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//    public TextView txtMenuName;
//    public ImageView imageView;
//    private ItemClickListener itemClickListener;
//    public FoodViewHolder(@NonNull View itemView) {
//        super(itemView);
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//}
//
////import androidx.recyclerview.widget.RecyclerView;
////
////
////import com.example.dostawa.R;
////
////public class MenuViewHolder<ItemClickListener> extends RecyclerView.ViewHolder implements View.OnClickListener {
////    public TextView txtMenuName;
////    public ImageView imageView;
////
////    private ItemClickListener itemClickListener;
////
////    public MenuViewHolder(View itemView){
////        super(itemView);
////        txtMenuName = (TextView)itemView.findViewById(R.id.menu_name);
////        imageView = (ImageView)itemView.findViewById(R.id.menu_image);
////
////        itemView.setOnClickListener(this);
////    }
////
////    public void setItemClickListener(ItemClickListener itemClickListener){
////        this.itemClickListener = itemClickListener;
////    }
////
//////    @Override
//////    public void onClick(View v) {
//////        itemClickListener.onClick(v, getAdapterPosition(),false);
//////    }
////}
