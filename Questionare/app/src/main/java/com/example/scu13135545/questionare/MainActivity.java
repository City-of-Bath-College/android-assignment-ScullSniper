package com.example.scu13135545.questionare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    //variables
    private Button btnFalse;
    private Button btnTrue;
    private TextView lblQuestion;
    private ImageView imgPicture;

    private List<QuestionObject> questions;

    private QuestionObject currentQuestion;
    private int index;

    private int score;



    @Override
            protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_main);

        //connect variables to interface item
        btnFalse = (Button)findViewById(R.id.btnFalse);
        btnTrue = (Button)findViewById(R.id.btnTrue);
        lblQuestion = (TextView)findViewById(R.id.lblQuestion);
        imgPicture = (ImageView)findViewById(R.id.imgPicture);
        //set Questionaire Text
        lblQuestion.setText("is my name ....");

        //set image picture
        imgPicture.setImageResource(R.drawable.face);

        //set Score Show


        index = 0;
        score = 0;
        // onclick listeners
        btnFalse.setOnClickListener(new View.OnClickListener() {
        @Override
          public void onClick(View v) {
                determineButtonPress(false);
            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(true);
            }
        });

        generateQuestions();

        setUpQuestions();

        Paper.init(this);

    }

    private void generateQuestions(){
        questions = new ArrayList<>();

        questions.add(new QuestionObject("London is the capital of England", true, R.drawable.england));

        questions.add(new QuestionObject("A humming birds wings beat at 80 beats a second", true, R.drawable.hummingbird));

        questions.add(new QuestionObject("A Liger is a cross between a Lemur and Linx", false, R.drawable.liger));

        questions.add(new QuestionObject("The earth is circling the moon", false, R.drawable.earth));

        questions.add(new QuestionObject("Hydrogen Monoxide is dangerous", false, R.drawable.pluto));

        questions.add(new QuestionObject("C⁶H¹²O⁶", true, R.drawable.pluto));

        questions.add(new QuestionObject("Pluto is a dwarf planet", true, R.drawable.pluto));

        questions.add(new QuestionObject("Pluto is a dwarf planet", true, R.drawable.pluto));

        questions.add(new QuestionObject("Pluto is a dwarf planet", true, R.drawable.pluto));

        questions.add(new QuestionObject("Pluto is a dwarf planet(end)", true, R.drawable.pluto));

    }

    private void setUpQuestions(){

        if (index == questions.size()){
            Log.d("Questionare", "Ended All the Questions");

            endGame();
        }else {

            currentQuestion = questions.get(index);

            lblQuestion.setText(currentQuestion.getQuestion());
            imgPicture.setImageResource(currentQuestion.getPicture());

            index++;
        }
    }

    private void determineButtonPress(boolean answer){
        boolean expectedAnswer = currentQuestion.isAnswer();

        if (answer == expectedAnswer){
            //you were right

            Toast.makeText(MainActivity.this,"Correct!",Toast.LENGTH_SHORT).show();
            score++;
        } else {
            //you were wrong

            Toast.makeText(MainActivity.this,"Wrong!",Toast.LENGTH_SHORT).show();
        }
    setUpQuestions();
    }

    private void endGame(){
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Congratulations")
                .setMessage("You scored " + score + " points this round!")
                .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        //new high score!
                        HighScoreObject highScore = new HighScoreObject(score, "player1", new Date().getTime());

                        //get user pref
                        List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

                        //add item
                        highScores.add(highScore);

                        //save again
                        Paper.book().write("highscores", highScores);

                        //return to intro
                        finish();
                    }
                })
                .create();
    alertDialog.show();
    }
}
