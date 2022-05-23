package com.emankhaled.ecommerceapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emankhaled.ecommerceapp.R;

public class AdminOrdersViewHolder extends RecyclerView.ViewHolder {

    public TextView orderTimeDate;
    public RecyclerView order_products_list;
    public RecyclerView.LayoutManager manager;
    public AdminOrdersViewHolder(@NonNull View itemView) {
        super(itemView);

        orderTimeDate =itemView.findViewById(R.id.order_product_date_time);
        order_products_list = itemView.findViewById(R.id.orders_product_list);
        manager = new LinearLayoutManager(itemView.getContext());
        order_products_list.setLayoutManager(manager);
    }
}
