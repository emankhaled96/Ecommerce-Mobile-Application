// Generated by view binder compiler. Do not edit!
package com.emankhaled.ecommerceapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.emankhaled.ecommerceapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySearchProductBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RelativeLayout RelativeLayout44;

  @NonNull
  public final RecyclerView searchList;

  @NonNull
  public final Button searchProductBtn;

  @NonNull
  public final EditText searchProductCategoryName;

  private ActivitySearchProductBinding(@NonNull RelativeLayout rootView,
      @NonNull RelativeLayout RelativeLayout44, @NonNull RecyclerView searchList,
      @NonNull Button searchProductBtn, @NonNull EditText searchProductCategoryName) {
    this.rootView = rootView;
    this.RelativeLayout44 = RelativeLayout44;
    this.searchList = searchList;
    this.searchProductBtn = searchProductBtn;
    this.searchProductCategoryName = searchProductCategoryName;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySearchProductBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySearchProductBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_search_product, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySearchProductBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.RelativeLayout44;
      RelativeLayout RelativeLayout44 = ViewBindings.findChildViewById(rootView, id);
      if (RelativeLayout44 == null) {
        break missingId;
      }

      id = R.id.search_list;
      RecyclerView searchList = ViewBindings.findChildViewById(rootView, id);
      if (searchList == null) {
        break missingId;
      }

      id = R.id.search_product_Btn;
      Button searchProductBtn = ViewBindings.findChildViewById(rootView, id);
      if (searchProductBtn == null) {
        break missingId;
      }

      id = R.id.search_product_Category_name;
      EditText searchProductCategoryName = ViewBindings.findChildViewById(rootView, id);
      if (searchProductCategoryName == null) {
        break missingId;
      }

      return new ActivitySearchProductBinding((RelativeLayout) rootView, RelativeLayout44,
          searchList, searchProductBtn, searchProductCategoryName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}