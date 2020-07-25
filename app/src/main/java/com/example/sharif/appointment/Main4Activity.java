package com.example.sharif.appointment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

public class Main4Activity extends AppCompatActivity {
  VideoView videoView;
  EditText editText;
  Button button;
  String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        videoView=findViewById(R.id.video3);
        editText=findViewById(R.id.del_reord);
        button=findViewById(R.id.delete);
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
                a=editText.getText().toString();
                if(a.equals("")){
                    Toast.makeText(Main4Activity.this,"Enter CNIC",Toast.LENGTH_SHORT).show();
                }
                else if(a.length()!=13){
                    Toast.makeText(Main4Activity.this,"Enter complete  CNIC\n number",Toast.LENGTH_SHORT).show();
                }
                else {
                    Database database = new Database(Main4Activity.this);
                   int b= database.Delete(a);
                   if(b>0){
                       Toast.makeText(Main4Activity.this,"Appointment Calceld",Toast.LENGTH_SHORT).show();
                   }
                   else {
                       Toast.makeText(Main4Activity.this,"Your are not Appointed",Toast.LENGTH_SHORT).show();
                   }
                    editText.setText("");

                }
            }
        });
    }
}
