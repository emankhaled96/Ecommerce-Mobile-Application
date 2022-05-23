// Generated by view binder compiler. Do not edit!
package com.emankhaled.ecommerceapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class ActivityPaymentDetailsBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView payamount;

  @NonNull
  public final TextView payid;

  @NonNull
  public final TextView paystate;

  private ActivityPaymentDetailsBinding(@NonNull RelativeLayout rootView,
      @NonNull TextView payamount, @NonNull TextView payid, @NonNull TextView paystate) {
    this.rootView = rootView;
    this.payamount = payamount;
    this.payid = payid;
    this.paystate = paystate;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPaymentDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPaymentDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_payment_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPaymentDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.payamount;
      TextView payamount = ViewBindings.findChildViewById(rootView, id);
      if (payamount == null) {
        break missingId;
      }

      id = R.id.payid;
      TextView payid = ViewBindings.findChildViewById(rootView, id);
      if (payid == null) {
        break missingId;
      }

      id = R.id.paystate;
      TextView paystate = ViewBindings.findChildViewById(rootView, id);
      if (paystate == null) {
        break missingId;
      }

      return new ActivityPaymentDetailsBinding((RelativeLayout) rootView, payamount, payid,
          paystate);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
