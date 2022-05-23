package com.emankhaled.ecommerceapp.Buyer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.emankhaled.ecommerceapp.Admin.AdminHomeActivity;
import com.emankhaled.ecommerceapp.Admin.AdminMaintainProductsActivity;
import com.emankhaled.ecommerceapp.Model.Products;
import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.emankhaled.ecommerceapp.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private DatabaseReference ProductsRef, favoriteListRef ;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private TextView allproductstxt;
    private Button morebtn , dressbtn , watchbtn , bagbtn , mobilebtn;
    private String type ="";
    Boolean checked = false;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ProductsRef=FirebaseDatabase.getInstance().getReference().child("Products");

        allproductstxt = findViewById(R.id.textView);
        favoriteListRef =FirebaseDatabase.getInstance().getReference().child("Favorite List");

        dressbtn = findViewById(R.id.dressBtn);
        watchbtn = findViewById(R.id.watchBtn);
        bagbtn = findViewById(R.id.bagBtn);
        mobilebtn = findViewById(R.id.mobileBtn);


        morebtn = findViewById(R.id.morebtn);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle != null){

            type=getIntent().getExtras().get("Admin").toString();

        }

        Paper.init(this);

        morebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,CategoriesActivity.class));
            }
        });
        dressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName","Dresses");
                startActivity(i);
            }
        });
        watchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName","Watches");
                startActivity(i);
            }
        });
        bagbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName","Bags");
                startActivity(i);
            }
        });
        mobilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,CategoryDetailsActivity.class);
                i.putExtra("catName","SmartPhones");
                startActivity(i);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!type.equals("Admin")){

                    Intent intent=new Intent(HomeActivity.this, CartActivity.class);
                    startActivity(intent);

                }


            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView=navigationView.getHeaderView(0);

        TextView userNameTextView=headerView.findViewById(R.id.user_profile_name);

        CircleImageView profileImageView=headerView.findViewById(R.id.user_profile_image);

if (type.equals("Admin")){
    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    toolbar.setNavigationIcon(getDrawable(R.drawable.ic_baseline_arrow_back_ios_24));

    allproductstxt.setTextColor(getResources().getColor(R.color.blue));
    toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
    getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext() , AdminHomeActivity.class));
            finish();
        }
    });
   // new ColorDrawable(getResources().getColor(android.R.color.transparent))
    toolbar.setTitle("Products");
    fab.setVisibility(View.GONE);
}
   if(!type.equals("Admin")){

       userNameTextView.setText(Prevalent.currentOnlineUser.getName());

       Picasso.get().load(Prevalent.currentOnlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);
   }
        recyclerView=findViewById(R.id.Recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(this , 2);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products> options=new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(ProductsRef.orderByChild("ProductState").equalTo("Approved"),Products.class)
                .build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter=
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i, @NonNull final Products products) {

                        productViewHolder.txtproductname.setText(products.getProductName());


                        productViewHolder.txtproductprice.setText("Price = "+products.getPrice()+ " $");

                        Picasso.get().load(products.getImage()).into(productViewHolder.imageView);


                        final String postkey  =getRef(i).getKey();

                        Log.d("postion",postkey);
                        if (type .equals("Admin")){
                            productViewHolder.txtproductname.setTextColor(getResources().getColor(R.color.blue));
                            productViewHolder.txtproductprice.setTextColor(getResources().getColor(R.color.blue));

                        }else{
                            productViewHolder.setFavoriteIcon(postkey);


                            productViewHolder.favoriteBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if (checked){
                                        favoriteListRef.child(Prevalent.currentOnlineUser.getPhone()).child("Products").child(products.getPid())
                                                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Toast.makeText(getApplicationContext(), "Product is Deleted from your favorites Successfully", Toast.LENGTH_SHORT).show();
                                                productViewHolder.favoriteBtn.setBackgroundResource(R.drawable.ic_baseline_favorite_border_pink_24);

                                                checked = false;
                                            }
                                        });
                                    }else {

                                        addToFavorites(products.getPid(), products.getProductName(), products.getPrice(), products.getImage());
                                        productViewHolder.favoriteBtn.setBackgroundResource(R.drawable.ic_baseline_favorite_pink_24);
                                    }

                                }
                            });

                        }

                        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {

                          if(type.equals("Admin")){

                              Intent intent=new Intent(HomeActivity.this, AdminMaintainProductsActivity.class);
                              intent.putExtra("pid",products.getPid());

                              startActivity(intent);

                          }else{
                          Intent intent = new Intent(HomeActivity.this, ProductDetailsActivity.class);
                          intent.putExtra("pid",products.getPid());
                          startActivity(intent);
                      }}
                  });
                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                      if(type.equals("Admin")){
                          View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_items_layout,parent,false);
                          ProductViewHolder holder=new ProductViewHolder(view);
                          return holder;
                      }else{
                          View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout,parent,false);
                          ProductViewHolder holder=new ProductViewHolder(view);
                          return holder;
                      }

                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    private void addToFavorites(String productID ,String name , String price ,String image) {
        checked = true;

        final HashMap<String,Object> favMap=new HashMap<>();
        favMap.put("pid",productID);
        favMap.put("pname",name);
        favMap.put("price",price);
        favMap.put("pimage",image);

        favMap.put("favoritestate","checked");

        favoriteListRef.child(Prevalent.currentOnlineUser.getPhone()).child("Products").child(productID).updateChildren(favMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(HomeActivity.this, "Added Successfully To your favourites", Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(HomeActivity.this, FavoritesActivity.class);
//                            startActivity(intent);

                        }}
                });

    }

    // to go to the home screen when clicking the back button
    @Override
    public void onBackPressed() {
        if(type.equals("Admin")){
            startActivity(new Intent(getApplicationContext() , AdminHomeActivity.class));
            finish();
        }else{
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

//        if (id == R.id.action_settings)
//        {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cart)

        {
            if(!type.equals("Admin")){

                Intent intent=new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);


            }



        }
        else if (id == R.id.nav_search)
        {
            if(!type.equals("Admin")){


                Intent intent=new Intent(HomeActivity.this, SearchProductActivity.class);
                startActivity(intent);

            }


        }
        else if (id == R.id.nav_fav)
        {
            if(!type.equals("Admin")) {
                Intent intent = new Intent(HomeActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        }

        else if (id == R.id.nav_categories)
        {
            if (!type.equals("Admin")) {

                startActivity(new Intent(HomeActivity.this, CategoriesActivity.class));
            }
        }
        else if (id == R.id.nav_settings)
        {
            if(!type.equals("Admin")){


                Intent intent=new Intent(HomeActivity.this, SettingsActivity.class);

                startActivity(intent);

            }

        }else if (id == R.id.nav_orders)
        {
            if(!type.equals("Admin")){


                Intent intent=new Intent(HomeActivity.this, OrderActivity.class);

                startActivity(intent);

            }}
        else if (id == R.id.nav_logout)
        {
            if(!type.equals("Admin")){


                Paper.book().destroy();

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }


}