package com.emankhaled.ecommerceapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emankhaled.ecommerceapp.Interface.ItemClickListener;
import com.emankhaled.ecommerceapp.R;


public class SellerItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtproductname,txtproductdescription,txtproductprice,txtproductstate;
    public ImageView imageView;
    public ItemClickListener listener;

    public SellerItemViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView=(ImageView) itemView.findViewById(R.id.seller_product_image);

        txtproductname=(TextView) itemView.findViewById(R.id.seller_product_name);
        txtproductstate=(TextView) itemView.findViewById(R.id.seller_product_state);

        txtproductprice=(TextView) itemView.findViewById(R.id.seller_product_price);

        txtproductdescription=(TextView) itemView.findViewById(R.id.seller_product_description);
    }

    public void setItemClickListener(ItemClickListener listener){

        this.listener=listener;
    }
    @Override
    public void onClick(View v) {

        listener.onClick(v,getAdapterPosition(),false);

    }
}
