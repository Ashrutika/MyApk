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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private Session session;
    private Button btnlog;
    private EditText email;
    private EditText password;
    private Button btnclk;
    private TextView forgotpassword;
    private ProgressDialog progressDialog;
    private int count = 3;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");

        btnlog = (Button) findViewById(R.id.btnLogin);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPass);


        session = new Session(MainActivity.this);



        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String typedEmail = email.getText().toString();
                final String typedPassword = password.getText().toString();
                register(typedEmail,typedPassword);
                progressDialog.show();
                new BackGroundJob().execute();
            }
        });


        OnClickHere();
        OnclickChangepass();

        if (session.loggedin())
        {
            startActivity(new Intent(MainActivity.this, Main3Activity.class));
            finish();
        }



    }

    public void register(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Authentication successful.",
                                    Toast.LENGTH_SHORT).show();
                            session.setLoggedin(true);
                            Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                           startActivity(intent);
                            finish();

                        }
                        else
                        {
                            // If sign in fails, display a message to the user.
                            count--;
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            if(count==0)
                            {
                              btnlog.setEnabled(false);
                            }
                        }



                    }
                });
    }



    public void OnClickHere() {
        btnclk = (Button) findViewById(R.id.btnClick);
        btnclk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent1);
            }
        });
    }


    public void OnclickChangepass(){
        forgotpassword=(TextView)findViewById(R.id.forgotpass_id);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private class BackGroundJob extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(1000);
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
