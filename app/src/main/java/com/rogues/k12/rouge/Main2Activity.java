package com.rogues.k12.rouge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class Main2Activity extends AppCompatActivity {
    Button Start;
    int pic = 1;
    ImageButton plane;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_main2);
        plane = findViewById(R.id.plane);
        Start = findViewById(R.id.start);
        plane.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (pic == 1) {
                    pic = 2;
                    plane.setImageResource(R.drawable.ship2);
                } else if (Main2Activity.this.pic == 2) {
                    pic = 3;
                    plane.setImageResource(R.drawable.ship3);
                } else if (Main2Activity.this.pic == 3) {
                    pic = 4;
                    plane.setImageResource(R.drawable.ship1);
                } else if (Main2Activity.this.pic == 4) {
                    pic = 1;
                    plane.setImageResource(R.drawable.ship4);
                }
            }
        });
        this.Start.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("plane", Integer.toString(Main2Activity.this.pic));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
