package com.example.shrut.myapk;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.List;
import java.util.Map;

public class MainchatActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

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
    private CustomAdapter cAdapter;
    private ListView chat_list;
    private List<ProfileList> profileLists;
    private List<ChatBubble> wholeChat;
    DatabaseReference dRef;
    Long expiryTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileName = getIntent().getStringExtra("userName");
        setTitle(profileName);

        setContentView(R.layout.activity_mainchat);

        mAuth = FirebaseAuth.getInstance();
        final String myId = mAuth.getCurrentUser().getUid();
        profileID = getIntent().getStringExtra("userId");

        dRef = FirebaseDatabase.getInstance().getReference();

        chat_list = (ListView) findViewById(R.id.listview_chat);
        ChatBubbles = new ArrayList<>();
        wholeChat = new ArrayList<>();

        list_view = (RecyclerView) findViewById(R.id.listview);
        list_view.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        list_view.setLayoutManager(mLayoutManager);
        expiryTime = 24 * 60 * 60 * 60 * 1000L;

//        userName= (TextView) findViewById(R.id.userName);
//        userName.setText(profileName);


        btn_send = findViewById(R.id.btnSend);
        editMsg = (EditText) findViewById(R.id.etTypeMsg);

        mAdapter = new CustomAdapter(ChatBubbles);
        list_view.setAdapter(mAdapter);


//        adapter = new MessageAdapter(this, R.layout.receiver, ChatBubbles);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editMsg.getText().toString().trim().equals("")) {
                    Toast.makeText(MainchatActivity.this, "Enter Text", Toast.LENGTH_SHORT).show();
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
                    messageSentObject.put("expires", expiryTime);
                    messageReceivedObject.put("expires", expiryTime);
                    DatabaseReference sendMesssageRef = dRef.child("chats").child(myId).child(profileID).push();
                    String pushId = sendMesssageRef.getKey();
                    sendMesssageRef.setValue(messageSentObject);
                    dRef.child("chats").child(profileID).child(mAuth.getCurrentUser().getUid()).child(pushId).setValue(messageReceivedObject);

                    Map notificationMap=new HashMap();
                    notificationMap.put("from",mAuth.getCurrentUser().getUid());
//                    notificationMap.put("from",mAuth.getCurrentUser().getUid());
                    dRef.child("notifications").child(profileID).child(pushId).setValue(notificationMap);
//                    adapter.notifyDataSetChanged();

                    editMsg.setText("");
                    expiryTime = 24 * 60 * 60 * 60 * 1000L;

//                  x  if (isSent == true) {
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

        dRef.child("chats").child(mAuth.getCurrentUser().getUid()).child(profileID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                renderChat((Map<String, String>) dataSnapshot.getValue(), dataSnapshot.getKey());
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

    public void renderChat(Map<String, String> messages, String key) {
//        Long.parseLong(String.valueOf(messages.get("timeStamp")))
        if (messages != null) {
//            if (0 >= Long.parseLong(ServerValue.TIMESTAMP.toString())) {
//                dRef.child(mAuth.getCurrentUser().getUid()).child(profileID).child(key).removeValue();
//            } else {
            ChatBubbles.add(new ChatBubble(messages.get("message"), messages.get("direction").equals("sent"), String.valueOf(messages.get("timeStamp"))));
            wholeChat.add(new ChatBubble(messages.get("message"), messages.get("direction").equals("sent"), String.valueOf(messages.get("timeStamp"))));
            System.out.println("-----------------------" + messages);
            mAdapter.notifyDataSetChanged();
//            }
//            mAdapter=new CustomAdapter(ChatBubbles);
//            list_view.setAdapter(mAdapter);
        }
    }

//        ProfileList p=new ProfileList("https://www.shareicon.net/data/128x128/2016/07/11/316099_man_512x512.png",value);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        menuInflater.inflate(R.menu.delete_chat, menu);
        final MenuItem searchItem = menu.findItem(R.id.search_id);
        final MenuItem deleteChat = menu.findItem(R.id.delete_chat);
        deleteChat.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(MainchatActivity.this)
                        .setTitle("DELETE")
                        .setMessage("Are you sure, you want to delete chat?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteChat();
                                Toast.makeText(MainchatActivity.this, "Deleted chat successfully.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                return false;
            }
        });

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ArrayList<ChatBubble> newList = new ArrayList<>();
                for (ChatBubble chatBubble : wholeChat) {
                    if (chatBubble.getContent().contains(s)) {
                        newList.add(chatBubble);
                    }
                }

                setFilter(newList);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
//
        return true;
    }
//        });
//
//    }


    public void showAlertbox() {
        timeList = new String[]{"Default", "30 seconds", "5 minutes", "15 minutes", "1 hour"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainchatActivity.this);
        builder.setTitle("Set Time To Delete Message");
        builder.setSingleChoiceItems(timeList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        expiryTime = 24 * 60 * 60 * 60 * 1000L;
                        break;
                    case 1:
                        expiryTime = 30 * 1000L;
                        break;
                    case 2:
                        expiryTime = 5 * 60 * 1000L;
                        break;
                    case 3:
                        expiryTime = 15 * 60 * 1000L;
                        break;
                }
//                Toast.makeText(MainchatActivity.this, "msg will be deleted after " + timeList[i], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                btn_send.performClick();
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

    public void setFilter(ArrayList<ChatBubble> newList) {
        ChatBubbles.clear();
        ChatBubbles.addAll(newList);
        mAdapter.notifyDataSetChanged();

    }

    public void deleteChat() {
        dRef.child("chats").child(mAuth.getCurrentUser().getUid()).child(profileID).setValue(null);
    }


}