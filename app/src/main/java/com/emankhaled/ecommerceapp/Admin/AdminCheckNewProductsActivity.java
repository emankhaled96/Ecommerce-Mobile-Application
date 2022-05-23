package com.emankhaled.ecommerceapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Interface.ItemClickListener;
import com.emankhaled.ecommerceapp.Model.Products;
import com.emankhaled.ecommerceapp.R;
import com.emankhaled.ecommerceapp.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AdminCheckNewProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference unverifiedProductsRef;
    private TextView backarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_check_new_products);

        backarrow = findViewById(R.id.arrow_admin_new_product);
        recyclerView=findViewById(R.id.admin_ProductsCheckList);
        recyclerView.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        unverifiedProductsRef= FirebaseDatabase.getInstance().getReference().child("Products");

   backarrow.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           startActivity(new Intent(getApplicationContext() , AdminHomeActivity.class));
           finish();
       }
   });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options=
                new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(unverifiedProductsRef.orderByChild("ProductState")
                        .equalTo("Not Approved"),Products.class)
                        .build();


        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i, @NonNull final Products products) {

                        productViewHolder.txtproductname.setText(products.getProductName());


                        productViewHolder.txtproductprice.setText("Price = "+products.getPrice());

                        Picasso.get().load(products.getImage()).into(productViewHolder.imageView);

                        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final String productID=products.getPid();
                                final String productCat = products.getCategory();

                                CharSequence options[]=new CharSequence[]{

                                        "Yes",
                                        "No"

                                };
                                final AlertDialog.Builder builder=new AlertDialog.Builder(AdminCheckNewProductsActivity.this);
                                builder.setTitle("Do You Want To Approve This Product?");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        if(which==0){

                                            changeProductState(productID ,productCat );
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
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_items_layout, parent, false);

                        ProductViewHolder holder=new ProductViewHolder(view);

                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void changeProductState(String productID , String productCategory) {


        unverifiedProductsRef.child(productID).child("ProductState").setValue("Approved")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        if(task.isSuccessful()){


                            unverifiedProductsRef.child(productID).child("ProductStateCat").setValue("Approved "+ productCategory)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {



                                        }
                                    });
                            Toast.makeText(AdminCheckNewProductsActivity.this, "This Item Has Been Approved , It Is Available For Selling", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}
