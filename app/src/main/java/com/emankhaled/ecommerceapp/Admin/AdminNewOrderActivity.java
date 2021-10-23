package com.emankhaled.ecommerceapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.emankhaled.ecommerceapp.Model.AdminOrders;
import com.emankhaled.ecommerceapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminNewOrderActivity extends AppCompatActivity {

    private RecyclerView ordersList;

    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_order);

        ordersRef= FirebaseDatabase.getInstance().getReference().child("Orders");

        ordersList=(RecyclerView)findViewById(R.id.Orders_List);

        ordersList.setLayoutManager(new LinearLayoutManager(this));


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
            protected void onBindViewHolder(@NonNull AdminOrdersViewHolder adminOrdersViewHolder, final int i, @NonNull final AdminOrders adminOrders) {

                adminOrdersViewHolder.username.setText("Name : " +adminOrders.getUsername());

                adminOrdersViewHolder.userPhoneNo.setText("Phone : "+adminOrders.getUserPhone());

                adminOrdersViewHolder.orderTotalPrice.setText("Total Price = $" +adminOrders.getTotalAmount());

                adminOrdersViewHolder.orderDateTime.setText("Ordered at " +adminOrders.getDate() +" "+ adminOrders.getTime());

                adminOrdersViewHolder.userShippingAddress.setText("Shipping Address : " + adminOrders.getHomeAddress() + " , "+ adminOrders.getCityName());

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

                                    removeOrder(uID);


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

    private void removeOrder(String uID) {


        ordersRef.child(uID).removeValue();
    }

    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder{


        public TextView username,userPhoneNo,orderTotalPrice,userShippingAddress,orderDateTime;

        public Button showOrderedProduct;

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
