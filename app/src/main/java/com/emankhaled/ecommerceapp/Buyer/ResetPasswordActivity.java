package com.emankhaled.ecommerceapp.Buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emankhaled.ecommerceapp.Prevalent.Prevalent;
import com.emankhaled.ecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ResetPasswordActivity extends AppCompatActivity {

    private TextView pageTitle,titleQuestions ,arrowres;
    private EditText phoneNumber,question1,question2;
    private Button verifyBtn;
    private String check="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        check=getIntent().getStringExtra("check");

        arrowres = findViewById(R.id.arrow_det);
        arrowres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check.equals("login")){
                    startActivity(new Intent(getApplicationContext() , LoginActivity.class));
                    finish();
                }else if (check.equals("settings")){
                    startActivity(new Intent(getApplicationContext() , SettingsActivity.class));
                    finish();
                }

            }
        });

        verifyBtn=(Button)findViewById(R.id.verify_btn);
        titleQuestions=(TextView)findViewById(R.id.security_questions_text);
        pageTitle=(TextView)findViewById(R.id.page_title);
        phoneNumber=(EditText)findViewById(R.id.find_phone_number);
        question1=(EditText)findViewById(R.id.question_1);
        question2=(EditText)findViewById(R.id.question_2);


    }

    @Override
    protected void onStart() {
        super.onStart();
        phoneNumber.setVisibility(View.GONE);

        if(check.equals("login")){
            phoneNumber.setVisibility(View.VISIBLE);

            verifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verifyUser();
                }
            });



        }else if(check.equals("settings")){



            pageTitle.setText("Set Questions");
            titleQuestions.setText("Please Set Answers For The Following Security Questions...");
            verifyBtn.setText("Set");
            displayPreviousAnswers();

            verifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setAnswers();


                }
            });

        }
    }

    private void verifyUser() {


        final String phone=phoneNumber.getText().toString();
        final String Answer1=question1.getText().toString().toLowerCase();

        final String Answer2=question2.getText().toString().toLowerCase();

        if(!phone.equals("")&&!Answer1.equals("")&&!Answer2.equals("")) {

            final DatabaseReference ref= FirebaseDatabase.getInstance().getReference()
                    .child("Users")
                    .child(phone);

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    if(dataSnapshot.exists()){

                        String dbPhone=dataSnapshot.child("phone").getValue().toString();



                            if(dataSnapshot.hasChild("Security Questions")){

                                String dbans1=dataSnapshot.child("Security Questions").child("answer1").getValue().toString();
                                String dbans2=dataSnapshot.child("Security Questions").child("answer2").getValue().toString();


                                if(!Answer1.equals(dbans1)){

                                    Toast.makeText(ResetPasswordActivity.this, "Your 1st answer is wrong", Toast.LENGTH_SHORT).show();

                                }else  if(!Answer2.equals(dbans2)){

                                    Toast.makeText(ResetPasswordActivity.this, "Your 2nd answer is wrong", Toast.LENGTH_SHORT).show();

                                }
                                else {

                                    final AlertDialog.Builder builder = new AlertDialog.Builder(ResetPasswordActivity.this);
                                    builder.setTitle("New Password");

                                    final EditText newPassword = new EditText(ResetPasswordActivity.this);
                                    newPassword.setHint("Write New Password Here...");
                                    builder.setView(newPassword);
                                    builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if (!newPassword.getText().toString().equals("")) {

                                                ref.child("password").setValue(newPassword.getText().toString())
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {


                                                                if (task.isSuccessful()) {

                                                                    Toast.makeText(ResetPasswordActivity.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();

                                                                    Intent intent=new Intent(ResetPasswordActivity.this, LoginActivity.class);
                                                                    startActivity(intent);
                                                            }
                                                        }

                                                        });
                                            }
                                        }
                                    });

                                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            dialog.cancel();
                                        }
                                    });

                                    builder.show();
                                }
                            }else{
                                Toast.makeText(ResetPasswordActivity.this, "you haven't set the security questions", Toast.LENGTH_SHORT).show();
                            }



                    } else{


                    Toast.makeText(ResetPasswordActivity.this, "This user phone number doesn't exist", Toast.LENGTH_SHORT).show();
                }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }else{

            Toast.makeText(this, "Please Complete The Form.", Toast.LENGTH_SHORT).show();
        }

    }

    private void setAnswers(){


        String Answer1=question1.getText().toString().toLowerCase();

        String Answer2=question2.getText().toString().toLowerCase();

        if(question1.equals("") && question2.equals("")){


            Toast.makeText(ResetPasswordActivity.this, "Please Answer Both Questions", Toast.LENGTH_SHORT).show();
        }else{


            DatabaseReference ref= FirebaseDatabase.getInstance().getReference()
                    .child("Users")
                    .child(Prevalent.currentOnlineUser.getPhone());

            HashMap<String , Object> answersMap=new HashMap<>();

            answersMap.put("answer1",Answer1);
            answersMap.put("answer2",Answer2);

            ref.child("Security Questions").updateChildren(answersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {


                    if(task.isSuccessful()){

                        Toast.makeText(ResetPasswordActivity.this, "You Have Answered The Security Questions Successfully", Toast.LENGTH_SHORT).show();


                        Intent intent=new Intent(ResetPasswordActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
            });

        }

    }

    private void displayPreviousAnswers(){






        DatabaseReference reference=FirebaseDatabase.getInstance().getReference()
                .child("Users").child(Prevalent.currentOnlineUser.getPhone());

        reference.child("Security Questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){


                    String ans1=dataSnapshot.child("answer1").getValue().toString();
                    String ans2=dataSnapshot.child("answer2").getValue().toString();
                    question1.setText(ans1);
                    question2.setText(ans2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
