package com.graemehill.binaryclock;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class BinaryClockView extends View {
	
	Canvas canvas = null;
	Paint inFill = null;
	Paint backGround = null;
	Paint circleBackGround = null;
	int cWidth = 0;
	int cHeight = 0;
	
	public BinaryClockView(Context context) {
		super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        inFill = new Paint();
        backGround = new Paint();
        circleBackGround = new Paint();
        //inFill.setColor(Color.RED);
        inFill.setColor(Color.rgb(85,  97,  255));
        circleBackGround.setColor(Color.rgb(64, 64, 64));
        backGround.setColor(Color.BLACK);
    	// Colour the Backgound

	}

    @Override
    public void onDraw(Canvas canvas) {
    	
    	this.canvas = canvas;
    	
    	this.cWidth = canvas.getWidth();
    	this.cHeight = canvas.getHeight();
    	
    	int cWidth = this.cWidth /4;

    	int hoursColPos = cWidth;
    	int minsColPos = cWidth * 2; 
    	int secsColPos = cWidth * 3;
    	
    	Calendar cal = Calendar.getInstance(); 
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int secs = cal.get(Calendar.SECOND);
        
    	// Colour the Backgound
    	this.canvas.drawPaint(this.backGround);
    	
    	int circleRadius = this.cWidth / 15;
        
        drawColumn(hoursColPos, 150, hour, 6, circleRadius);
        drawColumn(minsColPos,150, minute, 6, circleRadius);
        drawColumn(secsColPos, 150, secs, 6, circleRadius);
    	
    }
    
    public void drawColumn(int xPos, int yPos, int number, int digits, int circleRadius) {


    	
    	int[] bits = convertBinary(number, digits);
    	
    	//Log.d("INFO", String.valueOf(bits.length));
    	for (int i = 0; i < bits.length; i++) {
	    	if (bits[i] == 1) {
	    		this.canvas.drawCircle(xPos,  yPos + (i * (circleRadius * 3)),  circleRadius, this.inFill);
	    	} else {
	    		this.canvas.drawCircle(xPos,  yPos + (i * (circleRadius * 3)),  circleRadius, this.circleBackGround);
	    	}
    	}
    }
	
    public void refreshTime() {
        this.invalidate();
    }
    
    public static int[] convertBinary(int no, int numDigits) {
        int i = 0, temp[] = new int[7];
        int binary[];
        while (no > 0) {
            temp[i++] = no % 2;
            no /= 2;
        }
        // i = num of digits without correct
        binary = new int[numDigits];
        int k = 0;
        for (int j = numDigits - 1; j >= 0; j--) {
        	if (j > i) {
        		binary[k] = 0;
    		} else {
    			binary[k] = temp[j];
    		}
        	k++;
        }

        return binary;
    }
    
}
