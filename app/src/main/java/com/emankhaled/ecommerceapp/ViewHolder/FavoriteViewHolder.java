package com.emankhaled.ecommerceapp.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emankhaled.ecommerceapp.Interface.ItemClickListener;

import com.emankhaled.ecommerceapp.R;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FavoriteViewHolder extends RecyclerView.ViewHolder implements ItemClickListener {

    public TextView favProductName,favProductPrice;
    public ItemClickListener favItemClickListener;
    public ImageView favImage;
    public Button removeBtn;
    DatabaseReference removeFavRef;

    public FavoriteViewHolder(@NonNull View itemView) {
        super(itemView);

        favProductName = itemView.findViewById(R.id.fav_product_name);
        favProductPrice = itemView.findViewById(R.id.fav_product_price);
        favImage = itemView.findViewById(R.id.fav_product_image);
        removeBtn = itemView.findViewById(R.id.removeFromFav);
    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {
        favItemClickListener.onClick(view,getAdapterPosition(),false);
    }

    public void setFavItemClickListener(ItemClickListener favItemClickListener) {
        this.favItemClickListener = favItemClickListener;
    }

    public void setRemoveFavoriteIcon(final String position) {
        removeFavRef = FirebaseDatabase.getInstance().getReference("Favorite List");


    }
}
