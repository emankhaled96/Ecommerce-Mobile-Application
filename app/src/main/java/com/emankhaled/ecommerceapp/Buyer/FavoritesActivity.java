package com.emankhaled.ecommerceapp.Buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Interface.ItemClickListener;
import com.emankhaled.ecommerceapp.Model.Favorites;
import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.emankhaled.ecommerceapp.ViewHolder.FavoriteViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView favRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TextView favArrow;
    DatabaseReference favoriteListRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoriteListRef = FirebaseDatabase.getInstance().getReference().child("Favorite List");
        favArrow = findViewById(R.id.arrow_fav);
        favRecyclerView = findViewById(R.id.Fav_List);
        favRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        favRecyclerView.setLayoutManager(layoutManager);


        favArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Favorites> options =
                new FirebaseRecyclerOptions.Builder<Favorites>().setQuery(favoriteListRef.child(Prevalent.currentOnlineUser.getPhone())
                        .child("Products"), Favorites.class).build();



        FirebaseRecyclerAdapter<Favorites , FavoriteViewHolder> adapter =
                new FirebaseRecyclerAdapter<Favorites, FavoriteViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull FavoriteViewHolder favoriteViewHolder, int i, @NonNull final Favorites favorites) {

                        favoriteViewHolder.favProductName.setText(favorites.getPname());
                        favoriteViewHolder.favProductPrice.setText(favorites.getPrice()+" LE");
                        Picasso.get().load(favorites.getPimage()).into(favoriteViewHolder.favImage);

                        favoriteViewHolder.removeBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                favoriteListRef.child(Prevalent.currentOnlineUser.getPhone()).child("Products").child(favorites.getPid())
                                        .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        Toast.makeText(getApplicationContext(), "Product is Deleted from your favorites Successfully", Toast.LENGTH_SHORT).show();

//                                        Intent i=new Intent(getApplicationContext(),  HomeActivity.class);
//                                        startActivity(i);
                                    }
                                });

                            }
                        });

                    }

                    @NonNull
                    @Override
                    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item_layout,parent, false);
                        FavoriteViewHolder holder = new FavoriteViewHolder(view);
                        return holder;
                    }
                };
        favRecyclerView.setAdapter(adapter);
        adapter.startListening();

    }

}