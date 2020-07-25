package com.example.sharif.appointment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.VideoView;

import java.util.jar.Manifest;

public class Main2Activity extends AppCompatActivity {
    String OP[]={"Select your target","Take a appointment","Update record","Cancel appointment","Help Line"};
    Spinner spinner;
    VideoView videoView;
    ArrayAdapter<String> arrayAdapter;
    int request_call=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        spinner=findViewById(R.id.operation);
        videoView=findViewById(R.id.video1);
        Uri uri = Uri.parse("android.resource://com.example.sharif.appointment/" + R.raw.video);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        arrayAdapter=new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_list_item_1,OP);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    if(position==1){
                        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else if(position==2){
                        Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                        startActivity(intent);
                    }
                    else if(position==3){
                        Intent intent=new Intent(Main2Activity.this,Main4Activity.class);
                        startActivity(intent);
                    }
                    else if(position==4){
                            makePhoneCall();
                    }
                }
                position=0;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void makePhoneCall(){
        String call="123";
        if(ContextCompat.checkSelfPermission(Main2Activity.this,
                android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Main2Activity.this,
                    new String[]{  android.Manifest.permission.CALL_PHONE},request_call);
        }
        else {
            String dial="tel:" + call;
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
        }
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
        spinner.setAdapter(arrayAdapter);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==requestCode){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
        }
    }
}
