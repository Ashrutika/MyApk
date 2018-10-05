package com.example.shrut.myapk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by root1 on 17/9/18.
 */

public class Profileclass extends Fragment {

   // private ImageView mprofileimage;
    //private TextView mname;
   // private TextView memail;
    //private Button mchangeprofile;


//    private DatabaseReference mUsersDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      View view= inflater.inflate(R.layout.fragment_profile,container,false);

       //String user_id=getInstance().getStringExtra("user_id");

      // mUsersDatabase= FirebaseDatabase.getInstance().getReference().child("Users").child("user_id");

       // mprofileimage=(ImageView)view.findViewById(R.id.set_profile_image);
       // mname=(TextView)view.findViewById(R.id.dis_name);
       // memail=(TextView) view.findViewById(R.id.dis_email);
       // mchangeprofile=(Button)view.findViewById(R.id.btnChange_prof);

//       mUsersDatabase.addValueEventListener(new ValueEventListener(){
//           @Override
//           public void onDataChange(DataSnapshot dataSnapshot){
//              // String display_name=dataSnapshot.child("name").getValue().toString();
//               String display_email=dataSnapshot.child("email").getValue().toString();
//               //String display_image=dataSnapshot.child("image").getValue().toString();
//
//              // mname.setText(display_name);
//               memail.setText(display_email);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error){
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });






      return view;
    }



}
