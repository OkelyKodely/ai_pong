package com.example.aipong;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout Container;
    ArrayList<View> textList = new ArrayList<>();

    int count = 0;

    int multiple_time = 1;

    int score = 0, scr = 0;

    ImageView ball;

    Button paddle1, paddle2;
    ImageView left, right;

    int x_move = 4;
    int y_move = 12;

    int x = 120;
    int y = 130;

    String going = "left";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        left = (ImageView) findViewById(R.id.left);
        left.setEnabled(true);
        left.setClickable(true);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isPressed()) {

                    try {
                        if (paddle1.getX() <= ball.getX() + 100 && paddle1.getX() >= ball.getX() - 40 - 100 &&
                                paddle1.getY() <= ball.getY() + 10 + 40 && paddle1.getY() >= ball.getY() - 10 + 40 - 40) {
                            x_move -= 15;
                        }
                    } catch(Exception e) {}

                    going = "left";
                    if("left".equals(going)) {
                        paddle1 = (Button) findViewById(R.id.paddle1);
                        paddle1.setX(paddle1.getX() - 80);
                    }
                }
            }
        });

        paddle2 = (Button) findViewById(R.id.paddle2);

        Thread t1 = new Thread() {
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(10);

                        if(paddle2.getX() > ball.getX()) {
                            paddle2.setX(paddle2.getX() - 2*multiple_time);
                        } else if(paddle2.getX() < ball.getX()) {
                            paddle2.setX(paddle2.getX() + 2*multiple_time);
                        }

                        paddle2.invalidate();

                    } catch(Exception e) {}
                }
            }
        };
        t1.start();

        right = (ImageView) findViewById(R.id.right);
        right.setEnabled(true);
        right.setClickable(true);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isPressed()) {

                    try {
                        if (paddle1.getX() <= ball.getX() + 100 && paddle1.getX() >= ball.getX() - 40 - 100 &&
                                paddle1.getY() <= ball.getY() + 10 + 40 && paddle1.getY() >= ball.getY() - 10 + 40 - 40) {
                            x_move += 15;
                        }
                    } catch(Exception e) {}

                    going = "right";
                    if("right".equals(going)) {
                        paddle1 = (Button) findViewById(R.id.paddle1);
                        paddle1.setX(paddle1.getX() + 80);
                    }
                }
            }
        });

        Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                paddle1.setX(350);
                paddle1.setY(840);

                x_move = 12*multiple_time;
                y_move = 12*multiple_time;

                x = 180;
                y = 130;

                ball.setX(x);
                ball.setY(y);

                score = 0;
                scr = 0;
            }
        });

        final LinearLayout c = (LinearLayout) findViewById(R.id.Container);

        ball = (ImageView) findViewById(R.id.ball);
        Thread t = new Thread() {
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(40);

                        ball.setX(x);
                        ball.setY(y);

                        if(!(y > 800)) {

                            if (y <= 0) {
                                y_move = -y_move;
                                score += 1;
                                TextView tv = (TextView) findViewById(R.id.score);
                                tv.setText("You : " + score + " Phone : " + scr);
                                tv.setTextColor(Color.DKGRAY);

                                paddle1.setX(350);
                                paddle1.setY(840);

                                x_move = 7*multiple_time;
                                y_move = 12*multiple_time;

                                x = 120;
                                y = 130;

                                ball.setX(x);
                                ball.setY(y);
                            } else {

                                if (x >= 1000 - 40)
                                    x_move = -x_move;

                                if (x <= 0)
                                    x_move = -x_move;

                                x += x_move;

                                if (paddle1.getX() <= ball.getX() + 100 && paddle1.getX() >= ball.getX() - 40 - 100 &&
                                        paddle1.getY() <= ball.getY() + 10 + 40 && paddle1.getY() >= ball.getY() - 10 + 40 - 40) {
                                    if (y_move > 0) {
                                        y_move = -y_move;
                                    }
                                }

                                if (paddle2.getX() <= ball.getX() + 100 && paddle2.getX() >= ball.getX() - 40 - 100 &&
                                        paddle2.getY() <= ball.getY() + 4 + 0 && paddle2.getY() >= ball.getY() - 10) {
                                    y_move = -y_move;
                                }

                                y += y_move;

                                ball.setX(x);
                                ball.setY(y);
                            }
                            c.invalidate();

                        } else {

                            Container = (LinearLayout) findViewById(R.id.Container);

                            for (int i = 0; i < Container.getChildCount(); i++) {
                                textList.add(Container.getChildAt(i));
                            }

                            fadeView(textList.get(0));

                            if (y >= 800) {
                                y_move = -y_move;
                                scr += 1;
                                TextView tv = (TextView) findViewById(R.id.score);
                                tv.setText("You : " + score + " Phone : " + scr);
                                tv.setTextColor(Color.DKGRAY);
                            }

                            paddle1.setX(350);
                            paddle1.setY(840);

                            x_move = 7*multiple_time;
                            y_move = 12*multiple_time;

                            x = 120;
                            y = 130;

                            ball.setX(x);
                            ball.setY(y);

                            c.invalidate();
                        }
                    } catch(Exception e) {}
                }
            }
        };

        Container = (LinearLayout) findViewById(R.id.Container);

        for (int i = 0; i < Container.getChildCount(); i++) {
            textList.add(Container.getChildAt(i));
        }

        fadeView(textList.get(0));

        t.start();
    }

    private void fadeView(final View view) {
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.fadein);
        count++;

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (count == textList.size()) return;
                fadeView(textList.get(count));
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        view.startAnimation(anim);
    }
}