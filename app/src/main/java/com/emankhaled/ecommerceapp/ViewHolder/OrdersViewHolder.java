package com.emankhaled.ecommerceapp.ViewHolder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emankhaled.ecommerceapp.Interface.ItemClickListener;
import com.emankhaled.ecommerceapp.Model.Orders;
import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class OrdersViewHolder extends RecyclerView.ViewHolder implements ItemClickListener {

    public TextView txtProductName,txtProductPrice,txtProductQuantity,orderAgain;
    public ImageView productImage;

    public ItemClickListener itemClickListener;
    public OrdersViewHolder(@NonNull View itemView) {
        super(itemView);


        productImage = itemView.findViewById(R.id.order_product_image);
        txtProductName=itemView.findViewById(R.id.order_product_name);
        txtProductPrice=itemView.findViewById(R.id.order_product_price);
        txtProductQuantity=itemView.findViewById(R.id.order_product_quantity);
        orderAgain = itemView.findViewById(R.id.order_again_txt);
    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


}
