package com.example.questioner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class resultAdmin extends AppCompatActivity {



    MyDbHelper dbHelper;
    ArrayList<Result> topicsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showresult);




        dbHelper = new MyDbHelper(this);
        topicsList = new ArrayList<>();
        topicsList = dbHelper.getResult();
        String r = "";
        int i;

        if(topicsList.isEmpty()){
        r ="No result yet";

    }
        else{

        for(i=0;i<topicsList.size();i++){

            String name = topicsList.get(i).getName();
            String topic = topicsList.get(i).getTopic();
            String mark = topicsList.get(i).getMark();
            r += name+"         "+topic+"         "+mark+"   ";


        }

        }
        TextView s =(TextView) findViewById(R.id.bResult);
        s.setText(r);


}}