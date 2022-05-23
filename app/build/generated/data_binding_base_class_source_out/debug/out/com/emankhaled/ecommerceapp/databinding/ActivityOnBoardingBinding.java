// Generated by view binder compiler. Do not edit!
package com.emankhaled.ecommerceapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.emankhaled.ecommerceapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityOnBoardingBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout dotslinearlayout;

  @NonNull
  public final TextView nextTxt;

  @NonNull
  public final RelativeLayout relativelayout;

  @NonNull
  public final TextView skipTxt;

  @NonNull
  public final ViewPager viewPager;

  private ActivityOnBoardingBinding(@NonNull ConstraintLayout rootView,
      @NonNull LinearLayout dotslinearlayout, @NonNull TextView nextTxt,
      @NonNull RelativeLayout relativelayout, @NonNull TextView skipTxt,
      @NonNull ViewPager viewPager) {
    this.rootView = rootView;
    this.dotslinearlayout = dotslinearlayout;
    this.nextTxt = nextTxt;
    this.relativelayout = relativelayout;
    this.skipTxt = skipTxt;
    this.viewPager = viewPager;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityOnBoardingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityOnBoardingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_on_boarding, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityOnBoardingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dotslinearlayout;
      LinearLayout dotslinearlayout = ViewBindings.findChildViewById(rootView, id);
      if (dotslinearlayout == null) {
        break missingId;
      }

      id = R.id.next_Txt;
      TextView nextTxt = ViewBindings.findChildViewById(rootView, id);
      if (nextTxt == null) {
        break missingId;
      }

      id = R.id.relativelayout;
      RelativeLayout relativelayout = ViewBindings.findChildViewById(rootView, id);
      if (relativelayout == null) {
        break missingId;
      }

      id = R.id.skip_Txt;
      TextView skipTxt = ViewBindings.findChildViewById(rootView, id);
      if (skipTxt == null) {
        break missingId;
      }

      id = R.id.viewPager;
      ViewPager viewPager = ViewBindings.findChildViewById(rootView, id);
      if (viewPager == null) {
        break missingId;
      }

      return new ActivityOnBoardingBinding((ConstraintLayout) rootView, dotslinearlayout, nextTxt,
          relativelayout, skipTxt, viewPager);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
