package com.emankhaled.ecommerceapp.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emankhaled.ecommerceapp.Interface.ItemClickListener;
import com.emankhaled.ecommerceapp.Model.Favorites;
import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtproductname,txtproductdescription,txtproductprice;
    public ImageView imageView;
    public ItemClickListener listener;

    public Button favoriteBtn;
    DatabaseReference favRef;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView=(ImageView) itemView.findViewById(R.id.product_image);

        txtproductname=(TextView) itemView.findViewById(R.id.product_name);

        txtproductprice=(TextView) itemView.findViewById(R.id.product_price);


        favoriteBtn = itemView.findViewById(R.id.favourite_btn) ;

    }

    public void setItemClickListener(ItemClickListener listener){

        this.listener=listener;
    }
    @Override
    public void onClick(View v) {

        listener.onClick(v,getAdapterPosition(),false);

    }
    public void setFavoriteIcon(final String position) {
        favoriteBtn = itemView.findViewById(R.id.favourite_btn);
        favRef = FirebaseDatabase.getInstance().getReference("Favorite List");


        favRef.child(Prevalent.currentOnlineUser.getPhone()).child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Favorites favorites;

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    favorites = ds.getValue(Favorites.class);

                    if (favorites.getPid().equals(position)) {

                        favoriteBtn.setBackgroundResource(R.drawable.ic_baseline_favorite_pink_24);
                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
