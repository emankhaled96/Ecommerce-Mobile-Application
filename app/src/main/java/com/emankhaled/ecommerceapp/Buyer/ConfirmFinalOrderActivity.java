package com.emankhaled.ecommerceapp.Buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFinalOrderActivity extends AppCompatActivity {

    private EditText nameEditText,phoneEditText,homeAddressEditText,cityNameEditText;
    private Button confirmBtn;

    private String totalAmount="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        totalAmount= getIntent().getStringExtra("Total Price");

        Toast.makeText(this, "Total Price = "+totalAmount, Toast.LENGTH_SHORT).show();
        nameEditText=(EditText)findViewById(R.id.shipment_name);
        phoneEditText=(EditText)findViewById(R.id.shipment_phone_no);
        homeAddressEditText=(EditText)findViewById(R.id.shipment_address);
        cityNameEditText=(EditText)findViewById(R.id.shipment_city);
        confirmBtn=(Button)findViewById(R.id.confirm_final_order_btn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });


    }

    private void check() {

        if(TextUtils.isEmpty(nameEditText.getText().toString())){

            Toast.makeText(this, "Please Provide Your Full Name", Toast.LENGTH_SHORT).show();
        }else  if(TextUtils.isEmpty(phoneEditText.getText().toString())){

            Toast.makeText(this, "Please Provide Your Phone Number", Toast.LENGTH_SHORT).show();
        }else  if(TextUtils.isEmpty(homeAddressEditText.getText().toString())){

            Toast.makeText(this, "Please Provide Your Home Address", Toast.LENGTH_SHORT).show();
        }else  if(TextUtils.isEmpty(cityNameEditText.getText().toString())){

            Toast.makeText(this, "Please Provide Your City Name", Toast.LENGTH_SHORT).show();
        }else{

            confirmOrder();
        }
    }

    private void confirmOrder() {

        String saveCurrentDate,saveCurrentTime;

        Calendar calForDate=Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calForDate.getTime());

        final DatabaseReference orderRef= FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUser.getPhone());
        final HashMap<String,Object> orderMap=new HashMap<>();
        orderMap.put("totalAmount",totalAmount);
        orderMap.put("username",nameEditText.getText().toString());
        orderMap.put("userPhone",phoneEditText.getText().toString());
        orderMap.put("homeAddress",homeAddressEditText.getText().toString());
        orderMap.put("cityName",cityNameEditText.getText().toString());
        orderMap.put("time",saveCurrentTime);
        orderMap.put("date",saveCurrentDate);
        orderMap.put("state","not shipped");

        orderRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if(task.isSuccessful()){


                    FirebaseDatabase.getInstance().getReference()
                            .child("Cart List")
                            .child("User View")
                            .child(Prevalent.currentOnlineUser.getPhone()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(ConfirmFinalOrderActivity.this, "Your Final Order Has Been Placed Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(ConfirmFinalOrderActivity.this, HomeActivity.class);

                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();

                            }

                        }
                    });
                }
            }
        });

    }
}
