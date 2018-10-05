package com.example.shrut.myapk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {


    public EditText ed_contact;
    private EditText ed_name;
    private EditText ed_pass;
    private EditText ed_email;
    private Button btnsign;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth = FirebaseAuth.getInstance();



        ed_contact=(EditText)findViewById(R.id.etPhone);
        ed_name=(EditText)findViewById(R.id.etName);
        ed_email=(EditText)findViewById(R.id.etEmail);
        ed_pass=(EditText)findViewById(R.id.etPass);
        btnsign=(Button)findViewById(R.id.btnSign);


        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=ed_email.getText().toString();
                String password=ed_pass.getText().toString();
                register(email,password);
            }
        });


    }

    public void register(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Main2Activity.this, "Authentication successful.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                            startActivity(intent);
                            finish();


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Main2Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }



}
