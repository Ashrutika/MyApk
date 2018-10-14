package com.example.shrut.myapk;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList<ChatBubble> messages;

    public CustomAdapter(ArrayList<ChatBubble> messages) {
        this.messages = messages;
    }

    int index = -1;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        System.out.println("##############################" + viewType);
        View v = null;

        if (viewType == 10) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sender, parent, false);
        }
        if (viewType == 20) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.receiver, parent, false);
        }

//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.receiver, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).isSent()) {
            return 10;
        }

        return 20;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.message.setText(this.messages.get(position).getContent());
//        holder.image.setImageResource(personImages.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView message;

        public MyViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.txt_msg);
        }
    }
}