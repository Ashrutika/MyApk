package com.example.shrut.myapk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    public EditText ed_contact;
    private EditText ed_name;
    private EditText ed_pass;
    private EditText ed_email;
    private Button btnsign;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth = FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(Main2Activity.this);
        progressDialog.setMessage("Loading...");

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
                String userName=ed_name.getText().toString();
                String contact=ed_contact.getText().toString();
                register(email,password,userName,contact);
                progressDialog.show();
                new Main2Activity.BackGroundJob().execute();
            }
        });


    }

    public void register(final String email, String password, final String userName, final String contact){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Main2Activity.this, "Signup Successful",
                                    Toast.LENGTH_SHORT).show();
                            HashMap<String,String> users=new HashMap<>();
                            users.put("username",userName);
                            users.put("email",email);
                            users.put("contact",contact);
                            myRef.child("users").child(mAuth.getCurrentUser().getUid()).setValue(users);
                            Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
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

    private class BackGroundJob extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.cancel();
        }
    }

}
