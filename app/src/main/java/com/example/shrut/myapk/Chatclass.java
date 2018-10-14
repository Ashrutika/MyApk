package com.example.shrut.myapk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by root1 on 17/9/18.
 */

public class Chatclass extends Fragment {

    private ListView chat_list;
    private TextView name;
    private ImageView image;
    private TextView email;
    private List<ProfileList> profileLists;
    private ArrayAdapter<ProfileList> adapter;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_chat,container,false);


        mAuth=FirebaseAuth.getInstance();
        profileLists = new ArrayList<>();
        final Object[] value = new Object[1];
        chat_list = (ListView)view.findViewById(R.id.listview_chat);
        name=(TextView)view.findViewById(R.id.name_person_id);
        image=(ImageView)view.findViewById(R.id.img_circle_id);
        email=(TextView)view.findViewById(R.id.email_person_id);

        myRef= FirebaseDatabase.getInstance().getReference().child("users");
//                .child(mAuth.getCurrentUser().getUid());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                storeUsers((Map<String,Object>) dataSnapshot.getValue());
//                ProfileList[] value = dataSnapshot.getValue();

                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        adapter = new ProfileAdapter(getActivity(), R.layout.person_chat, profileLists);

        chat_list.setAdapter(adapter);


        return view;
    }


    public void storeUsers(Map<String,Object> users){
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {

            if(!entry.getKey().equals(mAuth.getCurrentUser().getUid())) {
                //Get user map
                Map singleUser = (Map) entry.getValue();
                System.out.println("-----------------------" + entry.getKey());
                if (singleUser.get("email") != null && singleUser.get("username") != null) {
                    profileLists.add(new ProfileList(entry.getKey(), singleUser.get("email").toString(), singleUser.get("username").toString(), singleUser.get("contact").toString()));
                }
            }
        }

//        ProfileList p=new ProfileList("https://www.shareicon.net/data/128x128/2016/07/11/316099_man_512x512.png",value);
        adapter.notifyDataSetChanged();

    }


}
