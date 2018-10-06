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

public class Chatclass extends Fragment {

    private ListView chat_list;
    private TextView name;
    private ImageView image;
    private List<ProfileList> profileLists;
    private ArrayAdapter<ProfileList> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_chat,container,false);

        profileLists = new ArrayList<>();

        chat_list = (ListView)view.findViewById(R.id.listview_chat);
        name=(TextView)view.findViewById(R.id.name_person_id);
        image=(ImageView)view.findViewById(R.id.img_circle_id);

        ProfileList p=new ProfileList("https://www.shareicon.net/data/128x128/2016/07/11/316099_man_512x512.png","shrutika");
        profileLists.add(p);

        adapter = new ProfileAdapter(getActivity(), R.layout.person_chat, profileLists);

        chat_list.setAdapter(adapter);

        adapter.notifyDataSetChanged();

//        String[] items={"hii",
//                         "hello",
//                          "this is string"};
//
//
//
//        ListView listView=(ListView)view.findViewById(R.id.listview_chat);
//
//        ArrayAdapter<String> profileAdapter=new ArrayAdapter<String>(
//                getActivity(),
//                android.R.layout.simple_list_item_activated_1,
//                items
//        );
//        listView.setAdapter(profileAdapter);

        return view;
    }




}
