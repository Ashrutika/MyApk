package com.example.shrut.myapk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public  class MainActivity extends AppCompatActivity  {
    DatabaseHelper Mydb;
    private Session session;
    private Button btnlog;
    private EditText contact;
    private EditText password;
    private Button btnclk;
    private int count=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mydb=new DatabaseHelper(MainActivity.this);
        session=new Session(MainActivity.this);


        OnClick();
        OnClickHere();

        if(session.loggedin()){
            startActivity(new Intent(MainActivity.this,Main3Activity.class));
            finish();
        }
    }

   public void OnClick(){
        btnlog=(Button)findViewById(R.id.btnLogin);
        contact=(EditText)findViewById(R.id.etPhone);
        password=(EditText)findViewById(R.id.etPass);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String contactStr=contact.getText().toString();
                String passStr=password.getText().toString();
                String Password=Mydb.searchLog(contactStr);

                if(passStr.equals(Password)){
                    Toast.makeText(MainActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                    session.setLoggedin(true);
                    Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    count--;
                    Toast.makeText(MainActivity.this, "Incorrect Information", Toast.LENGTH_SHORT).show();
                    if(count==0)
                    {
                       btnlog.setEnabled(false);
                    }
                }

            }
        });
   }

    public void OnClickHere(){
        btnclk=(Button)findViewById(R.id.btnClick);
        btnclk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent1);
            }
        });
    }

}
