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
    ImageButton FAB; //floatingActionButton to demo onPause()
    ImageButton musicButton; //button starting/stopping music to demo services

    Boolean activityJustStarted = false;
    Boolean activityJustRestarted = false;
    Boolean transparentActivityEnabled = false;
    Boolean activityPaused = false;
    Boolean musicPlaying = false;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("Tag Name", "on create!");
        activityJustStarted = true;

        FAB = (ImageButton) findViewById(R.id.imageButton);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TransparentActivity.class));
                transparentActivityEnabled = true;

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
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(activityJustStarted){
            ((TextView)findViewById(R.id.activityStatus)).setText("Main Activity created and started");
            ((TextView)findViewById(R.id.instructions)).setText("Click checkmark to pause Main Activity");
            activityJustStarted = false;
        }

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
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Tag Name", "on stop!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
            ((TextView)findViewById(R.id.instructions)).setText("Click the device circle or square to send this app to the background");
            transparentActivityEnabled = false;
        }
        else if(activityJustRestarted){
            ((TextView)findViewById(R.id.activityStatus)).setText("Main Activity paused, stopped, restarted and resumed");
            ((TextView)findViewById(R.id.instructions)).setText("Click back button to restart lifecycle or the checkmark button to pause");
            activityJustRestarted = false;

        }
        Log.d("Tag Name", "on resume!");
    }

    public void playMusic(){
        Log.d("Tag name", "Music service started!");
        player = MediaPlayer.create(this, R.raw.moonlight);
        player.setLooping(true);
        player.start(); //start service
        musicPlaying = true;
        Log.d("Tag name", "Player started!");
        if(player.isLooping() != true){
            Log.d("Tag name", "Player not playing any file.");
        }
    }

    public void stopMusic(){
        player.stop(); //stop service
        player.release();
        musicPlaying = false;
        Log.d("Tag name", "Player destroyed");
    }
}
