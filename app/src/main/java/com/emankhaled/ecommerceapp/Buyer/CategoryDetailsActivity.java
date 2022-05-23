package com.emankhaled.ecommerceapp.Buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emankhaled.ecommerceapp.Admin.AdminMaintainProductsActivity;
import com.emankhaled.ecommerceapp.Model.Products;
import com.emankhaled.ecommerceapp.R;
import com.emankhaled.ecommerceapp.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CategoryDetailsActivity extends AppCompatActivity {
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String extras;

  private   TextView categorynameTxt , arrowcat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        arrowcat = findViewById(R.id.arrow_cat);
        extras = getIntent().getStringExtra("catName");
        ProductsRef= FirebaseDatabase.getInstance().getReference().child("Products");
        recyclerView=findViewById(R.id.category_List);
        recyclerView.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(this , 2);
        recyclerView.setLayoutManager(layoutManager);
        categorynameTxt = findViewById(R.id.category_name_txt);
        if (extras!=null){
        categorynameTxt.setText(extras);}


        arrowcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();



                        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                                .setQuery(ProductsRef.orderByChild("ProductStateCat").equalTo("Approved "+extras),Products.class
                                )
                                .build();

                        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                                    @Override
                                    protected void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i, @NonNull final Products products) {


                                        productViewHolder.txtproductname.setText(products.getProductName());


                                        productViewHolder.txtproductprice.setText("Price = " + products.getPrice());

                                        Picasso.get().load(products.getImage()).into(productViewHolder.imageView);

                                        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {


                                                Intent intent = new Intent(CategoryDetailsActivity.this, ProductDetailsActivity.class);
                                                intent.putExtra("pid", products.getPid());
                                                startActivity(intent);
                                            }
                                        });
                                    }

                                    @NonNull
                                    @Override
                                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_items_layout, parent, false);
                                        ProductViewHolder holder = new ProductViewHolder(view);
                                        return holder;
                                    }
                                };

                        recyclerView.setAdapter(adapter);
                        adapter.startListening();
                    }
                }





