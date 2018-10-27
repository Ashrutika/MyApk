package com.example.shrut.myapk;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by root1 on 19/10/18.
 */

public class NavigationHeader extends AppCompatActivity {

    private DatabaseReference dRef;
    private FirebaseAuth mAuth;
    private StorageReference userProfileStorageRef;

    private TextView mname;
    private TextView memail;
    private String currentUserid;

    private ImageView mprofileImg;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);


        mAuth = FirebaseAuth.getInstance();
        currentUserid = mAuth.getCurrentUser().getUid();
        dRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUserid);
        userProfileStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://myapk-927fd.appspot.com").child("profileimages");

        mname = (TextView) findViewById(R.id.headerName);
        memail = (TextView) findViewById(R.id.headerEmail);

        mprofileImg = (ImageView) findViewById(R.id.profileImg_nav);


        setProfileImage();
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String pname = dataSnapshot.child("username").getValue().toString();
                    String pemail = dataSnapshot.child("email").getValue().toString();

                    mname.setText(pname);
                    memail.setText(pemail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setProfileImage(){
        userProfileStorageRef.child(currentUserid+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                ImageFromURL image=new ImageFromURL(mprofileImg);
                image.execute(uri.toString());
            }
        });
    }
}
