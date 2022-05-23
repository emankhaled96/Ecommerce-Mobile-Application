package com.emankhaled.ecommerceapp.Buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Model.Cart;
import com.emankhaled.ecommerceapp.Model.Favorites;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFinalOrderActivity extends AppCompatActivity {

    private EditText nameEditText,phoneEditText,homeAddressEditText,cityNameEditText;
    private Button confirmBtn;
    private TextView priceTxt ,arrowcon;
    private String totalAmount="" , productID = "";
    ArrayList<String> myList;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);


        arrowcon = findViewById(R.id.arrow_con);
      arrowcon.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(getApplicationContext() , CartActivity.class));
              finish();
          }
      });

      extras = getIntent().getExtras();
      if(extras!=null){
          totalAmount= extras.getString("Total Price");


          myList = extras.getStringArrayList("pidList");
      }

        priceTxt = findViewById(R.id.txtPrice);
        priceTxt.setText("Total Price = "+totalAmount + " $");
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
      //  Log.d("pidList",myList.toString());

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

        SimpleDateFormat currentTime=new SimpleDateFormat("h:mm a");
        saveCurrentTime=currentTime.format(calForDate.getTime());

        final DatabaseReference orderRef= FirebaseDatabase.getInstance().getReference().child("Orders").child(saveCurrentDate+" "+saveCurrentTime);

        final HashMap<String,Object> orderMap=new HashMap<>();
        orderMap.put("orderid",saveCurrentDate + " " + saveCurrentTime);
        orderMap.put("orderphone",phoneEditText.getText().toString());
        orderMap.put("totalamount",totalAmount);
        orderMap.put("username",nameEditText.getText().toString());
        orderMap.put("userphone",Prevalent.currentOnlineUser.getPhone());
        orderMap.put("homeaddress",homeAddressEditText.getText().toString());
        orderMap.put("cityname",cityNameEditText.getText().toString());
        orderMap.put("time",saveCurrentTime);
        orderMap.put("date",saveCurrentDate);
        orderMap.put("state","not shipped");

        orderRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {



                if(task.isSuccessful()){


                    for (String id : myList){
                        FirebaseDatabase.getInstance().getReference().child("Cart List").child("User View")
                                .child(Prevalent.currentOnlineUser.getPhone()).child("Products")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        Cart products;

                                        for (DataSnapshot ds : snapshot.getChildren()) {
                                            products = ds.getValue(Cart.class);

                                            if (products.getPid().equals(id)) {
                                                final DatabaseReference productRef= FirebaseDatabase.getInstance().getReference().child("Orders")
                                                        .child(saveCurrentDate+" "+saveCurrentTime).child("Products").child(id);

                                                final HashMap<String,Object> orderdetailsMap=new HashMap<>();
                                                orderdetailsMap.put("Pid",id);
                                                orderdetailsMap.put("ProductName",products.getPname());
                                                orderdetailsMap.put("ProductPrice",products.getPrice());
                                                orderdetailsMap.put("ProductImage",products.getPimage());
                                                orderdetailsMap.put("ProductQuantity",products.getQuantity());

                                                productRef.updateChildren(orderdetailsMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

//
                                                        if (task.isSuccessful()){
                                                            Log.d("Products","Added");

                                                            FirebaseDatabase.getInstance().getReference()
                                                                    .child("Cart List")
                                                                    .child("User View")
                                                                    .child(Prevalent.currentOnlineUser.getPhone()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                    if(task.isSuccessful()){

                                                                        Toast.makeText(ConfirmFinalOrderActivity.this, "Your Final Order Has Been Placed Successfully", Toast.LENGTH_SHORT).show();

                                                                        Intent intent=new Intent(ConfirmFinalOrderActivity.this, SuccessfullyConfirmActivity.class);
                                                                        intent.putExtra("amount" , totalAmount);
                                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                        startActivity(intent);
                                                                        finish();

                                                                    }

                                                                }
                                                            });
                                                        }
                                                        else{
                                                            Log.d("Products",task.getException().toString());
                                                        }

                                                    }
                                                });


                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                    }


                }
            }
        });

    }

}
