package com.example.shrut.myapk;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root1 on 10/10/18.
 */

public class FriendAdapter extends ArrayAdapter<FriendList> {


    private Activity activity;
    private int resource;

    private List<FriendList> friendslist;
    private ImageFromURL imageFromURL;

    public FriendAdapter(Activity context, int resource, List<FriendList> objects) {
        super(context, resource, objects);
        this.activity=context;
        this.friendslist=objects;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FriendAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        int layoutResource = 0;
        FriendList friendLists = getItem(position);

        layoutResource = this.resource;

        if (convertView != null) {
            holder = (FriendAdapter.ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.friend_request, parent, false);
            holder = new FriendAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
            holder.name.setText((CharSequence) friendLists.getName());
            new ImageFromURL(holder.image).execute(friendLists.getImageUrl());
            //add btn accept and reject

        }

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
//        private Button btnaccept;
//        private Button btnreject;

        public ViewHolder(View v){

            name= (TextView)v.findViewById(R.id.person_name_id);  //name_person_id is the id of textview in friend_request xml
            image=(ImageView)v.findViewById(R.id.circleimgreq_id);
//            btnaccept=(Button)v.findViewById(R.id.accept_id);
//            btnreject=(Button)v.findViewById(R.id.reject_id);
        }
    }



}
