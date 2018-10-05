package com.example.shrut.myapk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by root1 on 17/9/18.
 */

public class Chatclass extends Fragment {

//    private ListView chat_list;
//    private TextView name;
//    private ImageView image;
//    private List<ProfileList> ProfileLists;
//    private ArrayAdapter<ProfileList> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_chat,container,false);

//        ProfileLists = new ArrayList<>();
//
//        chat_list = (ListView)view.findViewById(R.id.fragment_chat_id);
//        name=(TextView)view.findViewById(R.id.name_person_id);
//        image=(ImageView)view.findViewById(R.id.img_circle_id);
//        adapter = new ProfileAdapter(getActivity(), R.layout.person_chat, ProfileLists);
//
//        chat_list.setAdapter(adapter);
//
//        ProfileList ProfileList = new ProfileList(image,name);
//       ProfileLists.add(ProfileList);
//        adapter.notifyDataSetChanged();

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
