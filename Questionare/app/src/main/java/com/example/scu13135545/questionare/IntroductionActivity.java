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
    private Button btnShare;
    private TextView txtHighScore;
    private ArrayList HighScores;
    private List<HighScoreObject> highscores;
    private int maxScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        Paper.init(this);


        btnPlay = (Button)findViewById(R.id.btnPlaybutton);
        btnHighScore = (Button)findViewById(R.id.btnHighscore);
        btnAbout = (Button)findViewById(R.id.btnAbout);
        btnShare = (Button)findViewById(R.id.btnShare);
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
                Intent i = new Intent(IntroductionActivity.this, HighScoreActivity.class);
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
        Paper.init(this);

        highscores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out my High Score = " + maxScore);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,getResources().getText(R.string.app_name)));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());


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