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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SellerLoginActivity extends AppCompatActivity {

    private Button sellerloginButton;
    private EditText emailInput,passwordInput;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

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

                    }
                }
            });
        }else{

            Toast.makeText(this, "Please Complete The Form", Toast.LENGTH_SHORT).show();
        }
    }
}
