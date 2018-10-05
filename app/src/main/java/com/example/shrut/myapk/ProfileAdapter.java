//package com.example.shrut.myapk;
//
//import android.app.Activity;
//import android.graphics.drawable.Drawable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.List;
//
///**
// * Created by root1 on 3/10/18.
// */
//
//public class ProfileAdapter extends ArrayAdapter<ProfileList> {
//
//    private Activity activity;
//    private int resource;
//
//    private List<ProfileList> profiles;
//
//
//    public ProfileAdapter(Activity context, int resource, List<ProfileList> objects) {
//        super(context, resource, objects);
//        this.activity=context;
//        this.profiles=objects;
//        this.resource=resource;
//    }
//
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ProfileAdapter.ViewHolder holder;
//        LayoutInflater inflater=(LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//
//        int layoutResource=0;
//        ProfileList ProfileList=getItem(position);
//
//        layoutResource=this.resource;
//
//        if(convertView!=null)
//        {
//            holder=(ProfileAdapter.ViewHolder)convertView.getTag();
//        }
//        else
//        {
//            //if(ProfileList.getSenderOrReceiver()==true){
//                convertView=inflater.inflate(R.layout.person_chat,parent,false);
//                holder=new ProfileAdapter.ViewHolder(convertView);
//                convertView.setTag(holder);
//                holder.name.setText((CharSequence) ProfileList.getName());
//                holder.image.setImageDrawable(Drawable.createFromPath(String.valueOf(ProfileList.getImageUrl())));
//           // }
////            if(ChatBubble.getSenderOrReceiver()==false){
////                convertView=inflater.inflate(R.layout.receiver,parent,false);
////                holder=new MessageAdapter.ViewHolder(convertView);
////                convertView.setTag(holder);
////                holder.msg.setText(ChatBubble.getContent());
////            }
//        }
//
//        //set msg content
//
//
//        return convertView;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//
//        return 2;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position % 2;
//    }
//
//    private class ViewHolder
//    {
//        private TextView name;
//        private ImageView image;
//
//        public ViewHolder(View v){
//
//            name= (TextView)v.findViewById(R.id.name_person_id);  //name_person_id is the id of textview in person_chat
//            image=(ImageView)v.findViewById(R.id.img_circle_id);
//        }
//    }
//}
