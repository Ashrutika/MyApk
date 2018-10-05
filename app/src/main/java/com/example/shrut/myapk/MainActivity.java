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


public class MainActivity extends AppCompatActivity {
   // DatabaseHelper Mydb;
    private Session session;
    private Button btnlog;
    private EditText email;
    private EditText password;
    private Button btnclk;
    private int count = 3;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        btnlog = (Button) findViewById(R.id.btnLogin);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPass);

        //Mydb = new DatabaseHelper(MainActivity.this);
        session = new Session(MainActivity.this);



        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String typedEmail = email.getText().toString();
                final String typedPassword = password.getText().toString();

                regiter(typedEmail,typedPassword);
            }
        });

//        OnClick();
        OnClickHere();
//
        if (session.loggedin())
        {
            startActivity(new Intent(MainActivity.this, Main3Activity.class));
            finish();
        }



    }

    public void regiter(String email,String password){
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

//   public void OnClick(){
//
//        btnlog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String contactStr=contact.getText().toString();
//                String passStr=password.getText().toString();
//                String Password=Mydb.searchLog(contactStr);
//
//                if(passStr.equals(Password)){
//                    Toast.makeText(MainActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
//                    session.setLoggedin(true);
//                    Intent intent=new Intent(MainActivity.this,Main3Activity.class);
//                    startActivity(intent);
//                    finish();
//
//                }
//                else
//                {
//                    count--;
//                    Toast.makeText(MainActivity.this, "Incorrect Information", Toast.LENGTH_SHORT).show();
//                    if(count==0)
//                    {
//                       btnlog.setEnabled(false);
//                    }
//                }
//
//            }
//        });
//   }

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

}
