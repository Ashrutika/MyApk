package com.example.shrut.myapk;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root1 on 1/10/18.
 */

public class MessageAdapter extends ArrayAdapter<ChatBubble> {

    private Activity activity;
    private int resource;

    private List<ChatBubble> messages;

    public MessageAdapter(Activity context, int resource, List<ChatBubble> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.messages = objects;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        int layoutResource = 0;
        ChatBubble ChatBubble = getItem(position);

        layoutResource = this.resource;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            if (ChatBubble.isSent() == true) {
                convertView = inflater.inflate(R.layout.sender, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
                holder.msg.setText(ChatBubble.getContent());
            }
            if (ChatBubble.isSent() == false) {
                convertView = inflater.inflate(R.layout.receiver, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
                holder.received_msg.setText(ChatBubble.getContent());
            }

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

    private class ViewHolder {
        private TextView msg;
        private TextView received_msg;
        public ViewHolder(View v) {

            msg = (TextView) v.findViewById(R.id.txt_msg);  //txt_msg is the id of textview in sender
            received_msg = (TextView) v.findViewById(R.id.txt1_msg);
        }
    }
}
