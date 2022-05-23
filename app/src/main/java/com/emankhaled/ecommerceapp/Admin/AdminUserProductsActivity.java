package com.emankhaled.ecommerceapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emankhaled.ecommerceapp.Model.Cart;
import com.emankhaled.ecommerceapp.Model.Orders;
import com.emankhaled.ecommerceapp.R;
import com.emankhaled.ecommerceapp.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AdminUserProductsActivity extends AppCompatActivity {


   private RecyclerView productsList;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference cartListRef;
    private TextView backarrow;
    private String userId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_products);

        backarrow = findViewById(R.id.arrow_admin_users_product);
        productsList=(RecyclerView)findViewById(R.id.Products_List);

        productsList.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        productsList.setLayoutManager(layoutManager);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , AdminHomeActivity.class));
                finish();
            }
        });
        userId=getIntent().getStringExtra("uid");
        cartListRef= FirebaseDatabase.getInstance().getReference()
                .child("Orders").child(userId).child("Products");
    }
    @Override
    protected void onStart() {
        super.onStart();




        FirebaseRecyclerOptions<Orders> options=new FirebaseRecyclerOptions.Builder<Orders>()
                .setQuery(cartListRef,Orders.class).build();

        FirebaseRecyclerAdapter<Orders , CartViewHolder> adapter=new FirebaseRecyclerAdapter<Orders, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i, @NonNull Orders cart) {

                cartViewHolder.txtProductQuantity.setText("Quantity = " + cart.getProductQuantity());
                cartViewHolder.txtProductPrice.setText(cart.getProductPrice() + " LE");
                cartViewHolder.txtProductName.setText(cart.getProductName());

                Picasso.get().load(cart.getProductImage()).into(cartViewHolder.productImage);
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent,false);
                CartViewHolder holder=new CartViewHolder(view);
                return holder;
            }
        };

        productsList.setAdapter(adapter);
        adapter.startListening();


    }
}
