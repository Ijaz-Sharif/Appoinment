package com.example.sharif.appointment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Parcelable;
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

public class MainActivity extends AppCompatActivity {
   VideoView videoView;
   Spinner place,dr,day;
   Button cnfrm;
   String a="",b="",c="",d="",e="";
   ArrayAdapter<String>  PLACE;
   ArrayAdapter<String> DOCTOR;
   ArrayAdapter<String> DAYS;
   EditText editText,editText1;
    Patient patient;
   String Area[]={"living place","Mailsi","Multan","Vehari","Lahore","Islamabad","Sahiwal","Burewala","Bahawalpur"};
   String DR[]={"Select your DR","DR_Imran","DR_Ali","DR_Shaid"};
   String Days[]={"Select your appoinment day","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //background vide play code
        videoView = findViewById(R.id.video);
        editText=findViewById(R.id.name);
        editText1=findViewById(R.id.CNIC);
        Uri uri = Uri.parse("android.resource://com.example.sharif.appointment/" + R.raw.video);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        // creating referance
        place = findViewById(R.id.leaving_plave);
        dr = findViewById(R.id.doctor);
        day = findViewById(R.id.day);
        cnfrm = findViewById(R.id.save);
        PLACE=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,Area);
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
       DOCTOR =new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,DR);
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
        DAYS=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,Days);
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
              b=editText1.getText().toString();
              if(a.equals("")) {
                  Toast.makeText(MainActivity.this,"Enter your name",Toast.LENGTH_SHORT).show();
              }
            else if(b.equals("")) {
                  Toast.makeText(MainActivity.this,"Enter your CNIC",Toast.LENGTH_SHORT).show();
              }
              else if(b.length()!=13){
                  Toast.makeText(MainActivity.this,"Enter complete CNIC\n number",Toast.LENGTH_SHORT).show();
              }
             else if(c.equals("")) {
                  Toast.makeText(MainActivity.this,"Select your city",Toast.LENGTH_SHORT).show();
              }
              else if(d.equals("")) {
                  Toast.makeText(MainActivity.this,"Select your dr",Toast.LENGTH_SHORT).show();
              }
              else if(e.equals("")){
                  Toast.makeText(MainActivity.this,"Select your day",Toast.LENGTH_SHORT).show();
              }
              else {
                   patient=new Patient(a,b,c,d,e);
                  Database database=new Database(MainActivity.this);
               boolean res=database.AddData(patient);
               if(res==true){
                   Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(MainActivity.this,Slip.class);
                   intent.putExtra("name",a);
                   intent.putExtra("cnic",b);
                   intent.putExtra("city",c);
                   intent.putExtra("dr",d);
                   intent.putExtra("day",e);
                   startActivity(intent);
               }
               else {
                   Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_SHORT).show();
               }
                  a="";
                  b="";
                  c="";
                  d="";
                  e="";
                  editText.setText("");
                  editText1.setText("");
                  place.setAdapter(PLACE);
                  dr.setAdapter(DOCTOR);
                  day.setAdapter(DAYS);
              }
          }
      });
    }
    @Override
    protected void onStart() {
        super.onStart();
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }
}
