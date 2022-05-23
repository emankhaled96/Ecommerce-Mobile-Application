package com.emankhaled.ecommerceapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emankhaled.ecommerceapp.Buyer.HomeActivity;
import com.emankhaled.ecommerceapp.Buyer.MainActivity;
import com.emankhaled.ecommerceapp.R;
import com.emankhaled.ecommerceapp.Sellers.SellerProductCategoryActivity;

public class AdminHomeActivity extends AppCompatActivity {

    private Button logoutBtn,checkNewOrderBtn,maintainProductsBtn,checkApproveProductsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        logoutBtn=(Button)findViewById(R.id.admin_logout_btn);

        checkApproveProductsBtn=(Button)findViewById(R.id.approve_new_products_Btn);


        checkNewOrderBtn=(Button)findViewById(R.id.check_Order_Btn);

        maintainProductsBtn=(Button)findViewById(R.id.maintain_Btn);
        checkApproveProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminHomeActivity.this,AdminCheckNewProductsActivity.class);
                startActivity(intent);

            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminHomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        checkNewOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminHomeActivity.this, AdminNewOrderActivity.class);
                startActivity(intent);
            }
        });

        maintainProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminHomeActivity.this, HomeActivity.class);
                intent.putExtra("Admin","Admin");
                startActivity(intent);


            }
        });
    }
}
