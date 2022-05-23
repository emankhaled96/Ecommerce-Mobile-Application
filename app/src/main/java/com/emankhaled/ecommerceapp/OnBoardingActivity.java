package com.emankhaled.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emankhaled.ecommerceapp.Buyer.MainActivity;

public class OnBoardingActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView nextTxt , skipTxt;
int currentpos;
    TextView[] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);


        nextTxt = findViewById(R.id.next_Txt);
        skipTxt = findViewById(R.id.skip_Txt);
        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.dotslinearlayout);


        sliderAdapter = new SliderAdapter(this);

        viewPager.setAdapter(sliderAdapter);
        addDots(0);
viewPager.addOnPageChangeListener(changeListener);
    }

    private void addDots(int position){


        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for(int i=0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(".");
            dots[i].setTextSize(60);

            dotsLayout.addView(dots[i]);


        }


dots[position].setTextColor(getResources().getColor(R.color.pink));

    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentpos = position;
            if(position ==0){
                nextTxt.setText("Next");
            }else if(position==1){
                nextTxt.setText("Next");

            }else {
                nextTxt.setText("Let's get started");

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void skip(View view){
        startActivity(new Intent(getApplicationContext() , MainActivity.class));
        finish();
    }

    public void next(View view){
        if(nextTxt.getText().equals("Next")){
        viewPager.setCurrentItem(currentpos + 1);
    }else{
            startActivity(new Intent(getApplicationContext() , MainActivity.class));
            finish();
        }
    }
}