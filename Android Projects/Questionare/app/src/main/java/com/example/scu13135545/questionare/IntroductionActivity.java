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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.paperdb.Paper;

public class IntroductionActivity extends AppCompatActivity {


    private Button btnPlay;
    private Button btnHighScore;
    private Button btnAbout;
    private TextView txtHighScore;
    private ArrayList HighScores;

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

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

        int maxScore = 0;
//        int looper = 0;
//        for loop
        for (HighScoreObject h : highScores){

            if (h.getScore() > maxScore){
                maxScore = h.getScore();
            }
        }

        //while loop
//        while(looper < highScores.size()){
//
//            HighScoreObject h = highScores.get(looper);
//            if(h.getScore() > maxScore){
//                maxScore = h.getScore();
//            }
//            looper++;
//        }


        txtHighScore.setText("High Score is " + maxScore);

    }
}