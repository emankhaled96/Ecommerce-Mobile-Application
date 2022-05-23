package com.emankhaled.ecommerceapp.Sellers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Admin.AdminCheckNewProductsActivity;
import com.emankhaled.ecommerceapp.Buyer.MainActivity;
import com.emankhaled.ecommerceapp.Model.Products;
import com.emankhaled.ecommerceapp.R;
import com.emankhaled.ecommerceapp.ViewHolder.ProductViewHolder;
import com.emankhaled.ecommerceapp.ViewHolder.SellerItemViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SellerHomeActivity extends AppCompatActivity {
private TextView mTextView;
private View toolbar;


    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference unverifiedProductsRef;

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()){

                case R.id.navigation_home:
                    Intent intent1=new Intent(SellerHomeActivity.this, SellerHomeActivity.class);
                    startActivity(intent1);
                    return true;

                case R.id.navigation_add:
                    Intent intent=new Intent(SellerHomeActivity.this, SellerProductCategoryActivity.class);
                    startActivity(intent);

                    return true;

                case R.id.navigation_logout:
                    final FirebaseAuth mAuth;
                    mAuth=FirebaseAuth.getInstance();
                    mAuth.signOut();



                    Intent i=new Intent(SellerHomeActivity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();
                    return true;

            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_seller_home);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(itemSelectedListener);

        recyclerView=findViewById(R.id.seller_home_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(this , 2);
        recyclerView.setLayoutManager(layoutManager);

        unverifiedProductsRef= FirebaseDatabase.getInstance().getReference().child("Products");


    }
    // to go to the home screen when clicking the back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products> options=
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(unverifiedProductsRef.orderByChild("sellerID")
                                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()),Products.class)
                        .build();


        FirebaseRecyclerAdapter<Products, SellerItemViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, SellerItemViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull SellerItemViewHolder productViewHolder, int i, @NonNull final Products products) {

                        productViewHolder.txtproductname.setText(products.getProductName());

                        productViewHolder.txtproductstate.setText("State = " +products.getProductState());

                        productViewHolder.txtproductdescription.setText(products.getDescription());

                        productViewHolder.txtproductprice.setText("Price = "+products.getPrice());

                        Picasso.get().load(products.getImage()).into(productViewHolder.imageView);

                        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final String productID=products.getPid();

                                CharSequence options[]=new CharSequence[]{

                                        "Yes",
                                        "No"

                                };
                                final AlertDialog.Builder builder=new AlertDialog.Builder(SellerHomeActivity.this);
                                builder.setTitle("Do You Want To Delete This Product?");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        if(which==0){

                                            DeleteProduct(productID);
                                        }else if(which==1){



                                        }
                                    }
                                });
                                builder.show();

                            }

                        });



                    }

                    @NonNull
                    @Override
                    public SellerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_item_view, parent, false);

                        SellerItemViewHolder holder=new SellerItemViewHolder(view);

                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void DeleteProduct(String productID) {

        unverifiedProductsRef.child(productID).removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        if(task.isSuccessful()){

                            Toast.makeText(SellerHomeActivity.this, "This Item Has Been Deleted successfully", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    }
