package com.emankhaled.ecommerceapp.Buyer;

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
import android.widget.TextView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private CircleImageView profileImageView;
    private EditText fullNameEditText,userPhoneEditText,addressEditText,passEditText;
    private TextView profilechangeTextBtn,closeTextBtn,saveTextBtn;
    private Button securityQuestionsButton;
    private UploadTask uploadTask;
    private Uri imageUri;
    private StorageReference storageprofilepictureRef;
    private String checker="";
    private String myUri="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        securityQuestionsButton=(Button)findViewById(R.id.security_questions_btn);
        storageprofilepictureRef= FirebaseStorage.getInstance().getReference().child("Profile Pictures");
        profileImageView=(CircleImageView)findViewById(R.id.settings_profile_image);

        fullNameEditText=(EditText) findViewById(R.id.settings_full_name);

        userPhoneEditText=(EditText) findViewById(R.id.settings_phone_number);

        addressEditText=(EditText) findViewById(R.id.settings_address);

        profilechangeTextBtn=(TextView) findViewById(R.id.profile_image_change_btn);

        closeTextBtn=(TextView) findViewById(R.id.close_setting_btn);

        saveTextBtn=(TextView) findViewById(R.id.update_account_settings_btn);

        passEditText=(EditText) findViewById(R.id.settings_pass);

        userInfoDisplay(profileImageView,fullNameEditText,userPhoneEditText,addressEditText,passEditText);

        securityQuestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingsActivity.this, ResetPasswordActivity.class);
                intent.putExtra("check","settings");
                startActivity(intent);
            }
        });
        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checker.equals("clicked")){

                    userInfoSaved();

                }else{

                    updateOnlyUserInfo();

                }
            }
        });
        profilechangeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker="clicked";

                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(SettingsActivity.this);
            }
        });
    }

    private void updateOnlyUserInfo() {

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Users");

        HashMap<String,Object> userMap=new HashMap<>();
        userMap.put("name",fullNameEditText.getText().toString());
        userMap.put("phoneOrder",userPhoneEditText.getText().toString());
        userMap.put("password",passEditText.getText().toString());
        userMap.put("Address",addressEditText.getText().toString());


        databaseReference.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);


        startActivity(new Intent(SettingsActivity.this, HomeActivity.class));

        Toast.makeText(SettingsActivity.this, "Profile Info Updated successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK && data!=null){

            CropImage.ActivityResult result=CropImage.getActivityResult(data);

            imageUri=result.getUri();

            profileImageView.setImageURI(imageUri);


        }else{


            Toast.makeText(this, "Error,try again..", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(SettingsActivity.this,SettingsActivity.class));

            finish();
        }
    }

    private void userInfoSaved() {

        if(TextUtils.isEmpty(fullNameEditText.getText().toString())){

            Toast.makeText(this, "Name is Required", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(passEditText.getText().toString())){

            Toast.makeText(this, "password is Required", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(userPhoneEditText.getText().toString())){

            Toast.makeText(this, "phone number is Required", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(addressEditText.getText().toString())){

            Toast.makeText(this, "Address is Required", Toast.LENGTH_SHORT).show();

        }else if(checker.equals("clicked")){

            uploadImage();

        }

    }

    private void uploadImage() {

        final ProgressDialog progressDialog=new ProgressDialog(this);

        progressDialog.setTitle("Update Profile");
        progressDialog.setMessage("Please wait,While we are updating your image");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        if(imageUri != null){

            final StorageReference fileRef=storageprofilepictureRef.child(Prevalent.currentOnlineUser.getPhone()+".jpg");

            uploadTask=fileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {

                    if(!task.isSuccessful()){

                        throw task.getException();

                    }


                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){


                        Uri downloadUri=task.getResult();
                        myUri=downloadUri.toString();

                        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Users");

                        HashMap<String,Object> userMap=new HashMap<>();
                        userMap.put("name",fullNameEditText.getText().toString());
                        userMap.put("phoneOrder",userPhoneEditText.getText().toString());
                        userMap.put("password",passEditText.getText().toString());
                        userMap.put("Address",addressEditText.getText().toString());
                        userMap.put("image",myUri);

                        databaseReference.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);
                        progressDialog.dismiss();

                        startActivity(new Intent(SettingsActivity.this,HomeActivity.class));

                        Toast.makeText(SettingsActivity.this, "Profile Info Updated successfully", Toast.LENGTH_SHORT).show();
                        finish();


                    }else{

                        progressDialog.dismiss();
                        Toast.makeText(SettingsActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(this, "Image is not selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void userInfoDisplay(final CircleImageView profileImageView, final EditText fullNameEditText, final EditText userPhoneEditText, final EditText addressEditText,final EditText passEditText)
    {

        DatabaseReference userRef= FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getPhone());

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    if(dataSnapshot.child("image").exists()){


                        String image=dataSnapshot.child("image").getValue().toString();

                        String name=dataSnapshot.child("name").getValue().toString();

                        String phone=dataSnapshot.child("phone").getValue().toString();

                        String address = dataSnapshot.child("Address").getValue().toString();

                        String pass=dataSnapshot.child("password").getValue().toString();

                        Picasso.get().load(image).into(profileImageView);

                        fullNameEditText.setText(name);

                        userPhoneEditText.setText(phone);

                        addressEditText.setText(address);

                        passEditText.setText(pass);
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
