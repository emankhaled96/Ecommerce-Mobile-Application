// Generated by view binder compiler. Do not edit!
package com.emankhaled.ecommerceapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.emankhaled.ecommerceapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FavoriteItemLayoutBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView favProductImage;

  @NonNull
  public final TextView favProductName;

  @NonNull
  public final TextView favProductPrice;

  @NonNull
  public final Button removeFromFav;

  private FavoriteItemLayoutBinding(@NonNull CardView rootView, @NonNull ImageView favProductImage,
      @NonNull TextView favProductName, @NonNull TextView favProductPrice,
      @NonNull Button removeFromFav) {
    this.rootView = rootView;
    this.favProductImage = favProductImage;
    this.favProductName = favProductName;
    this.favProductPrice = favProductPrice;
    this.removeFromFav = removeFromFav;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static FavoriteItemLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FavoriteItemLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.favorite_item_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FavoriteItemLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fav_product_image;
      ImageView favProductImage = ViewBindings.findChildViewById(rootView, id);
      if (favProductImage == null) {
        break missingId;
      }

      id = R.id.fav_product_name;
      TextView favProductName = ViewBindings.findChildViewById(rootView, id);
      if (favProductName == null) {
        break missingId;
      }

      id = R.id.fav_product_price;
      TextView favProductPrice = ViewBindings.findChildViewById(rootView, id);
      if (favProductPrice == null) {
        break missingId;
      }

      id = R.id.removeFromFav;
      Button removeFromFav = ViewBindings.findChildViewById(rootView, id);
      if (removeFromFav == null) {
        break missingId;
      }

      return new FavoriteItemLayoutBinding((CardView) rootView, favProductImage, favProductName,
          favProductPrice, removeFromFav);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}