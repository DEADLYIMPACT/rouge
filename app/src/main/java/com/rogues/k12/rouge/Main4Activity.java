package com.rogues.k12.rouge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity {

    Button again;
    int hs,score,time;
    RelativeLayout layout;
    TextView scorer,timer,highscore;;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main4);
        scorer = findViewById(R.id.score);
        timer =  findViewById(R.id.time);
        layout = findViewById(R.id.layout);
        highscore = findViewById(R.id.hs);
        again = findViewById(R.id.again);
        Bundle extras = getIntent().getExtras();
        score = extras.getInt("score");
        time = extras.getInt("time");
        SharedPreferences sharedPreferences = getSharedPreferences("hs", 0);
        hs = sharedPreferences.getInt("highs", 0);
        if (score <= hs) {
            layout.setBackgroundResource(R.drawable.goodgame);
        } else {
            layout.setBackgroundResource(R.drawable.highscore);
            hs = score;
            Editor edit = sharedPreferences.edit();
            edit.putInt("highs", score);
            edit.commit();
        }
        TextView textView = highscore;
        StringBuilder sb = new StringBuilder();
        sb.append("HIGHSCORE-");
        sb.append(hs);
        textView.setText(sb.toString());
        TextView textView2 = scorer;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SCORE-");
        sb2.append(score);
        textView2.setText(sb2.toString());
        TextView textView3 = timer;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("TIME-");
        sb3.append(time);
        textView3.setText(sb3.toString());
        again.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(Main4Activity.this, Main2Activity.class));
            }
        });
    }
}
