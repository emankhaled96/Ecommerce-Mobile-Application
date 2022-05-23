package com.emankhaled.ecommerceapp.Buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Model.Cart;
import com.emankhaled.ecommerceapp.Model.Products;
import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.emankhaled.ecommerceapp.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {


    private Button nextProcessBtn;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView emptyimg;
    private TextView sorryTxt , noItemTxt , arrowcart;
    private int overallPrice=0;
    private String productID="";

    ArrayList <String> pidList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        pidList = new ArrayList<>();

        arrowcart = findViewById(R.id.arrow_cart);
        emptyimg = findViewById(R.id.emptycartImg);
        sorryTxt = findViewById(R.id.sorry);
        noItemTxt = findViewById(R.id.noitemsTxt);
       arrowcart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),HomeActivity.class));
               finish();
           }
       });
        recyclerView=findViewById(R.id.Cart_List);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        nextProcessBtn=(Button)findViewById(R.id.next_btn);


        nextProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nextProcessBtn.getText().equals("Confirm Your Order")) {
                    Intent intent = new Intent(CartActivity.this, ConfirmFinalOrderActivity.class);

                    intent.putExtra("Total Price", String.valueOf(overallPrice));

                    intent.putExtra("pidList" , pidList);

                    //intent.putExtra("productID" , productID);
                    startActivity(intent);

                    finish();
                }else{
                    Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                    startActivity(intent);

                    finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

checkCartState();
        final DatabaseReference cartListRef=FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options=
                new FirebaseRecyclerOptions.Builder<Cart>().setQuery(cartListRef.child("User View")
                .child(Prevalent.currentOnlineUser.getPhone()).child("Products"),Cart.class).build();


        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter=
                new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i, @NonNull final Cart cart) {


                        cartViewHolder.txtProductQuantity.setText("Quantity = "+cart.getQuantity());
                        cartViewHolder.txtProductPrice.setText("Price = "+cart.getPrice());
                        cartViewHolder.txtProductName.setText(cart.getPname());
                        Picasso.get().load(cart.getPimage()).into(cartViewHolder.productImage);


                        productID = cart.getPid();
                        pidList.add(productID);


                        int oneTypeProductTotalPrice = Integer.parseInt(cart.getPrice()) * Integer.parseInt(cart.getQuantity());


                        overallPrice=overallPrice+oneTypeProductTotalPrice;


                        cartViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                CharSequence options[]=new CharSequence[]
                                        {
                                                "Edit",
                                                "Delete"


                                        };

                                AlertDialog.Builder builder=new AlertDialog.Builder(CartActivity.this);

                                builder.setTitle("Cart Options");

                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {


                                        if(which==0){
                                            Intent intent=new Intent(CartActivity.this, ProductDetailsActivity.class);
                                            intent.putExtra("pid",cart.getPid());
                                            startActivity(intent);

                                        }else if (which==1){


                                            cartListRef.child("User View").child(Prevalent.currentOnlineUser.getPhone()).child("Products").child(cart.getPid())
                                                    .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {


                                                    if(task.isSuccessful()){

                                                        pidList.remove(cart.getPid());



                                                                Toast.makeText(CartActivity.this, "Item Deleted Successfully", Toast.LENGTH_SHORT).show();


                                                            }


                                                    }
                                                });
                                            }
                                        }



                                });
                                builder.show();
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent,false);
                        CartViewHolder holder=new CartViewHolder(view);
                        return holder;

                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }




    private void checkCartState(){

        DatabaseReference ordersRef;
        ordersRef=FirebaseDatabase.getInstance().getReference().child("Cart List").child("User View").child(Prevalent.currentOnlineUser.getPhone());

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){


                    recyclerView.setVisibility(View.VISIBLE);
                    emptyimg.setVisibility(View.INVISIBLE);
                    sorryTxt.setVisibility(View.INVISIBLE);
                    noItemTxt.setVisibility(View.INVISIBLE);
                    nextProcessBtn.setText("Confirm Your Order");

                }else{

                    recyclerView.setVisibility(View.GONE);
                    emptyimg.setVisibility(View.VISIBLE);
                    sorryTxt.setVisibility(View.VISIBLE);
                    noItemTxt.setVisibility(View.VISIBLE);
                    nextProcessBtn.setText("Go Shopping");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    private void checkOrderState(){

        DatabaseReference ordersRef;
        ordersRef=FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUser.getPhone());

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String shippingstate=dataSnapshot.child("state").getValue().toString();

                    String username=dataSnapshot.child("username").getValue().toString();

                    if(shippingstate.equals("shipped")){


                     //   totalPriceTextView.setText("Dear"+ username + "\n order is shipped successfully");

                        recyclerView.setVisibility(View.GONE);
                       // txtMsg1.setVisibility(View.VISIBLE);
                        nextProcessBtn.setVisibility(View.GONE);

                        Toast.makeText(CartActivity.this, "you can purchase another order once you receive your first one ", Toast.LENGTH_SHORT).show();
                    }else if(shippingstate.equals("not shipped")){


                        //totalPriceTextView.setText("Shipping State = Not Shipped");

                        //txtMsg1.setText("Congratulations, Your Final Order has been shipped successfully,Soon you will receive your Order at your door step");
                        recyclerView.setVisibility(View.GONE);
                        //txtMsg1.setVisibility(View.VISIBLE);
                        nextProcessBtn.setVisibility(View.GONE);

                        Toast.makeText(CartActivity.this, "you can purchase another order once you receive your first one ", Toast.LENGTH_SHORT).show();



                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }}
