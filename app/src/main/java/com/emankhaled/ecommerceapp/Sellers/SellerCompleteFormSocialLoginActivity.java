package com.emankhaled.ecommerceapp.Sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SellerCompleteFormSocialLoginActivity extends AppCompatActivity {

    private EditText usernameTxt , phoneNumberTxt , addressTxt;

    private Button doneBtn;
    private ProgressDialog loadingBar;
    Bundle extras;
    String sid , semail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_complete_form_social_login);



        usernameTxt = findViewById(R.id.seller_sociallogin_name);
        phoneNumberTxt = findViewById(R.id.seller_sociallogin_phone);
        addressTxt = findViewById(R.id.seller_sociallogin_address);
        doneBtn = findViewById(R.id.seller_done_btn);
        loadingBar=new ProgressDialog(this);


        extras = getIntent().getExtras();
        if(extras!=null){

            sid = extras.getString("userID");
            semail = extras.getString("useremail");

        }

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerSeller(sid , semail);
            }
        });

    }

    private void registerSeller(String sid, String semail) {

        String name = usernameTxt.getText().toString();
        String phone = phoneNumberTxt.getText().toString();
        String address = addressTxt.getText().toString();

        if(!name.equals("") && !phone.equals("")  && !address.equals("")){


            loadingBar.setTitle("Creating seller Account");

            loadingBar.setMessage("Please wait , while we check the credentials");

            loadingBar.setCanceledOnTouchOutside(false);

            loadingBar.show();

            final DatabaseReference rootRef;
            rootRef= FirebaseDatabase.getInstance().getReference();

            HashMap<String,Object> sellerMap=new HashMap<>();

            sellerMap.put("sid",sid);
            sellerMap.put("phone",phone);
            sellerMap.put("email",semail);
            sellerMap.put("address",address);
            sellerMap.put("name",name);

            rootRef.child("Sellers").child(sid).updateChildren(sellerMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {


                        loadingBar.dismiss();

                        Toast.makeText(getApplicationContext(), "You are Logged in Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SellerHomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        loadingBar.dismiss();

                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }
}}