package com.imagineAny.androidLifecycle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import android.media.MediaPlayer;


public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;
    ImageButton FAB;
    ImageButton musicButton;


    Boolean activityJustStarted = false;
//    Boolean activityDestroyed = false;
    Boolean transparentActivityEnabled = false;
    Boolean activityPaused = false;
    Boolean activityJustRestarted = false;
    Boolean activityRestart = false;
    Boolean demoDone = false;
    Boolean musicPlaying = false;

    MediaPlayer player;

//    private TextView timerValue;
//
//    private long startTime = 0L;
//
//    private Handler customHandler = new Handler();
//
//    long timeInMilliseconds = 0L;
//    long timeSwapBuff = 0L;
//    long updatedTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Log.d("Tag Name", activityDestroyed.toString());
        Log.d("Tag Name", "on create!");
        activityJustStarted = true;

//        timerValue = (TextView) findViewById(R.id.timerValue);
//        startTime = SystemClock.uptimeMillis();
//        customHandler.postDelayed(updateTimerThread, 0);


        FAB = (ImageButton) findViewById(R.id.imageButton);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(musicPlaying){
//                    ((TextView) findViewById(R.id.activityStatus)).setText("Music service stopped");
//                    ((TextView)findViewById(R.id.instructions)).setText("Done!");
//                    stopMusic();
//                }
//                else if(demoDone){
//                    ((TextView) findViewById(R.id.activityStatus)).setText("Music service started");
//                    ((TextView)findViewById(R.id.instructions)).setText("Click button to stop music service");
//                    playMusic();
//                }
//                else {
                startActivity(new Intent(MainActivity.this, TransparentActivity.class));
                transparentActivityEnabled = true;
//                }
            }
        });

        musicButton = (ImageButton) findViewById(R.id.imageButtonMusic);
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicPlaying){
                    ((TextView) findViewById(R.id.activityStatus)).setText("Music service stopped");
                    ((TextView)findViewById(R.id.instructions)).setText("A service always runs until app is destroyed");
                    stopMusic();
                }
                else{
                    ((TextView) findViewById(R.id.activityStatus)).setText("Music service started");
                    ((TextView)findViewById(R.id.instructions)).setText("Visit any other app, music is always playing");
                    playMusic();
                }
            }
        });

//
//        musicButton = (ImageButton) findViewById(R.id.imageButtonMusic);
//        musicButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                if(musicPlaying){
////                    ((TextView) findViewById(R.id.activityStatus)).setText("Music service stopped");
////                    ((TextView)findViewById(R.id.instructions)).setText("");
////                    stopMusic();
////                }
////                else{
////                    ((TextView) findViewById(R.id.activityStatus)).setText("Music service started");
////                    ((TextView)findViewById(R.id.instructions)).setText("You can visit any other app, music is always playing");
////                    playMusic();
////                }
//        });
//    }
    }
    public void playMusic(){
        Log.d("Tag name", "Music service started!");
        player = MediaPlayer.create(this, R.raw.moonlight);
        player.setLooping(true);
        player.start();
        musicPlaying = true;
        Log.d("Tag name", "Player started!");
        if(player.isLooping() != true){
            Log.d("Tag name", "Player not playing any file.");
        }

    }

    public void stopMusic(){
        player.stop();
        player.release();
        musicPlaying = false;
        Log.d("Tag name", "Player destroyed");
    }

    @Override
    protected void onStart() {
        super.onStart();

//        if(activityDestroyed){
//            Log.d("Tag Name", "was destroyed!");
//            ((TextView)findViewById(R.id.activityStatus)).setText("Main Activity created and started after being destroyed");
//            ((TextView)findViewById(R.id.instructions)).setText("Click button to pause Main Activity : a Transparent Activity will be the foreground");
//        }
        if(activityJustStarted){
            ((TextView)findViewById(R.id.activityStatus)).setText("Main Activity created and started");
//            ((TextView)findViewById(R.id.instructions)).setText("Click button to pause Main Activity : a Transparent Activity will be the foreground");
            ((TextView)findViewById(R.id.instructions)).setText("Click checkmark to pause Main Activity");
            activityJustStarted = false;
        }

//        if(!activityRestart){
//            ((TextView)findViewById(R.id.activityStatus)).setText("Main Activity created and started");
//            ((TextView)findViewById(R.id.instructions)).setText("Click button to pause Main Activity : a Transparent Activity will be the foreground");
//        }

        Log.d("Tag Name", "on start!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Tag Name", "on restart!");
        activityJustRestarted = true;
        if(activityPaused){
            ((TextView) findViewById(R.id.activityStatus)).setText("Activity stopped and restarted, but Main Activity still paused");
            ((TextView)findViewById(R.id.instructions)).setText("Click back button to resume Main Activity");

        }

//        if(activityRestart) {
//            ((TextView) findViewById(R.id.activityStatus)).setText("Main Activity stopped and restarted");
//            if(!musicPlaying)
//                ((TextView)findViewById(R.id.instructions)).setText("Click button to start music service");
//            else if(musicPlaying)
//                ((TextView)findViewById(R.id.instructions)).setText("Click button to stop music service");
//            Log.d("Tag Name", "on restart!");
////            activityStopped = false;
//            demoDone = true;
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
//        ((TextView)findViewById(R.id.activityStatus)).setText("Activity was stopped!");
//        activityRestart = true ;
        Log.d("Tag Name", "on stop!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        activityDestroyed = true;
//        Log.d("Tag Name", activityDestroyed.toString());
        Log.d("Tag Name", "on destroy!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(transparentActivityEnabled){
            ((TextView)findViewById(R.id.activityStatus)).setText("Main Activity paused");
            ((TextView)findViewById(R.id.instructions)).setText("Click back button to return to Main Activity");
            activityPaused = true;

        }

        Log.d("Tag Name", "on pause!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Tag Name", activityJustStarted.toString());
        if (transparentActivityEnabled){
            ((TextView)findViewById(R.id.activityStatus)).setText("Main Activity resumed");
            ((TextView)findViewById(R.id.instructions)).setText("Click the circle or square to send this app to the background");
            transparentActivityEnabled = false;
        }
        else if(activityJustRestarted){
            ((TextView)findViewById(R.id.activityStatus)).setText("Main Activity paused, stopped, restarted and resumed");
            ((TextView)findViewById(R.id.instructions)).setText("Click back button to restart lifecycle or the checkmark button to pause");
            activityJustRestarted = false;

        }

        Log.d("Tag Name", "on resume!");
    }

//    private Runnable updateTimerThread = new Runnable() {
//
//        public void run() {
//
//            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
//
//            updatedTime = timeSwapBuff + timeInMilliseconds;
//
//            int secs = (int) (updatedTime / 1000);
//            int mins = secs / 60;
//            secs = secs % 60;
//            int milliseconds = (int) (updatedTime % 1000);
//            timerValue.setText("" + mins + ":"
//                    + String.format("%02d", secs) + ":"
//                    + String.format("%03d", milliseconds));
//            customHandler.postDelayed(this, 0);
//        }
//
//    };


}
