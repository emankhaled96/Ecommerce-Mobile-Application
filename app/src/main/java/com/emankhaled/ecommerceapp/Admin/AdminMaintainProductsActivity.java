package com.emankhaled.ecommerceapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.R;
import com.emankhaled.ecommerceapp.Sellers.SellerProductCategoryActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AdminMaintainProductsActivity extends AppCompatActivity {


    private Button applyChangesBtn,deleteProductBtn;
    private EditText name,price,description;
    private ImageView imageView;
    private String productID="";
    private DatabaseReference productsRef;
    private TextView backarrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_admin_maintain_products);

        backarrow = findViewById(R.id.arrow_admin_product_details);

        productID= getIntent().getStringExtra("pid");
        productsRef= FirebaseDatabase.getInstance().getReference().child("Products").child(productID);

        deleteProductBtn=(Button)findViewById(R.id.delete_product_Btn);
        applyChangesBtn=(Button)findViewById(R.id.apply_changes_Btn);
        name=(EditText) findViewById(R.id.product_name_maintain);
        price=(EditText) findViewById(R.id.product_price_maintain);
        description=(EditText) findViewById(R.id.product_description_maintain);
        imageView=(ImageView)findViewById(R.id.product_image_maintain);

        displaySpecificProductInfo();


        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , AdminHomeActivity.class));
                finish();
            }
        });
        applyChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applychanges();
            }
        });

        deleteProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteThisProduct();
            }
        });

    }

    private void deleteThisProduct() {
        productsRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){


                    Intent intent=new Intent(AdminMaintainProductsActivity.this, SellerProductCategoryActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(AdminMaintainProductsActivity.this, "This Product is deleted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void applychanges() {

        String pname=name.getText().toString();
        String pPrice=price.getText().toString();
        String pDescription=description.getText().toString();
        
        
        if(pname.equals("")){

            Toast.makeText(this, "write down product name", Toast.LENGTH_SHORT).show();
        }else if(pPrice.equals("")) {

            Toast.makeText(this, "write down product price", Toast.LENGTH_SHORT).show();
        }else if(pDescription.equals("")) {

            Toast.makeText(this, "write down product description", Toast.LENGTH_SHORT).show();
        }else{



            HashMap<String,Object> productMap=new HashMap<>();

            productMap.put("Pid",productID);

            productMap.put("Description",pDescription);

            productMap.put("Price",pPrice);


            productMap.put("ProductName",pname);

            productsRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){

                        Toast.makeText(AdminMaintainProductsActivity.this, "Changes Applied Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(AdminMaintainProductsActivity.this, SellerProductCategoryActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void displaySpecificProductInfo() {


        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String pname=dataSnapshot.child("ProductName").getValue().toString();
                    String pprice=dataSnapshot.child("Price").getValue().toString();
                    String pdescription=dataSnapshot.child("Description").getValue().toString();
                    String pimage=dataSnapshot.child("Image").getValue().toString();


                    name.setText(pname);
                    price.setText(pprice);
                    description.setText(pdescription);
                    Picasso.get().load(pimage).into(imageView);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
