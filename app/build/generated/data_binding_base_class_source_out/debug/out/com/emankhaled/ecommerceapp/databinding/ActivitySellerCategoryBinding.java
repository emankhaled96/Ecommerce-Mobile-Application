// Generated by view binder compiler. Do not edit!
package com.emankhaled.ecommerceapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.emankhaled.ecommerceapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySellerCategoryBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView arrowSellerCat;

  @NonNull
  public final ImageView femaleDresses;

  @NonNull
  public final ImageView glasses;

  @NonNull
  public final ImageView hatsCaps;

  @NonNull
  public final ImageView headphonesHandfree;

  @NonNull
  public final ImageView laptopPc;

  @NonNull
  public final ImageView mobilephones;

  @NonNull
  public final ImageView pursesBagsWallets;

  @NonNull
  public final ImageView shoes;

  @NonNull
  public final TextView sloganCategory;

  @NonNull
  public final ImageView sportsTShirts;

  @NonNull
  public final ImageView sweater;

  @NonNull
  public final ImageView tShirts;

  @NonNull
  public final ImageView watches;

  private ActivitySellerCategoryBinding(@NonNull RelativeLayout rootView,
      @NonNull TextView arrowSellerCat, @NonNull ImageView femaleDresses,
      @NonNull ImageView glasses, @NonNull ImageView hatsCaps,
      @NonNull ImageView headphonesHandfree, @NonNull ImageView laptopPc,
      @NonNull ImageView mobilephones, @NonNull ImageView pursesBagsWallets,
      @NonNull ImageView shoes, @NonNull TextView sloganCategory, @NonNull ImageView sportsTShirts,
      @NonNull ImageView sweater, @NonNull ImageView tShirts, @NonNull ImageView watches) {
    this.rootView = rootView;
    this.arrowSellerCat = arrowSellerCat;
    this.femaleDresses = femaleDresses;
    this.glasses = glasses;
    this.hatsCaps = hatsCaps;
    this.headphonesHandfree = headphonesHandfree;
    this.laptopPc = laptopPc;
    this.mobilephones = mobilephones;
    this.pursesBagsWallets = pursesBagsWallets;
    this.shoes = shoes;
    this.sloganCategory = sloganCategory;
    this.sportsTShirts = sportsTShirts;
    this.sweater = sweater;
    this.tShirts = tShirts;
    this.watches = watches;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySellerCategoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySellerCategoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_seller_category, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySellerCategoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.arrow_seller_cat;
      TextView arrowSellerCat = ViewBindings.findChildViewById(rootView, id);
      if (arrowSellerCat == null) {
        break missingId;
      }

      id = R.id.female_dresses;
      ImageView femaleDresses = ViewBindings.findChildViewById(rootView, id);
      if (femaleDresses == null) {
        break missingId;
      }

      id = R.id.glasses;
      ImageView glasses = ViewBindings.findChildViewById(rootView, id);
      if (glasses == null) {
        break missingId;
      }

      id = R.id.hats_caps;
      ImageView hatsCaps = ViewBindings.findChildViewById(rootView, id);
      if (hatsCaps == null) {
        break missingId;
      }

      id = R.id.headphones_handfree;
      ImageView headphonesHandfree = ViewBindings.findChildViewById(rootView, id);
      if (headphonesHandfree == null) {
        break missingId;
      }

      id = R.id.laptop_pc;
      ImageView laptopPc = ViewBindings.findChildViewById(rootView, id);
      if (laptopPc == null) {
        break missingId;
      }

      id = R.id.mobilephones;
      ImageView mobilephones = ViewBindings.findChildViewById(rootView, id);
      if (mobilephones == null) {
        break missingId;
      }

      id = R.id.purses_bags_wallets;
      ImageView pursesBagsWallets = ViewBindings.findChildViewById(rootView, id);
      if (pursesBagsWallets == null) {
        break missingId;
      }

      id = R.id.shoes;
      ImageView shoes = ViewBindings.findChildViewById(rootView, id);
      if (shoes == null) {
        break missingId;
      }

      id = R.id.slogan_category;
      TextView sloganCategory = ViewBindings.findChildViewById(rootView, id);
      if (sloganCategory == null) {
        break missingId;
      }

      id = R.id.sports_t_shirts;
      ImageView sportsTShirts = ViewBindings.findChildViewById(rootView, id);
      if (sportsTShirts == null) {
        break missingId;
      }

      id = R.id.sweater;
      ImageView sweater = ViewBindings.findChildViewById(rootView, id);
      if (sweater == null) {
        break missingId;
      }

      id = R.id.t_shirts;
      ImageView tShirts = ViewBindings.findChildViewById(rootView, id);
      if (tShirts == null) {
        break missingId;
      }

      id = R.id.watches;
      ImageView watches = ViewBindings.findChildViewById(rootView, id);
      if (watches == null) {
        break missingId;
      }

      return new ActivitySellerCategoryBinding((RelativeLayout) rootView, arrowSellerCat,
          femaleDresses, glasses, hatsCaps, headphonesHandfree, laptopPc, mobilephones,
          pursesBagsWallets, shoes, sloganCategory, sportsTShirts, sweater, tShirts, watches);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}