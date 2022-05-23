package com.emankhaled.ecommerceapp.Buyer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Config.Config;
import com.emankhaled.ecommerceapp.R;


import org.json.JSONException;

import java.math.BigDecimal;

public class SuccessfullyConfirmActivity extends AppCompatActivity {
    private Button gotopayment , gobackhome;


    Bundle extras;

    String amount ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.grey));
        setContentView(R.layout.activity_successfully_confirm);
        gotopayment = findViewById(R.id.continueToPaymentBtn);
        gobackhome = findViewById(R.id.goHomeBtn);


        gobackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , HomeActivity.class));
            }
        });

        extras = getIntent().getExtras();
        if(extras!=null){
            amount = extras.getString("amount");

        }

        gotopayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() , PaymentActivity.class);
                i.putExtra("amount",amount);
                startActivity(i);

      //              processPayment(amount);

            //    startActivity(new Intent(getApplicationContext() , PaymentActivity.class));
            }
        });
    }

//    @Override
//    protected void onDestroy() {
//        stopService(new Intent(this,PayPalService.class));
//        super.onDestroy();
//    }
//
//    @SuppressLint("MissingSuperCall")
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        if(requestCode==PAYPAL_REQUEST_CODE){
//            if (resultCode ==RESULT_OK){
//                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
//                if (confirmation != null){
//                    try{
//                        String paymentDetails = confirmation.toJSONObject().toString(4);
//                        startActivity(new Intent(this , PaymentDetailsActivity.class)
//                        .putExtra("PaymentDetails",paymentDetails)
//                                .putExtra("PaymentAmount" , amount));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            else if(resultCode == Activity.RESULT_CANCELED){
//                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
//            }
//        }else if(resultCode ==PaymentActivity.RESULT_EXTRAS_INVALID){
//            Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
//
//        }
//
//    }
//
//    private void processPayment(String amount) {
//
//        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount))
//                , "USD" , "Pay For" , PayPalPayment.PAYMENT_INTENT_SALE);
//
//        Intent intent = new Intent(this , PaymentActivity.class);
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
//        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
//        startActivityForResult(intent,PAYPAL_REQUEST_CODE);
//    }
//
//
//
//




}