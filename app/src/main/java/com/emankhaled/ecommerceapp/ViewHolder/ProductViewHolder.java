package com.emankhaled.ecommerceapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emankhaled.ecommerceapp.Interface.ItemClickListener;
import com.emankhaled.ecommerceapp.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtproductname,txtproductdescription,txtproductprice;
    public ImageView imageView;
    public ItemClickListener listener;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView=(ImageView) itemView.findViewById(R.id.product_image);

        txtproductname=(TextView) itemView.findViewById(R.id.product_name);

        txtproductprice=(TextView) itemView.findViewById(R.id.product_price);

        txtproductdescription=(TextView) itemView.findViewById(R.id.product_description);
    }

    public void setItemClickListener(ItemClickListener listener){

        this.listener=listener;
    }
    @Override
    public void onClick(View v) {

        listener.onClick(v,getAdapterPosition(),false);

    }
}
