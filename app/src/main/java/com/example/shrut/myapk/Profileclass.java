package com.example.shrut.myapk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by root1 on 17/9/18.
 */

public class Profileclass extends Fragment {

    private DatabaseReference dRef;
    private FirebaseAuth mAuth;
    private StorageReference userProfileStorageRef;

    private TextView mname;
    private TextView memail;
    private String currentUserid;

    private ImageView mprofileImg;
    private Button btnSetProfile;
    final static int Gallery_Pick = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUserid = mAuth.getCurrentUser().getUid();
        dRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUserid);
        userProfileStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://myapk-927fd.appspot.com").child("profileimages");

        mname = (TextView) view.findViewById(R.id.dis_name);
        memail = (TextView) view.findViewById(R.id.dis_email);

        mprofileImg = (ImageView) view.findViewById(R.id.set_profile_image);
        btnSetProfile = (Button) view.findViewById(R.id.btnChange_prof);


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


        btnSetProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, Gallery_Pick);

            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Pick && resultCode == RESULT_OK && data != null) {


            final Uri ImageUri = data.getData();


            Toast.makeText(getContext(), "" + ImageUri, Toast.LENGTH_LONG).show();

            final StorageReference filepath = userProfileStorageRef.child(currentUserid + ".jpg");

            filepath.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "image stored successfully", Toast.LENGTH_SHORT).show();
//                     Uri downloadUri=taskSnapshot.getDownloadUrl();
//                     Picasso.get().load(downloadUri).into(mprofileImg);


                }
            });

            try {
                final File localFile = File.createTempFile("image", "jpg");
                filepath.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                // Successfully downloaded data to local file
                                // ...

//                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
//                                mprofileImg.setImageBitmap(bitmap);


//                            Uri downloadUri=userProfileStorageRef.getDownloadUrl().getResult();
//                            Picasso.get().load(downloadUri).into(mprofileImg);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle failed download
                        // ...
                    }
                });


            } catch (IOException e) {
            }

        }
//
//        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
//
//            CropImage.ActivityResult result=CropImage.getActivityResult(data);
//
//            if(resultCode==RESULT_OK){
//
//                Uri resultUri=result.getUri();
//
//                StorageReference filepath=userProfileRef.child(currentUserid+ ".jpg");
//
//            filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//
//                    if(task.isSuccessful()){
//                        Toast.makeText(getContext(),"image stored successfully",Toast.LENGTH_SHORT).show();
//
//                        final String downloadUrl= userProfileRef.getDownloadUrl().getResult().toString();
//
//                        dRef.child("profileimage").setValue(downloadUrl)
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if(task.isSuccessful())
//                                        {
//                                            //successfully stored
//                                        }
//
//                                    }
//                                });
//                    }
//                }
//            });
//            }
//        }
//


    }
}
