package com.emankhaled.ecommerceapp.Buyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emankhaled.ecommerceapp.R;

public class CategoriesActivity extends AppCompatActivity {
    private ImageView dressImg , shirtImg , sweaterImg;
    private ImageView smartphonesImg , watchesImg , bagsImg;
    private ImageView hatsImg , sportshirtImg , headphonesImg;
    private ImageView glassesImg , shoesImg , laptopImg;

    private TextView dressTxt , shirtTxt , sweaterTxt;
    private TextView smartphonesTxt , watchesTxt , bagsTxt;
    private TextView hatsTxt , sportshirtTxt , headphonesTxt;
    private TextView glassesTxt , shoesTxt , laptopTxt ,arrowcats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        arrowcats = findViewById(R.id.arrow_cats);

        getWindow().setStatusBarColor(getResources().getColor(R.color.darkblue));

        dressImg=(ImageView)findViewById(R.id.buyer_dress);
        shirtImg=(ImageView)findViewById(R.id.buyer_shirts);
        sweaterImg=(ImageView)findViewById(R.id.buyer_sweater);
        smartphonesImg =(ImageView)findViewById(R.id.buyer_smartphones);
        watchesImg=(ImageView)findViewById(R.id.buyer_watches);
        bagsImg=(ImageView)findViewById(R.id.buyer_bags);
        hatsImg=(ImageView)findViewById(R.id.buyer_hats);
        sportshirtImg=(ImageView)findViewById(R.id.buyer_sport_shirts);
        headphonesImg=(ImageView)findViewById(R.id.buyer_headphones);
        glassesImg=(ImageView)findViewById(R.id.buyer_glasses);
        shoesImg =(ImageView)findViewById(R.id.buyer_shoes);
        laptopImg=(ImageView)findViewById(R.id.buyer_laptop);



        dressTxt=findViewById(R.id.buyer_dress_txt);
        shirtTxt=findViewById(R.id.buyer_shirts_txt);
        sweaterTxt=findViewById(R.id.buyer_sweater_txt);
        smartphonesTxt =findViewById(R.id.buyer_smartphones_txt);
        watchesTxt=findViewById(R.id.buyer_watches_txt);
        bagsTxt=findViewById(R.id.buyer_bags_txt);
        hatsTxt=findViewById(R.id.buyer_hats_txt);
        sportshirtTxt=findViewById(R.id.buyer_sport_shirts_txt);
        headphonesTxt=findViewById(R.id.buyer_headphones_txt);
        glassesTxt=findViewById(R.id.buyer_glasses_txt);
        shoesTxt =findViewById(R.id.buyer_shoes_txt);
        laptopTxt=findViewById(R.id.buyer_laptop_txt);

        arrowcats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , HomeActivity.class));
                finish();
            }
        });

        dressImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName",dressTxt.getText().toString());
                startActivity(i);


            }
                });
        shirtImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName",shirtTxt.getText().toString());
                startActivity(i);
            }
        });
        sweaterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName",sweaterTxt.getText().toString());
                startActivity(i);
            }
});


        watchesImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName",watchesTxt.getText().toString());
                startActivity(i);
            }
        });

        smartphonesImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName",smartphonesTxt.getText().toString());
                startActivity(i);
            }
        });

        bagsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName",bagsTxt.getText().toString());
                startActivity(i);
            }
        });

        hatsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName",hatsTxt.getText().toString());
                startActivity(i);
            }
        });

        sportshirtImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName",sportshirtTxt.getText().toString());
                startActivity(i);
            }
        });

        headphonesImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName",headphonesTxt.getText().toString());
                startActivity(i);
            }
        });

        glassesImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName",glassesTxt.getText().toString());
                startActivity(i);
            }
        });

        shoesImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName",shoesTxt.getText().toString());
                startActivity(i);
            }
        });

        laptopImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName",laptopTxt.getText().toString());
                startActivity(i);
            }
        });



    }
}