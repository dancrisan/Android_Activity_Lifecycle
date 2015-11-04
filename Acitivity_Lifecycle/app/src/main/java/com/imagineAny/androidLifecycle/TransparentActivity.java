package com.imagineAny.androidLifecycle;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by dancrisan on 15-10-30.
 */
public class TransparentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_second);

        Log.d("Tag Name", "transparent activity : on create!");

    }

}
