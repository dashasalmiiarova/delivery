package com.example.dostawa.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dostawa.Model.Category;
import com.example.dostawa.R;
import com.example.dostawa.RecyclerViewAdapter;
import com.example.dostawa.ViewHold;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
//    LinearLayoutManager linearLayoutManager;
//    RecyclerView mrecyclerView;
//    FirebaseDatabase firebaseDatabase;
//    DatabaseReference databaseReference;
//    FirebaseRecyclerAdapter<Category, ViewHold> firebaseRecyclerAdapter;
//    FirebaseRecyclerOptions<Category> options;
////    View v;
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
//        firebaseDatabase = FirebaseDatabase.getInstance();
//
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        mrecyclerView = root.findViewById(R.id.recyclerview);
//        databaseReference = firebaseDatabase.getReference("Category");
//        showData();
//
//        return root;
//    }
//    private void showData(){
//        options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(databaseReference,Category.class).build();
//        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Category, ViewHold>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull ViewHold viewHolder, int i, @NonNull Category category) {
//                viewHolder.setDetails(getContext(),category.getFood_id_name(),category.getImage());
//            }
//
//            @NonNull
//            @Override
//            public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_row,parent, false);
//                ViewHold viewHolder = new ViewHold(itemView);
//                viewHolder.setOnClickListener(new ViewHold.ClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onItemLongClick(View view, int position) {
//                        Toast.makeText(getContext(), "hello long click", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                return viewHolder;
//            }
//        };
//        mrecyclerView.setLayoutManager(linearLayoutManager);
//        firebaseRecyclerAdapter.startListening();
//        mrecyclerView.setAdapter(firebaseRecyclerAdapter);
//    }
//    public void onStart(){
//        super.onStart();
//        if (firebaseRecyclerAdapter != null){
//            firebaseRecyclerAdapter.startListening();
//        }
//    }

    View v;
    private RecyclerView mRecycleView;
    private List<Category> lstCategory;
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home,container,false);
        mRecycleView = (RecyclerView) v.findViewById(R.id.recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstCategory);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setAdapter(recyclerViewAdapter);
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstCategory = new ArrayList<>();
        lstCategory.add(new Category("Fish",R.drawable.img1));
        lstCategory.add(new Category("Chicken",R.drawable.img2));
        lstCategory.add(new Category("Pasta",R.drawable.img3));
        lstCategory.add(new Category("Soup",R.drawable.img4));
        lstCategory.add(new Category("Rise and fish",R.drawable.img5));
        lstCategory.add(new Category("Soup",R.drawable.img6));
        lstCategory.add(new Category("Soup",R.drawable.img7));
        lstCategory.add(new Category("Trimmings with shrimps",R.drawable.img8));
        lstCategory.add(new Category("Trimmings with fish",R.drawable.img9));
        lstCategory.add(new Category("Trimmings with chicken",R.drawable.img10));
    }
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        //start
        //    private HomeViewModel homeViewModel;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.menu_name);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//        return root;
        }
