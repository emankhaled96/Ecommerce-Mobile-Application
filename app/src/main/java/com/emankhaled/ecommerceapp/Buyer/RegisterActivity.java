package com.emankhaled.ecommerceapp.Buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button createAccountBtn;

    private EditText inputName, inputPhoneNumber, inputPassword;
    DatabaseReference RootRef;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         RootRef =  FirebaseDatabase.getInstance().getReference().child("Users");


        createAccountBtn = (Button) findViewById(R.id.register_btn);

        inputName = (EditText) findViewById(R.id.register_username_input);

        inputPhoneNumber = (EditText) findViewById(R.id.register_phonenumber_input);

        inputPassword = (EditText) findViewById(R.id.register_password_input);

        loadingBar=new ProgressDialog(this);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAccount();
            }
        });


    }

    public void createAccount(){

        String name=inputName.getText().toString();
        String phoneNo=inputPhoneNumber.getText().toString();
        String password=inputPassword.getText().toString();

        if(TextUtils.isEmpty(name)){

            Toast.makeText(RegisterActivity.this,"Please enter your name...",Toast.LENGTH_SHORT).show();

        }

        else if(TextUtils.isEmpty(phoneNo)){

            Toast.makeText(RegisterActivity.this,"Please enter your phone number...",Toast.LENGTH_SHORT).show();

        }

        else if(TextUtils.isEmpty(password)){

            Toast.makeText(RegisterActivity.this,"Please enter your password...",Toast.LENGTH_SHORT).show();

        }
        else{


            loadingBar.setTitle("Create Account");

            loadingBar.setMessage("Please wait , while we check the credentials");

            loadingBar.setCanceledOnTouchOutside(false);

            loadingBar.show();

            ValidatePhoneNumber(name,phoneNo,password);


        }




    }
    private void  ValidatePhoneNumber(final String name, final String phoneNo, final String password){

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(phoneNo)){




                    Toast.makeText(RegisterActivity.this, "this " + phoneNo + " already exists", Toast.LENGTH_SHORT).show();

                    loadingBar.dismiss();

                    Toast.makeText(RegisterActivity.this, "please try again using another phone number", Toast.LENGTH_SHORT).show();



                }else{

                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phoneNo);

                    userdataMap.put("name", name);

                    userdataMap.put("password", password);

                    RootRef.child(phoneNo).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(RegisterActivity.this, "Congratulation , Your Account Has Been Created", Toast.LENGTH_SHORT).show();

                                        loadingBar.dismiss();


                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);


                                    } else {

                                        Toast.makeText(RegisterActivity.this, "Network Error , please try again after some time", Toast.LENGTH_SHORT).show();

                                        loadingBar.dismiss();


                                    }

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
