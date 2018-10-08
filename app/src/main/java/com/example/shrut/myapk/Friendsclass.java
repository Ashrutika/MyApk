package com.example.shrut.myapk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by root1 on 17/9/18.
 */

public class Friendsclass extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_friends,container,false);


        String[] items={"hii",
                         "hello",
                          "this is string",
                           "shrutika","aditya","prajakta","rachana","rishabh","aryan","adi","abhi","smith","neha","mansi","komal","ankita","srilekha","srividya","arnav",
                            "srikant","uma","sudarshan","srinivas","sridhar","rithwik","rama"};


        ListView listView=(ListView)v.findViewById(R.id.listview_friend_request);

        ArrayAdapter<String> profileAdapter=new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                items
        );
        listView.setAdapter(profileAdapter);

        return v;
    }

    


}
