package com.example.shrut.myapk;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainchatActivity extends AppCompatActivity {

    private ListView list_view;
    private View btn_send;
    private EditText editMsg;
    private boolean isSent = true;
    String[] timeList;
    private List<ChatBubble> ChatBubbles;
    private ArrayAdapter<ChatBubble> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainchat);



        ChatBubbles = new ArrayList<>();

        list_view = (ListView) findViewById(R.id.listview);
        btn_send = findViewById(R.id.btnSend);
        editMsg = (EditText) findViewById(R.id.etTypeMsg);

        adapter = new MessageAdapter(this, R.layout.receiver, ChatBubbles);

        list_view.setAdapter(adapter);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editMsg.getText().toString().trim().equals("")) {

                    Toast.makeText(MainchatActivity.this, "please enter text...", Toast.LENGTH_SHORT).show();
                } else {
                    //add msg to list
                    ChatBubble ChatBubble = new ChatBubble(editMsg.getText().toString());
                    ChatBubbles.add(ChatBubble);
                    adapter.notifyDataSetChanged();
                    editMsg.setText("");

//                    if (isSent == true) {
//                        isSent = false;
//                    } else {
//                        if (isSent == false) {
//                            isSent = true;
//                        }
//                    }

                }
            }
        });

        btn_send.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                showAlertbox();
                return false;
            }
        });

    }


    @Override
   public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    public void showAlertbox() {
        timeList = new String[]{"Default", "15 minutes", "30 minutes", "45 minutes", "1 hour"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainchatActivity.this);
        builder.setTitle("Set Time To Delete Message");
        builder.setSingleChoiceItems(timeList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainchatActivity.this, "msg will be deleted after " + timeList[i], Toast.LENGTH_SHORT).show();
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
