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
   // DatabaseHelper Mydb;

    public EditText ed_contact;
    private EditText ed_name;
    private EditText ed_pass;
    private EditText ed_email;
    private Button btnsign;
    //private Button btnv;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth = FirebaseAuth.getInstance();

//        Mydb=new DatabaseHelper(Main2Activity.this);

        ed_contact=(EditText)findViewById(R.id.etPhone);
        ed_name=(EditText)findViewById(R.id.etName);
        ed_email=(EditText)findViewById(R.id.etEmail);
        ed_pass=(EditText)findViewById(R.id.etPass);
        btnsign=(Button)findViewById(R.id.btnSign);
       // btnv=(Button)findViewById(R.id.bttn);

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=ed_email.getText().toString();
                String password=ed_pass.getText().toString();
                register(email,password);
            }
        });
//        add();
//        viewall();

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

                        // ...
                    }
                });
    }

//
//    public void add()
//    {
//        btnsign.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//
//                if (!ed_contact.getText().toString().equals("") && !ed_pass.getText().toString().equals("") )
//                {
//
//                    String data=Mydb.searchSign(ed_contact.getText().toString());
//                    if(data.equals("true"))
//                    {
//                        Toast.makeText(Main2Activity.this,"Already Exists",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        boolean isInserted = Mydb.insertdata(ed_contact.getText().toString(),ed_name.getText().toString(),ed_email.getText().toString(), ed_pass.getText().toString());
//                        if (isInserted == true)
//                        {
//                            Toast.makeText(Main2Activity.this, "Data Added", Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(Main2Activity.this,MainActivity.class);
//                            finish();
//                        }
//                        else
//                        {
//                            Toast.makeText(Main2Activity.this, "Data Not Added", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                }
//                else
//                {
//                    Toast.makeText(Main2Activity.this, "Please Enter Data", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        });
//    }
//
//
//    public void viewall()
//    {
//        btnv.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Cursor res= Mydb.getData();
//                if(res.getCount()==0)
//                {
//                    //msg
//                    showdata("error","no data found");
//                    return;
//                }
//                StringBuffer buffer=new StringBuffer();
//                while (res.moveToNext())
//                {
//                    buffer.append("Contact:"+res.getString(0)+"\n");
//                    buffer.append("Name:"+res.getString(1)+"\n");
//                    buffer.append("Email:"+res.getString(2)+"\n");
//                    buffer.append("Password:"+res.getString(3)+"\n\n");
//                }
//
//                //show data
//                showdata("data",buffer.toString());
//            }
//        });
//
//    }

  /*  public void showdata(String title,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }*/


}
