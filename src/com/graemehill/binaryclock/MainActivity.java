package com.graemehill.binaryclock;

import java.util.Timer;
import java.util.TimerTask;

import com.graemehill.binaryclock.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

public class MainActivity extends Activity {

	BinaryClockView binaryClockView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		//setContentView(R.layout.main);
        //viewDigits = new SegmentDigits();
		
		binaryClockView = new BinaryClockView(this);
        setContentView(binaryClockView);
        binaryClockView.requestFocus();
        
        Timer t = new Timer();
        t.schedule(new MyTimer(), 3000, 250);
	}
	
	
	/** Generates Help Menu */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	/** Menu Clicks */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_about:
	            showHelpScreen();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void showHelpScreen() {
		AlertDialog dialog =  new AlertDialog.Builder(
                this).create();
		dialog.setTitle("About");
		dialog.setMessage("Designed by Graeme Hill (c) 2015");
		dialog.setButton("OK", new DialogInterface.OnClickListener() {
        	 
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                // closed
            }
        });		
		dialog.show();
	}
	


    
    class MyTimer extends TimerTask {

		@Override
		public void run() {
			binaryClockView.post(new Runnable() {
                public void run() {
                	binaryClockView.refreshTime();
                }
            });
		}
    	
    }


}
