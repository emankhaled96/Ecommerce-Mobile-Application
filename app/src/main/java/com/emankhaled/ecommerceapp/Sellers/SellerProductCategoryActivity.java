package com.emankhaled.ecommerceapp.Sellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.emankhaled.ecommerceapp.Admin.AdminNewOrderActivity;
import com.emankhaled.ecommerceapp.Buyer.HomeActivity;
import com.emankhaled.ecommerceapp.Buyer.MainActivity;
import com.emankhaled.ecommerceapp.R;

public class SellerProductCategoryActivity extends AppCompatActivity {

    private ImageView tshirts,sportshirts,femaledresses,sweather;

    private ImageView glasses,pursesBags,hatsCaps,shoes;

    private ImageView headPhonesHandFree,laptops,watches,mobiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_category);


        tshirts=(ImageView)findViewById(R.id.t_shirts);

        sportshirts=(ImageView)findViewById(R.id.sports_t_shirts);

        femaledresses=(ImageView)findViewById(R.id.female_dresses);

        sweather=(ImageView)findViewById(R.id.sweather);
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

                intent.putExtra("Category","Tshirts");

                startActivity(intent);
            }
        });

        sportshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Sports Shirts");

                startActivity(intent);
            }
        });

        femaledresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Female Dresses");

                startActivity(intent);
            }
        });

        sweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Sweathers");

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

                intent.putExtra("Category","Purses Bags Wallets");

                startActivity(intent);
            }
        });


        hatsCaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Hats Caps");

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

                intent.putExtra("Category","HeadPhones HandFree");

                startActivity(intent);
            }
        });

        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);

                intent.putExtra("Category","Laptops PC");

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

                intent.putExtra("Category","MobilePhones");

                startActivity(intent);
            }
        });







    }
}
