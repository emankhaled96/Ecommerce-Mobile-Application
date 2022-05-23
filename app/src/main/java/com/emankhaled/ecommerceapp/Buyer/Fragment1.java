package com.emankhaled.ecommerceapp.Buyer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emankhaled.ecommerceapp.Model.AdminOrders;
import com.emankhaled.ecommerceapp.Model.Orders;
import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.emankhaled.ecommerceapp.ViewHolder.AdminOrdersViewHolder;
import com.emankhaled.ecommerceapp.ViewHolder.OrdersViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class Fragment1 extends Fragment {

    private ImageView emptyimg;

    private TextView sorryTxt, noItemTxt;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Orders, OrdersViewHolder> adapterproducts ;
    FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder> adapterorderTime ;
    DatabaseReference orderTimeRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v =  inflater.inflate(R.layout.fragment1_layout,container,false);
        recyclerView = v.findViewById(R.id.order_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        emptyimg =v.findViewById(R.id.order_empty_fragment);
        sorryTxt = v.findViewById(R.id.confirmation_text_order_fragment);
        noItemTxt = v.findViewById(R.id.confirmation_text_regular_order_fragment);


      

        return v;

    }



    @Override
    public void onStart() {
        super.onStart();
       checkOrderState();
        orderTimeRef = FirebaseDatabase.getInstance().getReference().child("Orders");

        FirebaseRecyclerOptions<AdminOrders> options = new FirebaseRecyclerOptions.Builder<AdminOrders>()
                .setQuery(orderTimeRef.orderByChild("userphone").equalTo(Prevalent.currentOnlineUser.getPhone()),AdminOrders.class).build();


        adapterorderTime = new FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdminOrdersViewHolder adminOrdersViewHolder, int i, @NonNull AdminOrders adminOrders) {
                adminOrdersViewHolder.orderTimeDate.setText(adminOrders.getOrderid());
                FirebaseRecyclerOptions<Orders> options2 = new FirebaseRecyclerOptions.Builder<Orders>()
                        .setQuery(orderTimeRef.child(adminOrders.getOrderid()).child("Products")
                                ,Orders.class).build();


                adapterproducts = new FirebaseRecyclerAdapter<Orders, OrdersViewHolder>(options2) {
                    @Override
                    protected void onBindViewHolder(@NonNull OrdersViewHolder ordersViewHolder, int i, @NonNull Orders orders) {
                        ordersViewHolder.txtProductName.setText(orders.getProductName());
                        ordersViewHolder.txtProductPrice.setText(orders.getProductPrice()+" $ / Piece");

                        ordersViewHolder.txtProductQuantity.setText("Quantity = "+orders.getProductQuantity());
                        Picasso.get().load(orders.getProductImage()).into(ordersViewHolder.productImage);

                        ordersViewHolder.orderAgain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(),ProductDetailsActivity.class);
                                intent.putExtra("pid" , orders.getPid());
                                startActivity(intent);
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v2 = LayoutInflater.from(getActivity()).inflate(R.layout.order_layout_view1,parent,false);

                        return new OrdersViewHolder(v2);
                    }
                };

                adapterproducts.startListening();
                adapterproducts.notifyDataSetChanged();
                adminOrdersViewHolder.order_products_list.setAdapter(adapterproducts);

            }

            @NonNull
            @Override
            public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v1 = LayoutInflater.from(getActivity()).inflate(R.layout.order_layout_view2,parent,false);

                return new AdminOrdersViewHolder(v1);
            }
        };
        adapterorderTime.startListening();
        adapterorderTime.notifyDataSetChanged();
        recyclerView.setAdapter(adapterorderTime);

    }

    private void checkOrderState(){

        DatabaseReference ordersRef;
        ordersRef= FirebaseDatabase.getInstance().getReference().child("Orders");

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int counter = 0;

            if (dataSnapshot.exists()) {
              for (DataSnapshot ds : dataSnapshot.getChildren()) {

                String userphone = ds.child("userphone").getValue().toString();

                  if (userphone.equals(Prevalent.currentOnlineUser.getPhone())) {

                      counter++;

                     Log.d("Counter",String.valueOf(counter));



                   }

              }

                if (counter > 0){
                    recyclerView.setVisibility(View.VISIBLE);
                    emptyimg.setVisibility(View.INVISIBLE);
                    sorryTxt.setVisibility(View.INVISIBLE);
                    noItemTxt.setVisibility(View.INVISIBLE);
                }else if (counter == 0){

                        recyclerView.setVisibility(View.GONE);
                        emptyimg.setVisibility(View.VISIBLE);
                        sorryTxt.setVisibility(View.VISIBLE);
                        noItemTxt.setVisibility(View.VISIBLE);
                }


    }else{

        recyclerView.setVisibility(View.GONE);
        emptyimg.setVisibility(View.VISIBLE);
        sorryTxt.setVisibility(View.VISIBLE);
        noItemTxt.setVisibility(View.VISIBLE);

    }
}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
