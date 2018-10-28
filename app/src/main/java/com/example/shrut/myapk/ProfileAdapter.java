package com.example.shrut.myapk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by root1 on 3/10/18.
 */

public class ProfileAdapter extends ArrayAdapter<ProfileList> {

    private Activity activity;
    private int resource;

    private List<ProfileList> profiles;
    private ImageFromURL imageFromURL;
    private StorageReference userProfileStorageRef;

    public ProfileAdapter(Activity context, int resource, List<ProfileList> objects) {
        super(context, resource, objects);
        this.activity=context;
        this.profiles=objects;
        this.resource=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater inflater=(LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        int layoutResource=0;
        final ProfileList profileList=getItem(position);

        layoutResource=this.resource;

        if(convertView!=null)
        {
            holder=(ViewHolder)convertView.getTag();
        }
        else
        {
            //if(ProfileList.getSenderOrReceiver()==true){
            convertView=inflater.inflate(R.layout.person_chat,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
            holder.name.setText((CharSequence) profileList.getUserName());
//            new ImageFromURL(holder.image).execute(profileList.getEmail());
            holder.email.setText((CharSequence)profileList.getEmail());

            userProfileStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://myapk-927fd.appspot.com").child("profileimages");
            userProfileStorageRef.child(profileList.getUserId()+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    new ImageFromURL(holder.image).execute(uri.toString());
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent startChat=new Intent(getContext(),MainchatActivity.class);
                    startChat.putExtra("userId",profileList.getUserId());
                    startChat.putExtra("userName",profileList.getUserName());
                    getContext().startActivity(startChat);
                   // Toast.makeText(getContext(),profileList.getUserId(),Toast.LENGTH_LONG).show();
                }
            });
            // }
//            if(ChatBubble.getSenderOrReceiver()==false){
//                convertView=inflater.inflate(R.layout.receiver,parent,false);
//                holder=new MessageAdapter.ViewHolder(convertView);
//                convertView.setTag(holder);
//                holder.msg.setText(ChatBubble.getContent());
//            }
        }

        //set msg content


        return convertView;
    }

    @Override
    public int getViewTypeCount() {

        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    private class ViewHolder
    {
        private TextView name;
        private ImageView image;
        private TextView email;

        public ViewHolder(View v){

            name= (TextView)v.findViewById(R.id.name_person_id);  //name_person_id is the id of textview in person_chat
            image=(ImageView)v.findViewById(R.id.img_circle_id);
            email=(TextView)v.findViewById(R.id.email_person_id);
        }
    }



}
