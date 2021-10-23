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

import com.emankhaled.ecommerceapp.Buyer.MainActivity;
import com.emankhaled.ecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SellerRegistrationActivity extends AppCompatActivity {

    private Button sellerLoginButton,registerButton;
    private EditText nameInput,phoneInput,emailInput,passwordInput,shopInput;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registration);

        loadingBar=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        sellerLoginButton=(Button)findViewById(R.id.seller_already_have_account_btn);
        registerButton=(Button)findViewById(R.id.seller_register_btn);

        nameInput=(EditText)findViewById(R.id.seller_name);
        phoneInput=(EditText)findViewById(R.id.seller_phone);
        passwordInput=(EditText)findViewById(R.id.seller_password);
        emailInput=(EditText)findViewById(R.id.seller_email);
        shopInput=(EditText)findViewById(R.id.seller_address);



        sellerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerRegistrationActivity.this,SellerLoginActivity.class);
                startActivity(intent);
            }
        });
        
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerSeller();
            }
        });
    }

    private void registerSeller() {

        final String name=nameInput.getText().toString();
        final String phone=phoneInput.getText().toString();
        final String password=passwordInput.getText().toString();
        final String email=emailInput.getText().toString();
        final String address=shopInput.getText().toString();

        if(!name.equals("") && !password.equals("") && !phone.equals("") && !email.equals("") && !address.equals("")){


            loadingBar.setTitle("Creating seller Account");

            loadingBar.setMessage("Please wait , while we check the credentials");

            loadingBar.setCanceledOnTouchOutside(false);

            loadingBar.show();
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){


                                final DatabaseReference rootRef;
                                rootRef= FirebaseDatabase.getInstance().getReference();
                                String sid=mAuth.getCurrentUser().getUid();

                                HashMap<String,Object> sellerMap=new HashMap<>();

                                sellerMap.put("sid",sid);
                                sellerMap.put("phone",phone);
                                sellerMap.put("email",email);
                                sellerMap.put("password",password);
                                sellerMap.put("address",address);
                                sellerMap.put("name",name);

                                rootRef.child("Sellers").child(sid).updateChildren(sellerMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if(task.isSuccessful()){


                                                    loadingBar.dismiss();

                                                    Toast.makeText(SellerRegistrationActivity.this, "You are Registered Successfully", Toast.LENGTH_SHORT).show();
                                                    Intent intent=new Intent(SellerRegistrationActivity.this, SellerHomeActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        });

                            }
                        }
                    });




        }else{

            Toast.makeText(this, "Please Complete The Registration Form", Toast.LENGTH_SHORT).show();
        }
    }
}
