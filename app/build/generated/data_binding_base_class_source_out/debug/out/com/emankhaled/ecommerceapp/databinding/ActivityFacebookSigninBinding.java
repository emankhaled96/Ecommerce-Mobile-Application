// Generated by view binder compiler. Do not edit!
package com.emankhaled.ecommerceapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.emankhaled.ecommerceapp.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ActivityFacebookSigninBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  private ActivityFacebookSigninBinding(@NonNull ConstraintLayout rootView) {
    this.rootView = rootView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFacebookSigninBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFacebookSigninBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_facebook_signin, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFacebookSigninBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    return new ActivityFacebookSigninBinding((ConstraintLayout) rootView);
  }
}
