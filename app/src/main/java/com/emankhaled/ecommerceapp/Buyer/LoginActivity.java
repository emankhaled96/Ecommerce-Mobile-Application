
package com.emankhaled.ecommerceapp.Buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Admin.AdminHomeActivity;
import com.emankhaled.ecommerceapp.Sellers.SellerProductCategoryActivity;
import com.emankhaled.ecommerceapp.Model.Users;
import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private Button LoginBtn;

    private EditText  inputPhoneNumber, inputPassword;

    private ProgressDialog loadingBar;

    private CheckBox chkBoxRememberMe;

    private TextView AdminLink,NotAdminLink,forgetPasswordLink;

    private String ParentDbName="Users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgetPasswordLink=(TextView)findViewById(R.id.forget_password_link);

        LoginBtn = (Button) findViewById(R.id.login_btn);

        inputPhoneNumber = (EditText) findViewById(R.id.login_phonenumber_input);

        inputPassword = (EditText) findViewById(R.id.login_password_input);

        chkBoxRememberMe=(CheckBox)findViewById(R.id.remember_me_chkb);

        AdminLink=(TextView)findViewById(R.id.admin_panel_link);

        NotAdminLink=(TextView)findViewById(R.id.not_admin_panel_link);

        Paper.init(this);

        loadingBar=new ProgressDialog(this);

        forgetPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, ResetPasswordActivity.class);
                intent.putExtra("check","login");
                startActivity(intent);
            }
        });
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginBtn.setText("Login As Admin");

                AdminLink.setVisibility(View.INVISIBLE);

                NotAdminLink.setVisibility(View.VISIBLE);

                ParentDbName="Admins";
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoginBtn.setText("Login");

                AdminLink.setVisibility(View.VISIBLE);

                NotAdminLink.setVisibility(View.INVISIBLE);

                ParentDbName="Users";
            }
        });
    }

    private void LoginUser() {

        String phoneNo=inputPhoneNumber.getText().toString();
        String password=inputPassword.getText().toString();


           if(TextUtils.isEmpty(phoneNo)){

            Toast.makeText(LoginActivity.this,"Please enter your phone number...",Toast.LENGTH_SHORT).show();

        }

        else if(TextUtils.isEmpty(password)){

            Toast.makeText(LoginActivity.this,"Please enter your password...",Toast.LENGTH_SHORT).show();

        }

        else{

               loadingBar.setTitle("Login Account");

               loadingBar.setMessage("Please wait , while we check the credentials");

               loadingBar.setCanceledOnTouchOutside(false);

               loadingBar.show();

               AllowAcsesstoAccount(phoneNo,password);





           }

    }

    private void AllowAcsesstoAccount(final String phoneNo, final String password) {

        if(chkBoxRememberMe.isChecked()){

            Paper.book().write(Prevalent.UserPhoneKey,phoneNo);

            Paper.book().write(Prevalent.UserPasswordKey,password);

        }

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(ParentDbName).child(phoneNo).exists()){


                    Users userData=dataSnapshot.child(ParentDbName).child(phoneNo).getValue(Users.class);

                    if(userData.getPhone().equals(phoneNo)){

                        if(userData.getPassword().equals(password)){

                            if(ParentDbName.equals("Admins")){

                            Toast.makeText(LoginActivity.this,"Welcome Admin! You are Logged in sucessfully...",Toast.LENGTH_SHORT).show();

                            loadingBar.dismiss();

                            Intent intent=new Intent(LoginActivity.this, AdminHomeActivity.class);
                            startActivity(intent);
                            }else if (ParentDbName.equals("Users")){

                                Toast.makeText(LoginActivity.this,"Logged in sucessfully...",Toast.LENGTH_SHORT).show();

                                loadingBar.dismiss();

                                Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                Prevalent.currentOnlineUser=userData;

                            }
                        }else{

                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this,"Password is incorrect.",Toast.LENGTH_SHORT).show();


                        }

                    }

                }else{


                    Toast.makeText(LoginActivity.this,"account with this "+phoneNo+" number does not exist",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
