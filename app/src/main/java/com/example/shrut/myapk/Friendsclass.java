package com.example.shrut.myapk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root1 on 17/9/18.
 */

public class Friendsclass extends Fragment {

    private ListView friends_list;
    private TextView name;
    private ImageView image;
//    private Button btnaccept;
//    private Button btnreject;
    private List<FriendList> friendLists;
    private ArrayAdapter<FriendList> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_friends,container,false);


        friendLists = new ArrayList<>();

        friends_list = (ListView)view.findViewById(R.id.listview_friend_request);
        name=(TextView)view.findViewById(R.id.person_name_id);
        image=(ImageView)view.findViewById(R.id.circleimgreq_id);

        for(int i=0;i<1;i++) {

            FriendList f = new FriendList("https://www.shareicon.net/data/128x128/2016/07/11/316099_man_512x512.png", "User");
            friendLists.add(f);

            adapter = new FriendAdapter(getActivity(), R.layout.friend_request, friendLists);

            friends_list.setAdapter(adapter);

            adapter.notifyDataSetChanged();


        }
        return view;
    }

    


}
