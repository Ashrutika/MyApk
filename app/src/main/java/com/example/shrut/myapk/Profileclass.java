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

//    private ImageView mprofileimage;
//    private TextView mname;
//  private TextView memail;

//     private FirebaseAuth firebaseAuth;
//     private FirebaseDatabase firebaseDatabase;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);



//        mname=(TextView)view.findViewById(R.id.dis_name);
       // memail=(TextView)view.findViewById(R.id.dis_email);
//
//        firebaseAuth= FirebaseAuth.getInstance();
//        firebaseDatabase= FirebaseDatabase.getInstance();
//
//        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
//                //Toast.makeText(Profileclass.this,"name="+userProfile.getUsername(),Toast.LENGTH_SHORT).show();
//                mname.setText(userProfile.getUsername());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
       return view;
    }



}
