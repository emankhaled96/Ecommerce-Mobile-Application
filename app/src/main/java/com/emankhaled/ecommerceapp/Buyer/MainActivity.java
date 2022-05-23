package com.emankhaled.ecommerceapp.Buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Model.Users;
import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.emankhaled.ecommerceapp.Sellers.SellerHomeActivity;
import com.emankhaled.ecommerceapp.Sellers.SellerRegistrationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button joinNowButton,LoginButton;

    private ProgressDialog loadingBar;
    private TextView sellerBegin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sellerBegin=(TextView)findViewById(R.id.seller_begin);


        joinNowButton=(Button)findViewById(R.id.main_join_now_btn);
        LoginButton=(Button)findViewById(R.id.main_login_btn);

        sellerBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SellerRegistrationActivity.class);
                startActivity(intent);

            }
        });
        loadingBar=new ProgressDialog(this);
        Paper.init(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        String UserPhoneKey=Paper.book().read(Prevalent.UserPhoneKey);

        String UserPasswordKey=Paper.book().read(Prevalent.UserPasswordKey);

        if(UserPhoneKey !="" && UserPasswordKey !=""){

            if(!TextUtils.isEmpty(UserPasswordKey)  && !TextUtils.isEmpty(UserPhoneKey)){

                AllowAccess(UserPhoneKey,UserPasswordKey);

                loadingBar.setTitle("Already Logged in");

                loadingBar.setMessage("Please wait.... ");

                loadingBar.setCanceledOnTouchOutside(false);

                loadingBar.show();


            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser!=null){



            Intent intent=new Intent(MainActivity.this, SellerHomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();


        }
    }

    private void AllowAccess(final String phoneNo, final String password) {

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Users").child(phoneNo).exists()){


                    Users userData=dataSnapshot.child("Users").child(phoneNo).getValue(Users.class);

                    if(userData.getPhone().equals(phoneNo)){

                        if(userData.getPassword().equals(password)){

                            Toast.makeText(MainActivity.this,"Please wait! You are already Logged in...",Toast.LENGTH_SHORT).show();

                            loadingBar.dismiss();

                            Intent intent=new Intent(MainActivity.this, HomeActivity.class);
                            Prevalent.currentOnlineUser=userData;
                            startActivity(intent);
                        }else{

//                            loadingBar.dismiss();
//                            Toast.makeText(MainActivity.this,"Password is incorrect.",Toast.LENGTH_SHORT).show();
//

                        }

                    }

                }else{


                    Toast.makeText(MainActivity.this,"account with this "+phoneNo+" number does not exist",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
