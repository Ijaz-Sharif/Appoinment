package com.example.sharif.appointment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Calendar;

public class Slip extends AppCompatActivity {
    TextView Name, Dr, Day, Cnic, Place;
    Calendar calender;
    String Days[] = {"Select your appoinment day", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    int year, month, day;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip);
        Intent intent = getIntent();
        // calender= Calendar.getInstance();
        //String s= DateFormat.getDateInstance().format(calender.getTime());
        //year = calender.get(Calendar.YEAR); // get the current year
        // month = calender.get(Calendar.MONTH);
        //day= calender.DAY_OF_MONTH;
        button = findViewById(R.id.picture);
        Name = findViewById(R.id.NAME);
        Name.setText(intent.getStringExtra("name"));
        Dr = findViewById(R.id.DOCTOR);
        Dr.setText(intent.getStringExtra("dr"));
        Day = findViewById(R.id.DAY);
        Day.setText(intent.getStringExtra("day"));
        Place = findViewById(R.id.PLACE);
        Place.setText(intent.getStringExtra("city"));
        Cnic = findViewById(R.id.CNIC1);
        Cnic.setText(intent.getStringExtra("cnic"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.INVISIBLE);
                final RelativeLayout relativeLayout = findViewById(R.id.main);
                relativeLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap b = takeScreenShot(relativeLayout);
                        try {
                            if(b!=null){
                                SaveSreenShoot(b);
                                Toast.makeText(Slip.this,"Check your appointment\n slip in internal storage",Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    private Bitmap takeScreenShot(View v) {
        Bitmap screen = null;
        try {
            int width = v.getMeasuredWidth();
            int hight = v.getMeasuredHeight();
            screen = Bitmap.createBitmap(width, hight, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(screen);
            v.draw(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screen;
    }

    private void SaveSreenShoot(Bitmap b) {
        ByteArrayOutputStream bao = null;
        File file = null;
        try {
                 bao= new ByteArrayOutputStream();
                 b.compress(Bitmap.CompressFormat.PNG,40,bao);
                 file=new File(Environment.getExternalStorageDirectory()+File.separator+"Patient Slip.png");
                 file.createNewFile();
            FileOutputStream f=new FileOutputStream(file);
            f.write(bao.toByteArray());
            f.close();
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
}
