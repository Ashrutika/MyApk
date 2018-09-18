package com.example.shrut.myapk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public  class MainActivity extends AppCompatActivity  {

    private Button btnlog;
    private EditText contact;
    private EditText password;
    private Button btnclk;
    private int count=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClick();
        OnClickHere();
    }

   public void OnClick(){
        btnlog=(Button)findViewById(R.id.btnLogin);
        contact=(EditText)findViewById(R.id.etPhone);
        password=(EditText)findViewById(R.id.etPass);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contact.getText().toString().equals("8177963943") && password.getText().toString().equals("1234")) {

                    Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                    startActivity(intent);
                }
                else{
                    count--;
                    if(count==0){
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
