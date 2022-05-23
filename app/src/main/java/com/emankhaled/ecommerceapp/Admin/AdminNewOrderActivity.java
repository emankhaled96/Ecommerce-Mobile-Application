package com.emankhaled.ecommerceapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Model.AdminOrders;
import com.emankhaled.ecommerceapp.Model.Cart;
import com.emankhaled.ecommerceapp.Model.Favorites;
import com.emankhaled.ecommerceapp.Model.Products;
import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminNewOrderActivity extends AppCompatActivity {

    private RecyclerView ordersList;

    private DatabaseReference ordersRef;
    private DatabaseReference updateRef;
    private DatabaseReference cartlistRef;
    private TextView backarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_order);

        ordersRef= FirebaseDatabase.getInstance().getReference().child("Orders");

        cartlistRef= FirebaseDatabase.getInstance().getReference().child("Cart List");

        updateRef = FirebaseDatabase.getInstance().getReference().child("Previous Orders");
        ordersList=(RecyclerView)findViewById(R.id.Orders_List);

        ordersList.setLayoutManager(new LinearLayoutManager(this));


        backarrow = findViewById(R.id.arrow_admin_new_order);
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


        FirebaseRecyclerOptions<AdminOrders> options=
                new FirebaseRecyclerOptions.Builder<AdminOrders>()
                .setQuery(ordersRef,AdminOrders.class)
                .build();

        FirebaseRecyclerAdapter<AdminOrders,AdminOrdersViewHolder> adapter=
                new FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdminOrdersViewHolder adminOrdersViewHolder, @SuppressLint("RecyclerView") final int i, @NonNull final AdminOrders adminOrders) {


                adminOrdersViewHolder.username.setText(adminOrders.getUsername());

                adminOrdersViewHolder.userPhoneNo.setText("Phone Number : "+adminOrders.getOrderphone());

                adminOrdersViewHolder.orderTotalPrice.setText("Total Price : " +adminOrders.getTotalamount() + " LE");

                adminOrdersViewHolder.orderDateTime.setText("Time : " +adminOrders.getDate() +" "+ adminOrders.getTime());

                adminOrdersViewHolder.userShippingAddress.setText("Address : " + adminOrders.getHomeaddress() + " , "+ adminOrders.getCityname());

                adminOrdersViewHolder.showOrderedProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String uID=getRef(i).getKey();
                        Intent intent=new Intent(AdminNewOrderActivity.this, AdminUserProductsActivity.class);
                        intent.putExtra("uid",uID);
                        startActivity(intent);
                    }
                });

                adminOrdersViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        CharSequence options[] = new CharSequence[]
                                {

                                        "Yes",
                                        "No"

                                };

                        AlertDialog.Builder builder = new AlertDialog.Builder(AdminNewOrderActivity.this);

                        builder.setTitle("Have You Shipped This Order's Products ?");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(which==0){

                                    String uID=getRef(i).getKey();

                                    removeOrderAndUpdatePreviousOrders(uID);


                                }else{

                                    finish();

                                }
                            }
                        });

                        builder.show();

                    }
                });
            }


            @NonNull
            @Override
            public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout,parent,false);

               return new AdminOrdersViewHolder(view);
            }
        };

        ordersList.setAdapter(adapter);
        adapter.startListening();
    }

    // uID == buyer phone number
    private void removeOrderAndUpdatePreviousOrders(String uID) {


        cartlistRef.child("Admin View").child(uID).child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Cart products;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    products = ds.getValue(Cart.class);

                    final HashMap<String,Object> previousOrderMap=new HashMap<>();
                    previousOrderMap.put("pid",products.getPid());
                    previousOrderMap.put("pname",products.getPname());
                    previousOrderMap.put("pimage" , products.getPimage());
                    previousOrderMap.put("price",products.getPrice());
                    previousOrderMap.put("Quantity",products.getQuantity());
                    updateRef.child(uID).child("Products").updateChildren(previousOrderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(getApplicationContext(), "Orders Updated", Toast.LENGTH_SHORT).show();
                            ordersRef.child(uID).removeValue();
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder{


        public TextView username,userPhoneNo,orderTotalPrice,userShippingAddress,orderDateTime,showOrderedProduct;


        public AdminOrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            username=itemView.findViewById(R.id.order_username);

            userPhoneNo=itemView.findViewById(R.id.order_phone_number);

            orderTotalPrice=itemView.findViewById(R.id.order_total_price);

            userShippingAddress=itemView.findViewById(R.id.order_address_city);

            orderDateTime=itemView.findViewById(R.id.order_date_time);

            showOrderedProduct=itemView.findViewById(R.id.show_all_products_btn);

        }
    }
}
