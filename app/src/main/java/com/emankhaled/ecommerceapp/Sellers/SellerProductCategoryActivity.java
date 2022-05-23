package com.emankhaled.ecommerceapp.Sellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emankhaled.ecommerceapp.Buyer.HomeActivity;
import com.emankhaled.ecommerceapp.R;

public class SellerProductCategoryActivity extends AppCompatActivity {

    private ImageView tshirts,sportshirts,femaledresses, sweater;

    private ImageView glasses,pursesBags,hatsCaps,shoes;

    private ImageView headPhonesHandFree,laptops,watches,mobiles;
    private TextView backarrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_category);


        backarrow = findViewById(R.id.arrow_seller_cat);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , SellerHomeActivity.class));
                finish();
            }
        });
        tshirts=(ImageView)findViewById(R.id.t_shirts);

        sportshirts=(ImageView)findViewById(R.id.sports_t_shirts);

        femaledresses=(ImageView)findViewById(R.id.female_dresses);

        sweater =(ImageView)findViewById(R.id.sweater);
        glasses=(ImageView)findViewById(R.id.glasses);

        pursesBags=(ImageView)findViewById(R.id.purses_bags_wallets);

        hatsCaps=(ImageView)findViewById(R.id.hats_caps);
        shoes=(ImageView)findViewById(R.id.shoes);

        headPhonesHandFree=(ImageView)findViewById(R.id.headphones_handfree);

        laptops=(ImageView)findViewById(R.id.laptop_pc);

        mobiles =(ImageView)findViewById(R.id.mobilephones);

        watches=(ImageView)findViewById(R.id.watches);

        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Shirts");

                startActivity(intent);
            }
        });

        sportshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Sport Shirts");

                startActivity(intent);
            }
        });

        femaledresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Dresses");

                startActivity(intent);
            }
        });

        sweater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Sweaters");

                startActivity(intent);
            }
        });

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Glasses");

                startActivity(intent);
            }
        });

        pursesBags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Bags");

                startActivity(intent);
            }
        });


        hatsCaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Hats");

                startActivity(intent);
            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Shoes");

                startActivity(intent);
            }
        });



        headPhonesHandFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Headphones");

                startActivity(intent);
            }
        });

        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Laptops");

                startActivity(intent);
            }
        });
        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Watches");

                startActivity(intent);
            }
        });

        mobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","SmartPhones");

                startActivity(intent);
            }
        });







    }
}
