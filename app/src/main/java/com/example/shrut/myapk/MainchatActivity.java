package com.example.shrut.myapk;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainchatActivity extends AppCompatActivity {

    private RecyclerView list_view;
    private View btn_send;
    private EditText editMsg;
    private boolean isSent = true;
    String[] timeList;
    private ArrayList<ChatBubble> ChatBubbles;
    private ArrayAdapter<ChatBubble> adapter;
    String profileID;
    String profileName;
    TextView userName;
    FirebaseAuth mAuth;
    int messageIndex = 0;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileName = getIntent().getStringExtra("userName");
        setTitle(profileName);

        setContentView(R.layout.activity_mainchat);

        mAuth = FirebaseAuth.getInstance();
        final String myId = mAuth.getCurrentUser().getUid();
        profileID = getIntent().getStringExtra("userId");

        final DatabaseReference dRef = FirebaseDatabase.getInstance().getReference().child("chats");

        ChatBubbles = new ArrayList<>();

        list_view = (RecyclerView) findViewById(R.id.listview);
        list_view.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        list_view.setLayoutManager(mLayoutManager);

//        userName= (TextView) findViewById(R.id.userName);
//        userName.setText(profileName);


        btn_send = findViewById(R.id.btnSend);
        editMsg = (EditText) findViewById(R.id.etTypeMsg);

        mAdapter=new CustomAdapter(ChatBubbles);
        list_view.setAdapter(mAdapter);

//        adapter = new MessageAdapter(this, R.layout.receiver, ChatBubbles);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editMsg.getText().toString().trim().equals("")) {
                    Toast.makeText(MainchatActivity.this, profileID, Toast.LENGTH_SHORT).show();
                } else {
                    //add msg to list
                    String message = editMsg.getText().toString();
//                    ChatBubble ChatBubble = new ChatBubble(message, true);
//                    ChatBubbles.add(ChatBubble);
                    Map messageSentObject = new HashMap();
                    Map messageReceivedObject = new HashMap<>();
                    messageReceivedObject.put("message", message);
                    messageSentObject.put("message", message);
                    messageSentObject.put("direction", "sent");
                    messageReceivedObject.put("direction", "receive");
                    messageReceivedObject.put("timeStamp", ServerValue.TIMESTAMP);
                    messageSentObject.put("timeStamp", ServerValue.TIMESTAMP);
                    DatabaseReference sendMesssageRef=dRef.child(myId).child(profileID).push();
                    String pushId=sendMesssageRef.getKey();
                    sendMesssageRef.setValue(messageSentObject);
                    dRef.child(profileID).child(mAuth.getCurrentUser().getUid()).child(pushId).setValue(messageReceivedObject);
//                    adapter.notifyDataSetChanged();

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

        dRef.child(mAuth.getCurrentUser().getUid()).child(profileID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                renderChat((Map<String,String>) dataSnapshot.getValue());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//                .addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                renderChat((ArrayList<String>) dataSnapshot.getValue());
////                ProfileList[] value = dataSnapshot.getValue();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
    }

    public void renderChat(Map<String,String> messages) {

        if (messages != null) {
            ChatBubbles.add(new ChatBubble(messages.get("message"), messages.get("direction").equals("sent")));
            System.out.println("-----------------------" + messages);
            mAdapter.notifyDataSetChanged();
//            mAdapter=new CustomAdapter(ChatBubbles);
//            list_view.setAdapter(mAdapter);
        }
    }

//        ProfileList p=new ProfileList("https://www.shareicon.net/data/128x128/2016/07/11/316099_man_512x512.png",value);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            public void onClick(DialogInterface dialogInterface, int i) {


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
