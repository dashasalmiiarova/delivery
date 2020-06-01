package com.example.dostawa.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dostawa.FoodAdapter;
import com.example.dostawa.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;

//    private FoodAdapter adapter;
    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        recyclerView = root.findViewById(R.id.recyclerview);
        unbinder = ButterKnife.bind(this, root);
        init();
        homeViewModel.getFood().observe(getViewLifecycleOwner(),foods -> {
            FoodAdapter adapter = new FoodAdapter(getContext(), foods);
            recyclerView.setAdapter(adapter);
        });

        return root;
    }

    private void init() {
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }
}

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
//        super.onViewCreated(view, savedInstanceState);
//        this.v=view;
//        init();
//        progress = new ProgressDialog(getActivity());
//        progress.setTitle("Loading");
//        progress.setMessage("Syncing");
//        progress.setCancelable(false);
//        progress.show();
//        loaddata();
//    }

//    private void loaddata() {
//        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Category");
//        db.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                dataset.clear();
//                mDatakey.clear();
//                for (DataSnapshot single:dataSnapshot.getChildren()){
//                    dataset.add(single.getValue(Category.class));
//                    mDatakey.add(single.getKey().toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

//    private void init() {
//        re = (RecyclerView) v.findViewById(R.id.recyclerview);
//        re.setLayoutManager(new LinearLayoutManager(getContext()));
//        cc = new FoodAdapter(dataset, mDatakey,getActivity());
//    }


//    private void init() {
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
//    }


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

    //It works!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    View v;
//    private RecyclerView mRecycleView;
//    private List<Category> lstCategory;
//    public HomeFragment() {
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        v = inflater.inflate(R.layout.fragment_home,container,false);
//        mRecycleView = (RecyclerView) v.findViewById(R.id.recyclerview);
//        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstCategory);
//        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecycleView.setAdapter(recyclerViewAdapter);
//        return v;
//    }
//
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        lstCategory = new ArrayList<>();
//        lstCategory.add(new Category("Fish",R.drawable.img1));
//        lstCategory.add(new Category("Chicken",R.drawable.img2));
//        lstCategory.add(new Category("Pasta",R.drawable.img3));
//        lstCategory.add(new Category("Soup",R.drawable.img4));
//        lstCategory.add(new Category("Rise and fish",R.drawable.img5));
//        lstCategory.add(new Category("Soup",R.drawable.img6));
//        lstCategory.add(new Category("Soup",R.drawable.img7));
//        lstCategory.add(new Category("Trimmings with shrimps",R.drawable.img8));
//        lstCategory.add(new Category("Trimmings with fish",R.drawable.img9));
//        lstCategory.add(new Category("Trimmings with chicken",R.drawable.img10));
//    }
    ///END!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!




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
//        }
