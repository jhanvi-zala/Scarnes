package com.example.admin.scarnesdice;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    Button roll2;
    Button hold1;
    Button reset1;
    int all_your_score = 0;
    int turn_your_score = 0;
    public int all_comp_score = 0;
    public int turn_comp_score = 0;
    Random random = new Random();
    ImageView dice;
    TextView your_score, computer_score;
    TextView t_your_score;
    TextView t_comp_score;
    TextView t;
    TextView c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hold1 = (Button) findViewById(R.id.hold);
        reset1 = (Button) findViewById(R.id.reset);
        roll2 = (Button) findViewById(R.id.roll);
        dice = (ImageView) findViewById(R.id.imageView);
        your_score = (TextView) findViewById(R.id.your_score);
        computer_score = (TextView) findViewById(R.id.computer_score);
        t_your_score = (TextView) findViewById(R.id.your_turn);
        t_comp_score = (TextView) findViewById(R.id.comp_turn);
        t = (TextView)findViewById(R.id.y_t);
        c = (TextView) findViewById(R.id.c_t);

        roll2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                if(all_comp_score>=100) {
                    hold1.setEnabled(false);
                    roll2.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"Computer wins",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    t_comp_score.setEnabled(false);
                    c.setEnabled(false);
                    t_your_score.setEnabled(true);
                    t.setEnabled(true);

                    int value = random.nextInt(6) + 1;
                    if (value == 1) {
                        turn_your_score = 0;
                        roll2.setEnabled(false);
                        all_your_score += turn_your_score;
                        String st = " " + all_your_score;
                        your_score.setText(st);
                        dice.setImageResource(R.drawable.dice1);
                        computerTurn();
                    } else if (value == 2) {
                        turn_your_score += 2;
                        dice.setImageResource(R.drawable.dice2);
                    } else if (value == 3) {
                        turn_your_score += 3;
                        dice.setImageResource(R.drawable.dice3);
                    } else if (value == 4) {
                        turn_your_score += 4;
                        dice.setImageResource(R.drawable.dice4);

                    } else if (value == 5) {
                        turn_your_score += 5;
                        dice.setImageResource(R.drawable.dice5);

                    } else if (value == 6) {
                        turn_your_score += 6;
                        dice.setImageResource(R.drawable.dice6);

                    }
                    String str = ""+turn_your_score;
                    t.setText(str);

                }

                // TODO Auto-generated method stub
            }
        });
        hold1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                all_your_score += turn_your_score;
                String st = " " + all_your_score;
                your_score.setText(st);
                turn_your_score = 0;
                computerTurn();

            }
        });
        reset1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                all_your_score = 0;
                turn_your_score = 0;
                all_comp_score = 0;
                turn_comp_score = 0;
                your_score.setText("00");
                computer_score.setText("00");
                t_your_score.setEnabled(true);
                t_comp_score.setEnabled(false);
                t.setEnabled(true);
                t.setText("00");
                c.setEnabled(false);
                c.setText("00");

                roll2.setEnabled(true);
                hold1.setEnabled(true);
            }
        });

    }


    public void computerTurn() {

        Boolean flag = true;
        t_your_score.setEnabled(false);
        t.setEnabled(false);
        t_comp_score.setEnabled(true);
        c.setEnabled(false);

        if (turn_your_score >= 100) {
            hold1.setEnabled(false);
            roll2.setEnabled(false);
            Toast.makeText(getApplicationContext(), "User wins", Toast.LENGTH_SHORT).show();

        } else {
            roll2.setEnabled(false);
            hold1.setEnabled(false);

            int value = random.nextInt(6) + 1;
            if (value == 1) {
                flag = false;
                turn_comp_score = 0;
                dice.setImageResource(R.drawable.dice1);
                roll2.setEnabled(true);
                hold1.setEnabled(true);
                all_comp_score += turn_comp_score;
                String st = " " + all_comp_score;
                computer_score.setText(st);

                //comp_hold(turn_comp_score);

            } else if (value == 2) {
                turn_comp_score += 2;
                dice.setImageResource(R.drawable.dice2);
            } else if (value == 3) {
                turn_comp_score += 3;
                dice.setImageResource(R.drawable.dice3);
            } else if (value == 4) {
                turn_comp_score += 4;
                dice.setImageResource(R.drawable.dice4);

            } else if (value == 5) {
                turn_comp_score += 5;
                dice.setImageResource(R.drawable.dice5);

            } else if (value == 6) {
                turn_comp_score += 6;
                dice.setImageResource(R.drawable.dice6);

            }

            String str = ""+turn_comp_score;
            c.setText(str);

        }
        Handler han = new Handler();
        final Boolean finalFlag = flag;
        if(!flag || turn_comp_score>10)
        {
            Toast.makeText(getApplicationContext(), "Computer's turn has ended", Toast.LENGTH_SHORT).show();
        }
        han.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                if (turn_comp_score <= 10 && finalFlag) {

                    computerTurn();
                } else {

                    roll2.setEnabled(true);
                    hold1.setEnabled(true);
                    all_comp_score += turn_comp_score;
                    String st = " " + all_comp_score;
                    turn_comp_score=0;
                    computer_score.setText(st);
                }
            }
        }, 2000);

    }
}
