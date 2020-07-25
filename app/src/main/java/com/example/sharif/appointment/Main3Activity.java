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

public class Main3Activity extends AppCompatActivity {
  VideoView videoView;
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        videoView=findViewById(R.id.video2);
        button=findViewById(R.id.upd);
        editText=findViewById(R.id.up_reord);
        Uri uri = Uri.parse("android.resource://com.example.sharif.appointment/" + R.raw.video);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("")){
                    Toast.makeText(Main3Activity.this,"Enter CNIC",Toast.LENGTH_SHORT).show();
                }
                else if(editText.getText().toString().length()!=13){
                    Toast.makeText(Main3Activity.this,"Enter complete  CNIC\n number",Toast.LENGTH_SHORT).show();
                }
                else {
                    Database database = new Database(Main3Activity.this);
                    int b= database.Delete(editText.getText().toString());
                    Intent intent=new Intent(Main3Activity.this,Main5Activity.class);
                    if(b>0){
                        intent.putExtra("CNIC",editText.getText().toString());
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Main3Activity.this,"Invalid CNIC number",Toast.LENGTH_SHORT).show();
                    }
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
