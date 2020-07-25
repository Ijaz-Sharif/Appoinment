package com.example.sharif.appointment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

public class Main5Activity extends AppCompatActivity {
    VideoView videoView;
    Spinner place,dr,day;
    Button cnfrm;
    String a="",b="",c="",d="",e="";
    ArrayAdapter<String> PLACE;
    ArrayAdapter<String> DOCTOR;
    ArrayAdapter<String> DAYS;
    EditText editText;
    String Area[]={"living place","Mailsi","Multan","Vehari","Lahore","Islamabad","Sahiwal","Burewala","Bahawalpur"};
    String DR[]={"Select your DR","DR_Imran","DR_Ali","DR_Shaid"};
    String Days[]={"Select your appoinment day","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        editText=findViewById(R.id.name4);
       videoView=findViewById(R.id.video4);
       place=findViewById(R.id.leaving_plave4);
       dr=findViewById(R.id.doctor4);
       day=findViewById(R.id.day4);
       cnfrm=findViewById(R.id.save4);
        Intent intent=getIntent();
        b=intent.getStringExtra("CNIC");
        Uri uri = Uri.parse("android.resource://com.example.sharif.appointment/" + R.raw.video);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        PLACE=new ArrayAdapter<String>(Main5Activity.this,android.R.layout.simple_list_item_1,Area);
        place.setAdapter(PLACE);
        place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    c=parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        DOCTOR =new ArrayAdapter<String>(Main5Activity.this,android.R.layout.simple_list_item_1,DR);
        dr.setAdapter(DOCTOR);
        dr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    d=parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        DAYS=new ArrayAdapter<String>(Main5Activity.this,android.R.layout.simple_list_item_1,Days);
        day.setAdapter(DAYS);
        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    e=parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    cnfrm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            a= editText.getText().toString();
            if(a.equals("")) {
                Toast.makeText(Main5Activity.this,"Enter your name",Toast.LENGTH_SHORT).show();
            }
            else if(c.equals("")) {
                Toast.makeText(Main5Activity.this,"Select your city",Toast.LENGTH_SHORT).show();
            }
            else if(d.equals("")) {
                Toast.makeText(Main5Activity.this,"Select your dr",Toast.LENGTH_SHORT).show();
            }
            else if(e.equals("")){
                Toast.makeText(Main5Activity.this,"Select your day",Toast.LENGTH_SHORT).show();
            }
            else {
                Patient patient=new Patient(a,b,c,d,e);
                Database database=new Database(Main5Activity.this);
                boolean res=database.upDate(patient,b);
                if(res==true){
                    Toast.makeText(Main5Activity.this,"Data inserted",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Main5Activity.this,"ERROR",Toast.LENGTH_SHORT).show();
                }
                a="";
                b="";
                c="";
                d="";
                e="";
                editText.setText("");
                place.setAdapter(PLACE);
                dr.setAdapter(DOCTOR);
                day.setAdapter(DAYS);
            }
        }
    });
     }
}
