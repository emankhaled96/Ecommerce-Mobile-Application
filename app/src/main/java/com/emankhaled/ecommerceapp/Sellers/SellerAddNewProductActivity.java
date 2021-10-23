package com.emankhaled.ecommerceapp.Sellers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class SellerAddNewProductActivity extends AppCompatActivity {

    private String categoryName , description , name , price , saveCurrentTime , saveCurrentDate;

    private Button addNewProductBtn;
    private ImageView inputProductImageView;
    private EditText inputProductName,inputProductDescription,inputProductPrice;
    private static final int GalleryPick=1;

    private String productRandomKey,ImageDownloadUrl;

    private StorageReference productImageRef;
    private ProgressDialog loadingBar;

    private DatabaseReference productRef,sellersRef;
    private String sName,sPhone,sAddress,sEmail,sID;

    private Uri ImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_new_product);

        categoryName=getIntent().getExtras().get("Category").toString();

        productImageRef= FirebaseStorage.getInstance().getReference().child("Product image");

        productRef= FirebaseDatabase.getInstance().getReference().child("Products");

        sellersRef= FirebaseDatabase.getInstance().getReference().child("Sellers");

        addNewProductBtn=(Button)findViewById(R.id.add_new_product_btn);

        inputProductImageView=(ImageView) findViewById(R.id.select_product_image);

        inputProductName=(EditText) findViewById(R.id.product_Name_EditText);

        inputProductDescription=(EditText) findViewById(R.id.product_description_EditText);

        inputProductPrice=(EditText) findViewById(R.id.product_price_EditText);
        loadingBar=new ProgressDialog(this);

        inputProductImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        addNewProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProduct();
            }
        });

        sellersRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    sName=dataSnapshot.child("name").getValue().toString();
                    sAddress=dataSnapshot.child("address").getValue().toString();
                    sEmail=dataSnapshot.child("email").getValue().toString();
                    sPhone=dataSnapshot.child("phone").getValue().toString();
                    sID=dataSnapshot.child("sid").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void ValidateProduct() {

        description=inputProductDescription.getText().toString();

        price=inputProductPrice.getText().toString();

        name=inputProductName.getText().toString();

        if(ImageUri == null){


            Toast.makeText(this, "Product Image Is Required", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(description)){


            Toast.makeText(this, "Product Description Is Required", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(price)){


            Toast.makeText(this, "Product Price Is Required", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(name)){


            Toast.makeText(this, "Product Name Is Required", Toast.LENGTH_SHORT).show();
        }else{



            storeProductInformation();
        }


    }

    private void storeProductInformation() {

        loadingBar.setTitle("Add New Product");

        loadingBar.setMessage("Please wait , while we are adding the product");

        loadingBar.setCanceledOnTouchOutside(false);

        loadingBar.show();

        Calendar calendar=Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("MM dd , yyyy");

        saveCurrentDate=currentDate.format(calendar.getTime());


        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");

        saveCurrentTime=currentTime.format(calendar.getTime());

        productRandomKey=saveCurrentDate+saveCurrentTime;

        final StorageReference filePath=productImageRef.child(ImageUri.getLastPathSegment()+productRandomKey +".jpg");

        final UploadTask uploadTask=filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                loadingBar.dismiss();
                String message=e.toString();

                Toast.makeText(SellerAddNewProductActivity.this, "Error : "+ message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(SellerAddNewProductActivity.this, "Product Image Uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {


                        if(!task.isSuccessful()){

                            throw task.getException();
                        }
                        ImageDownloadUrl=filePath.getDownloadUrl().toString();

                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful()){

                            ImageDownloadUrl=task.getResult().toString();

                            Toast.makeText(SellerAddNewProductActivity.this, "got the product image url successfully", Toast.LENGTH_SHORT).show();

                            saveProductInfoToDatabase();
                        }
                    }
                });
            }
        });

    }

    private void saveProductInfoToDatabase() {

        HashMap<String,Object> productMap=new HashMap<>();

        productMap.put("Pid",productRandomKey);

        productMap.put("Date",saveCurrentDate);

        productMap.put("Time",saveCurrentTime);

        productMap.put("Image",ImageDownloadUrl);

        productMap.put("Description",description);

        productMap.put("Price",price);

        productMap.put("Category",categoryName);

        productMap.put("ProductName",name);



        productMap.put("sellerName",sName);
        productMap.put("sellerAddress",sAddress);
        productMap.put("sellerPhone",sPhone);
        productMap.put("sellerEmail",sEmail);
        productMap.put("sellerID",sID);


        productMap.put("ProductState","Not Approved");

        productRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(SellerAddNewProductActivity.this, SellerHomeActivity.class);
                            startActivity(intent);
                            loadingBar.dismiss();

                            Toast.makeText(SellerAddNewProductActivity.this,"Product is saved successfully..",Toast.LENGTH_SHORT).show();
                        }else{

                            loadingBar.dismiss();

                            String msg=task.getException().toString();

                            Toast.makeText(SellerAddNewProductActivity.this, " Error: "+msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void OpenGallery() {

        Intent galleryintent=new Intent();

        galleryintent.setAction(Intent.ACTION_GET_CONTENT);

        galleryintent.setType("image/*");

        startActivityForResult(galleryintent,GalleryPick);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GalleryPick  && resultCode==RESULT_OK &&data!=null){

            ImageUri=data.getData();

            inputProductImageView.setImageURI(ImageUri);


        }
    }
}
