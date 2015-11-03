package com.imagineAny.androidLifecycle;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

/**
 * Created by dancrisan on 15-10-30.
 */
public class TransparentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_second);

//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setMessage("Activity paused, press back button to resume");

//        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface arg0, int arg1) {
//                Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
//            }
//        });
//
//        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });

//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();


    }

}
