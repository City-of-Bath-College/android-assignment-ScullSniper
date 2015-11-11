package com.example.scu13135545.questionare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class IntroductionActivity extends AppCompatActivity {


    private Button btnPlay;
    private Button btnHighScore;
    private Button btnAbout;
    private TextView txtHighScore;
    private int looper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        Paper.init(this);



        btnPlay = (Button)findViewById(R.id.btnPlaybutton);
        btnHighScore = (Button)findViewById(R.id.btnHighscore);
        btnAbout = (Button)findViewById(R.id.btnAbout);
        txtHighScore = (TextView)findViewById(R.id.txtHighScore);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(IntroductionActivity.this, MainActivity.class);
                startActivity(i);
                }
        });
        btnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, IntroductionActivity.class);
                startActivity(i);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, ProfileCard.class);
                startActivity(i);
            }
        });
        List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

        txtHighScore.setText("HighScore: ", highScores.);

    }
}