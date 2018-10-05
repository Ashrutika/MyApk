package com.example.shrut.myapk;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class startView extends AppCompatActivity {
    String [] timeList;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);

        btn=(Button)findViewById(R.id.alert);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(startView.this,Main3Activity.class);
                startActivity(intent);
            }
        });

      btn.setOnLongClickListener(new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View view) {

              showAlertbox();
              return false;
          }
      });

   }

    public void showAlertbox() {
        timeList = new String[]{"Default", "15 minutes", "30 minutes", "45 minutes", "1 hour"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set Time To Delete Message");
        builder.setSingleChoiceItems(timeList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(startView.this, "msg will be deleted after " + timeList[i], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface,int i) {


            }
        });
        builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

       // AlertDialog mDialog = builder.create();
        builder.setCancelable(true);
        builder.show();
    }
}
