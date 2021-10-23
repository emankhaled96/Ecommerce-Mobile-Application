package com.emankhaled.ecommerceapp.Buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.emankhaled.ecommerceapp.Model.Products;
import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailsActivity extends AppCompatActivity {

    private Button addtocartbtn;
    private TextView productName,productDescription,productPrice;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private String productID="",state="Normal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        addtocartbtn=(Button)findViewById(R.id.Pd_add_to_cart_btn);
        productID=getIntent().getStringExtra("pid");
        productPrice=(TextView)findViewById(R.id.product_price_detail);
        productDescription=(TextView)findViewById(R.id.product_description_detail);
        productName=(TextView)findViewById(R.id.product_name_detail);
        productImage=(ImageView)findViewById(R.id.product_image_detail);
        numberButton=(ElegantNumberButton)findViewById(R.id.number_btn);


        getProductDetails(productID);

        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(state.equals("order shipped") || state.equals("order placed")){

                    Toast.makeText(ProductDetailsActivity.this, "you can purchase another order once you receive your first one ", Toast.LENGTH_LONG).show();


                }else{
                addingToCartList();
            }}
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkOrderState();
    }

    private void addingToCartList() {

        String saveCurrentDate,saveCurrentTime;

        Calendar calForDate=Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calForDate.getTime());

        final DatabaseReference cartListRef=FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String,Object>  cartMap=new HashMap<>();
        cartMap.put("pid",productID);
        cartMap.put("pname",productName.getText().toString());
        cartMap.put("price",productPrice.getText().toString());
        cartMap.put("time",saveCurrentTime);
        cartMap.put("date",saveCurrentDate);
        cartMap.put("Quantity",numberButton.getNumber());
        cartMap.put("discount","");
        cartListRef.child("User View").child(Prevalent.currentOnlineUser.getPhone()).child("Products").child(productID).updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        if(task.isSuccessful()){
                        cartListRef.child("Admin View").child(Prevalent.currentOnlineUser.getPhone()).child("Products").child(productID).updateChildren(cartMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        Toast.makeText(ProductDetailsActivity.this, "Added Successfully To The Cart", Toast.LENGTH_SHORT).show();

                                        Intent intent=new Intent(ProductDetailsActivity.this, HomeActivity.class);

                                        startActivity(intent);
                                    }
                                });
                    }}
                });


    }

    private void getProductDetails(String productID) {

        DatabaseReference productRef= FirebaseDatabase.getInstance().getReference().child("Products");
        productRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    Products products=dataSnapshot.getValue(Products.class);
                    productName.setText(products.getProductName());
                    productDescription.setText(products.getDescription());
                    productPrice.setText(products.getPrice());
                    Picasso.get().load(products.getImage()).into(productImage);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void checkOrderState(){

        DatabaseReference ordersRef;
        ordersRef=FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUser.getPhone());

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String shippingstate=dataSnapshot.child("state").getValue().toString();

                    String username=dataSnapshot.child("username").getValue().toString();

                    if(shippingstate.equals("shipped")){


                        state="order shipped";
                    }else if(shippingstate.equals("not shipped")){


                        state="order placed";


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
