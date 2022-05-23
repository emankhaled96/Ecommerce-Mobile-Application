package com.emankhaled.ecommerceapp.Sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SellerLoginActivity extends AppCompatActivity {

    private Button sellerloginButton;
    private EditText emailInput,passwordInput;
    private ProgressDialog loadingBar;
    public FirebaseAuth mAuth;
    private ImageView googleBtn , facebookBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_seller_login);


        googleBtn = findViewById(R.id.googleBtn);
        facebookBtn = findViewById(R.id.facebookBtn);

        mAuth=FirebaseAuth.getInstance();
        loadingBar=new ProgressDialog(this);
        sellerloginButton=(Button)findViewById(R.id.seller_login_btn);

        emailInput=(EditText)findViewById(R.id.seller_login_email);
        passwordInput=(EditText)findViewById(R.id.seller_login_password);
        
        sellerloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSeller();
            }
        });


        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext() , SellerGoogleSigningActivity.class));
            }
        });

        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , SellerFacebookSigninActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loginSeller() {

        final String password=passwordInput.getText().toString();
        final String email=emailInput.getText().toString();

        if(!password.equals("") && !email.equals("")){

            loadingBar.setTitle("Seller Account Login");

            loadingBar.setMessage("Please wait , while we check the credentials");

            loadingBar.setCanceledOnTouchOutside(false);

            loadingBar.show();




            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    if(task.isSuccessful()){
                        loadingBar.dismiss();

                        Intent intent=new Intent(SellerLoginActivity.this,SellerHomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();

                    }else {

                        loadingBar.dismiss();
                        if(task.getException().getMessage().toString().equals("The password is invalid or the user does not have a password.")) {
                            Toast.makeText(getApplicationContext(), "You entered a wrong password ", Toast.LENGTH_SHORT).show();
                            //   Toast.makeText(getApplicationContext(), "You entered a wrong email or pass", Toast.LENGTH_SHORT).show();
                        }else if(task.getException().getMessage().toString().equals("There is no user record corresponding to this identifier. The user may have been deleted.")){
                            Toast.makeText(getApplicationContext(), "You Entered a Wrong Email , Please Register first ", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(SellerLoginActivity.this,SellerRegistrationActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(), "Something Wrong , Please Try again ", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            });
        }else{

            Toast.makeText(this, "Please Complete The Form", Toast.LENGTH_SHORT).show();
        }
    }
}
