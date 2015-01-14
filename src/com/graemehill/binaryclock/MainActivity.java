package com.graemehill.binaryclock;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	BinaryClockView binaryClockView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.main);
        //viewDigits = new SegmentDigits();
		
		binaryClockView = new BinaryClockView(this);
        setContentView(binaryClockView);
        binaryClockView.requestFocus();
        
        Timer t = new Timer();
        t.schedule(new MyTimer(), 3000, 250);
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
