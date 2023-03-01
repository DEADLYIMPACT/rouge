package com.rogues.k12.rouge;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class Main3Activity extends AppCompatActivity implements SensorEventListener {

    static MediaPlayer Space = null;
    Button b;
    ImageView fireball,plane;
    static int fs = 5,fx=0,fy=0,ph=0,pic=1,pw=0,score=0,sped=0,time=0,x=0,xMax=0,xMin=0,run=1;
    TextView scorer,timer;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView( R.layout.activity_main3);
        Space = MediaPlayer.create(this, R.raw.space);
        
        plane = findViewById(R.id.plane);
        fireball = findViewById(R.id.fireball);
        b = findViewById(R.id.button);
        timer = findViewById(R.id.time);
        scorer = findViewById(R.id.score);
        
        fs = 5;
        run = 1;
        sped++;
        time = 0;
        score = 0;
        x = 0;
        
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        ph = defaultDisplay.getHeight() / 8;
        pw = defaultDisplay.getWidth() / 5;
        
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(1), 0);
        
        plane.setX(0.0f);
        plane.getLayoutParams().height = ph;
        plane.getLayoutParams().width = pw;
        plane.setY((float) ((defaultDisplay.getHeight() / 12) - ((defaultDisplay.getHeight() / 12) * 2)));

        fx = new Random().nextInt(defaultDisplay.getWidth() - 50);
        fireball.setX((float) fx);
        fy = (int) b.getY();
        fireball.setY((float) fy);
        fireball.getLayoutParams().height = defaultDisplay.getHeight() / 7;
        fireball.getLayoutParams().width = defaultDisplay.getWidth() / 8;
        
        timer.setHeight(defaultDisplay.getHeight() / 12);
        timer.setWidth(defaultDisplay.getWidth() / 2);
        
        scorer.setHeight(defaultDisplay.getHeight() / 12);
        scorer.setWidth(defaultDisplay.getWidth() / 2);
        
        pic = Integer.parseInt(getIntent().getExtras().getString("plane"));
        
        if (pic == 1) {
            plane.setBackgroundResource(R.drawable.ship4);
        } else if (pic == 2) {
            plane.setBackgroundResource(R.drawable.ship2);
        } else if (pic == 3) {
            plane.setBackgroundResource(R.drawable.ship3);
        } else if (pic == 4) {
            plane.setBackgroundResource(R.drawable.ship1);
        }
        firebal();
        timer();
    }

    public void firebal() {
        if (run == 1) {
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            if (fireball.getY() > timer.getY()) {
                Intent intent = new Intent(this, Main4Activity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("score", score);
                bundle.putInt("time", time);
                intent.putExtras(bundle);
                run = 0;
                startActivity(intent);
            } else if (plane.getX() + ((float) plane.getWidth()) <= fireball.getX() || plane.getX() >= fireball.getX() + ((float) fireball.getWidth())) {
                fy += fs;
                fireball.setY((float) fy);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        firebal();
                    }
                }, 10);
            } else if (plane.getY() <= fireball.getY() - ((float) fireball.getWidth()) || plane.getY() - ((float) plane.getHeight()) >= fireball.getY()) {
                fy += fs;
                fireball.setY((float) fy);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        firebal();
                    }
                }, 10);
            } else {
                fx = new Random().nextInt(defaultDisplay.getWidth() - 50);
                fireball.setX((float) fx);
                fy = (int) b.getY();
                fireball.setY((float) fy);
                fs++;
                score++;
                TextView textView = scorer;
                StringBuilder sb = new StringBuilder();
                sb.append("SCORE-");
                sb.append(score);
                textView.setText(sb.toString());
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        firebal();
                    }
                }, 10);
            }
        }
    }

    public void timer() {
        if (run == 1) {
            time++;
            TextView textView = timer;
            StringBuilder sb = new StringBuilder();
            sb.append("TIME-");
            sb.append(time);
            textView.setText(sb.toString());
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    timer();
                }
            }, 1000);
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (run == 1) {
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            xMax = defaultDisplay.getWidth() - 30;
            xMin = (defaultDisplay.getWidth() - (defaultDisplay.getWidth() * 2)) + 30;
            x = (int) plane.getX();
            x -= (((int) sensorEvent.values[0]) * 3) / sped;
            plane.setX((float) x);
            if (x >= xMax) {
                x = xMin + 100;
                plane.setX((float) x);
            } else if (x <= xMin) {
                x = xMax - 100;
                plane.setX((float) x);
            }
        }
    }
}
