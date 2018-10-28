package com.example.shrut.myapk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Nav_example extends AppCompatActivity {

//    private DatabaseReference dRef;
//    private FirebaseAuth mAuth;
//   // private StorageReference userProfileStorageRef;
//
//    private TextView mname;
//    private TextView memail;
//    private String currentUserid;
//
//    private ImageView mprofileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_example);


//        mAuth = FirebaseAuth.getInstance();
//        currentUserid = mAuth.getCurrentUser().getUid();
//        dRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUserid);
//       // userProfileStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://myapk-927fd.appspot.com").child("profileimages");
//
//        mname = (TextView) findViewById(R.id.headerName);
//       // memail = (TextView) findViewById(R.id.headerEmail);
//
//        mprofileImg = (ImageView) findViewById(R.id.logo_header);
//
//
//        //setProfileImage();
//        dRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    String pname = dataSnapshot.child("username").getValue().toString();
//                    String pemail = dataSnapshot.child("email").getValue().toString();
//
//                    mname.setText(pname);
//                    memail.setText(pemail);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        final StorageReference filepath = userProfileStorageRef.child(currentUserid + ".jpg");
//
//        try {
//            final File localFile = File.createTempFile("image", "jpg");
//            filepath.getFile(localFile)
//                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                            // Successfully downloaded data to local file
//                            // ...
//
////                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
////                                mprofileImg.setImageBitmap(bitmap);
//
//
////                            Uri downloadUri=userProfileStorageRef.getDownloadUrl().getResult();
////                            Picasso.get().load(downloadUri).into(mprofileImg);
//
//                            // dRef.setValue("profilePicture",currentUserid+".jpg");
//                            setProfileImage();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    // Handle failed download
//                    // ...
//                }
//            });
//
//
//        } catch (IOException e) {
       // }
        }


//    public void setProfileImage(){
//        userProfileStorageRef.child(currentUserid+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                ImageFromURL image=new ImageFromURL(mprofileImg);
//                image.execute(uri.toString());
//            }
//        });
//    }
    }
