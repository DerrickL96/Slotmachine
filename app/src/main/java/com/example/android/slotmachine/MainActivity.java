package com.example.android.slotmachine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private SeekBar bar;
    private Button startbutton;
    private Button rulesbutton;
    private TextView score;
    private RelativeLayout layout1;

    public ImageView image1;
    public ImageView image2;
    public ImageView image3;

    public Drawable[] pictures = new Drawable[4];
    //private int[] pictures = {R.drawable.p76ers, R.drawable.heat, R.drawable.warriors, R.drawable.lakers};
    private Drawable draw1;
    private Drawable draw2;
    private Drawable draw3;
    private Drawable draw4;


    private Handler handler;

    private boolean statement;

    private int random;
    private int speed1 = 300;
    private int speed2 = 200;
    private int speed3 = 50;

    private int c1 = 0;
    private int c2 = 1;
    private int c3 = 2;

    private Update1 newupdate1;
    private Update2 newupdate2;
    private Update3 newupdate3;

    int scoretotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = findViewById(R.id.grid);
        bar = findViewById(R.id.bar);

        startbutton = findViewById(R.id.startbutton);
        rulesbutton = findViewById(R.id.rulesbutton);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

        score = findViewById(R.id.score);

        Random random = new Random();

        handler = new Handler();
        newupdate1 = new Update1();
        newupdate2 = new Update2();
        newupdate3 = new Update3();

        draw1 = getResources().getDrawable(R.drawable.heat);
        draw2 = getResources().getDrawable(R.drawable.p76ers);
        draw3 = getResources().getDrawable(R.drawable.warriors);
        draw4 = getResources().getDrawable(R.drawable.lakers);



        bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                        if (seekBar.getProgress() == 0) {
                            speed1 = 300;

                        } else if (seekBar.getProgress() == 1) {
                            speed1 = 200;

                        } else if (seekBar.getProgress() == 2) {
                            speed1 = 50;

                        }

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        // Adds images to the views
        ImageView imageView1 = (ImageView) getLayoutInflater().inflate(R.layout.view, grid, false);
        Drawable im1 = getResources().getDrawable(R.drawable.p76ers);
        imageView1.setImageDrawable(im1);
        grid.addView(imageView1);

        ImageView imageView2 = (ImageView) getLayoutInflater().inflate(R.layout.view, grid, false);
        Drawable im2 = getResources().getDrawable(R.drawable.warriors);
        imageView2.setImageDrawable(im2);
        grid.addView(imageView2);

        ImageView imageView3 = (ImageView) getLayoutInflater().inflate(R.layout.view, grid, false);
        Drawable im3 = getResources().getDrawable(R.drawable.lakers);
        imageView3.setImageDrawable(im3);
        grid.addView(imageView3);

        statement = true;



    }


    // Action for pressing rules button
    public void rulesPressed(View v) {

        Intent i = new Intent(this, ColorActivity.class);
        i.putExtra("Score:", scoretotal);
        startActivity(i);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int color = data.getIntExtra("COLOR", 0xFF);
        layout1.setBackgroundColor(color);

    }



    // Actions on pressing Start Button
    public void startPressed(View v) {


        pictures[0] = draw1;
        pictures[1] = draw2;
        pictures[2] = draw3;
        pictures[3] = draw4;



        Random random = new Random();
        if (startbutton.getText().equals("Start")) {

            speed1 = ((bar.getProgress() + 1) * random.nextInt(5) + 1);
            speed2 = ((bar.getProgress() + 1) * random.nextInt(5) + 1);
            speed3 = ((bar.getProgress() + 1) * random.nextInt(5) + 1);

            handler.postDelayed(newupdate1, speed1);
            handler.postDelayed(newupdate2, speed2);
            handler.postDelayed(newupdate3, speed3);


            startbutton.setText("Stop");



        } else {


            handler.removeCallbacks(newupdate1);
            handler.removeCallbacks(newupdate2);
            handler.removeCallbacks(newupdate3);


            ImageView i1 = (ImageView) grid.getChildAt(0);
            ImageView i2 = (ImageView) grid.getChildAt(1);
            ImageView i3 = (ImageView) grid.getChildAt(2);

            Drawable drawable1 = i1.getDrawable();
            Drawable drawable2 = i2.getDrawable();
            Drawable drawable3 = i3.getDrawable();




            // DRAW 1 - HEAT - 0 Points
            // DRAW 2 - 76ERS - 15 Points
            // DRAW 3 - WARRIORS - 5 Points
            // DRAW 4 - LAKERS - 0 Points

            // Checks for JACKPOT

            if (drawable1.equals(draw1) && drawable2.equals(draw1) && drawable3.equals(draw1)) {
                scoretotal += 50;
                score.setText("Score:" + scoretotal);
                Toast.makeText(this, "JACKPOT! +50 Points", Toast.LENGTH_SHORT).show();
            } else if (drawable1.equals(draw2) && drawable2.equals(draw2) && drawable3.equals(draw2)) {
                scoretotal += 50;
                score.setText("Score:" + scoretotal);
                Toast.makeText(this, "JACKPOT! +50 Points", Toast.LENGTH_SHORT).show();
            } else if (drawable1.equals(draw3) && drawable2.equals(draw3) && drawable3.equals(draw3)) {
                scoretotal += 50;
                score.setText("Score:" + scoretotal);
                Toast.makeText(this, "JACKPOT! +50 Points", Toast.LENGTH_SHORT).show();
            } else if (drawable1.equals(draw4) && drawable2.equals(draw4) && drawable3.equals(draw4)) {
                scoretotal += 50;
                score.setText("Score:" + scoretotal);
                Toast.makeText(this, "JACKPOT! +50 Points", Toast.LENGTH_SHORT).show();
            }


            // Point System for Images

            else if (drawable1.equals(draw2) && drawable2.equals(draw2)) {
                scoretotal += 30;
                score.setText("Score:" + scoretotal);
                Toast.makeText(this, "+30 Points", Toast.LENGTH_SHORT).show();

            }

            else if (drawable1.equals(draw2) && drawable3.equals(draw2)) {
                scoretotal += 30;
                score.setText("Score:" + scoretotal);
                Toast.makeText(this, "+30 Points", Toast.LENGTH_SHORT).show();

            }

            else if (drawable2.equals(draw2) && drawable3.equals(draw2)) {
                scoretotal += 30;
                score.setText("Score:" + scoretotal);
                Toast.makeText(this, "+30 Points", Toast.LENGTH_SHORT).show();

            }

            else if (drawable1.equals(draw2) || drawable2.equals(draw2) || drawable3.equals(draw2)) {
                scoretotal += 15;
                score.setText("Score:" + scoretotal);
                Toast.makeText(this, "+15 Points", Toast.LENGTH_SHORT).show();

            }

            else if (drawable1.equals(draw3) || drawable2.equals(draw3) || drawable3.equals(draw3)) {
                scoretotal += 0;
                score.setText("Score:" + scoretotal);
                Toast.makeText(this, "+0 Points", Toast.LENGTH_SHORT).show();

            } else if (drawable1.equals(draw4) || drawable2.equals(draw4) || drawable3.equals(draw4)) {
                scoretotal += 0;
                score.setText("Score:" + scoretotal);
                Toast.makeText(this, "+0 Points", Toast.LENGTH_SHORT).show();

            } else if (drawable1.equals(draw1) || drawable2.equals(draw1) || drawable3.equals(draw1)) {
                scoretotal += 0;
                score.setText("Score:" + scoretotal);
                Toast.makeText(this, "+0 Points", Toast.LENGTH_SHORT).show();

            }


            score.setText("Points:" + "" + scoretotal);
            startbutton.setText("Start");


        }


    }


    // Runs and Updates
    private class Update1 implements Runnable {


        @Override
        public void run() {
            ImageView i1 = (ImageView) grid.getChildAt(0);
            ImageView i2 = (ImageView) grid.getChildAt(1);
            ImageView i3 = (ImageView) grid.getChildAt(2);


            if (c1 < 3) {
                c1 += 1;
                i1.setImageDrawable((pictures[c1]));

            } else {
                c1 = 0;
                i1.setImageDrawable((pictures[0]));
            }
            handler.postDelayed(newupdate1, speed1 * 100);
        }
    }

    private class Update2 implements Runnable {

        @Override
        public void run() {
            ImageView i1 = (ImageView) grid.getChildAt(0);
            ImageView i2 = (ImageView) grid.getChildAt(1);
            ImageView i3 = (ImageView) grid.getChildAt(2);

            if(c2 < 3){
                c2 += 1;
                i2.setImageDrawable((pictures[c2]));

            }else{
                c2 = 0;
                i2.setImageDrawable((pictures[0]));
            }
            handler.postDelayed(newupdate2, speed2*100);
        }
    }

    private class Update3 implements Runnable {

        @Override
        public void run() {

            ImageView i1 = (ImageView) grid.getChildAt(0);
            ImageView i2 = (ImageView) grid.getChildAt(1);
            ImageView i3 = (ImageView) grid.getChildAt(2);

            if(c3 < 3){
                c3 += 1;
                i3.setImageDrawable((pictures[c3]));

            }else{
                c3 = 0;
                i3.setImageDrawable((pictures[0]));
            }
            handler.postDelayed(newupdate3, speed3*100);
        }
    }



    }


