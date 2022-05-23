package com.emankhaled.ecommerceapp.Buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Model.Products;
import com.emankhaled.ecommerceapp.R;
import com.emankhaled.ecommerceapp.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SearchProductActivity extends AppCompatActivity {


    private Button searchBtn;
    private EditText inputText;
    private RecyclerView searchList;
    private String searchInput , handlingOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        searchBtn = (Button) findViewById(R.id.search_product_Btn);
        inputText = (EditText) findViewById(R.id.search_product_Category_name);
        searchList = (RecyclerView) findViewById(R.id.search_list);

        searchList.setLayoutManager(new GridLayoutManager(SearchProductActivity.this, 2));

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchInput = inputText.getText().toString();
                handlingOutput =  handlingSearchWord(searchInput);

                onStart();
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Products");
        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(reference.orderByChild("ProductStateCat").equalTo("Approved " +handlingOutput), Products.class).build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i, @NonNull final Products products) {



                        productViewHolder.txtproductname.setText(products.getProductName());


                        productViewHolder.txtproductprice.setText("Price = " + products.getPrice());

                        Picasso.get().load(products.getImage()).into(productViewHolder.imageView);

                        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(SearchProductActivity.this, ProductDetailsActivity.class);
                                intent.putExtra("pid", products.getPid());
                                startActivity(intent);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_items_layout, parent, false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };


        searchList.setAdapter(adapter);
        adapter.startListening();

    }


    public String handlingSearchWord(String input) {

        if (input.equals("Dresses") ||
                input.equals("dresses") ||
                input.equals("dress")
                || input.equals("Dress")||
                input.equals("d")
                || input.equals("dr")||
                input.equals("D")
                || input.equals("Dr")) {

            return "Dresses";
        }else if (input.equals("Shirts") ||
                input.equals("shirt") ||
                input.equals("Shirt")
                || input.equals("shirts")||
                input.equals("Shi")
                || input.equals("shi")){
            return "Shirts";
        }else if (input.equals("Sweaters") ||
                input.equals("Sweater") ||
                input.equals("sweater")
                || input.equals("sweaters")||
                input.equals("Sw")
                || input.equals("sw")){
            return "Sweaters";
        }
        else if (input.equals("SmartPhones") ||
                input.equals("smartPhones") ||
                input.equals("smartPhone")
                || input.equals("SmartPhone") ||
                input.equals("smartphone")||
                input.equals("smartphones")||
                input.equals("sm")
                || input.equals("Sm")||
                input.equals("phone")
                || input.equals("Phone")||
                input.equals("Phones")
                || input.equals("phones")||
        input.equals("Smart Phones") ||
                input.equals("smart Phones") ||
                input.equals("smart Phone")
                || input.equals("Smart Phone") ||
                input.equals("smart phone")||
                input.equals("smart phones")){
            return "SmartPhones";
        }
        else if (input.equals("Watches") ||
                input.equals("watches") ||
                input.equals("watch")
                || input.equals("Watch")||
                input.equals("W")
                || input.equals("w")||
                input.equals("Wa")
                || input.equals("wa")){
            return "Watches";
        }
        else if (input.equals("Bags") ||
                input.equals("bag") ||
                input.equals("Bag")
                || input.equals("bags")||
                input.equals("B")
                || input.equals("Ba")||
                input.equals("b")
                || input.equals("ba")){
            return "Bags";
        }
        else if (input.equals("Hats") ||
                input.equals("hat") ||
                input.equals("Hat")
                || input.equals("hats")||
                input.equals("H")
                || input.equals("Ha")||
                input.equals("h")
                || input.equals("ha")){
            return "Hats";
        }
        else if (input.equals("Sport Shirts") ||
                input.equals("sport shirt") ||
                input.equals("sport shirts")
                || input.equals("Sport Shirt") ||
                input.equals("SportShirt")
                || input.equals("sportshirt")
                || input.equals("Sport shirt") ||
                input.equals("Sport wear")||
                input.equals("Sp")
                || input.equals("sp")||
                input.equals("sport")
                || input.equals("Sport")){
            return "Sport Shirts";
        }
        else if (input.equals("Shoes") ||
                input.equals("shoes") ||
                input.equals("Shoe")
                || input.equals("shoe")||
                input.equals("Sho")
                || input.equals("sho")){
            return "Shoes";
        }
        else if (input.equals("Glasses") ||
                input.equals("glasses") ||
                input.equals("glass")
                || input.equals("Glass")||
                input.equals("G")
                || input.equals("Gl")||
                input.equals("g")
                || input.equals("gl")){
            return "Glasses";
        }
        else if (input.equals("Laptops") ||
                input.equals("laptop") ||
                input.equals("Laptop")
                || input.equals("laptops")||
                input.equals("L")
                || input.equals("La")||
                input.equals("l")
                || input.equals("la")||
                input.equals("Computer")
                || input.equals("computer")){
            return "Laptops";
        } else if (input.equals("Headphones") ||
                input.equals("headphones") ||
                input.equals("Headphone")
                || input.equals("headphone")||
                input.equals("Handfree")||
                input.equals("handfree")
                ||
                input.equals("Head phone")
                || input.equals("head phone")||
                input.equals("Head phones")
                || input.equals("head phones")||
                input.equals("Earbuds")
                || input.equals("earbuds")||
                input.equals("Earbud")
                || input.equals("earbud") ||
                input.equals("H")
                || input.equals("He")||
                input.equals("h")
                || input.equals("he")||
        input.equals("e")
                || input.equals("ea")||
                input.equals("Ea")
                || input.equals("E")){
            return "Headphones";

        }else{
            Toast.makeText(getApplicationContext(), "Please Enter an Exact Category Name", Toast.LENGTH_SHORT).show();
        }
        return "";
    }
}