//package com.example.dostawa.ViewHolder;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.dostawa.Model.Category;
//import com.example.dostawa.R;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//
//public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {
//    Context context;
//    List<Category> categoryList;
//
//    public CategoriesAdapter(Context context, List<Category> categoryList) {
//        this.context = context;
//        this.categoryList = categoryList;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//        Unbinder unbinder;
//
//        @BindView(R.id.menu_title)
//        TextView txt_menu_title;
//        @BindView(R.id.menu_image)
//        ImageView imageView;
//
//        public  MyViewHolder(@NonNull View itemView){
//            super(itemView);
//            unbinder = ButterKnife.bind(this,itemView);
//        }
//    }
//
//}

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
