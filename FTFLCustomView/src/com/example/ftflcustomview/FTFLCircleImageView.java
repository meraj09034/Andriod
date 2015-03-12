package com.example.ftflcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FTFLCircleImageView extends ImageView {

	int m_X;
	int m_Y;
	int mWindowWidth;
	int mWindowHeight;

	public FTFLCircleImageView(Context context, int eWidth, int eHeight) {
		super(context);
		
		mWindowWidth = eWidth;
		mWindowHeight = eHeight;
		init();
	}
	
	 public FTFLCircleImageView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	       // init();
	    }

	 

	private void init() {
		// TODO Auto-generated method stub
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				150, 150);
		//this.setImageResource(R.drawable.ic_launcher);
		this.setLayoutParams(layoutParams);
	}
	
	protected void onDraw(Canvas c)
	{
		super.onDraw(c);
		Paint p = new Paint();
		p.setColor(Color.RED);
		
		c.drawCircle(40, 40, 40, p);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		this.bringToFront();
		int x = (int) event.getRawX();
		int y = (int) event.getRawY();

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) this
					.getLayoutParams();
			m_X = x - lParams.leftMargin;
			m_Y = y - lParams.topMargin;
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_MOVE:

			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this
					.getLayoutParams();
	
			
			layoutParams.leftMargin = x - m_X;
			
			layoutParams.topMargin = y - m_Y;
			
						
			if(x < 150 && layoutParams.leftMargin<1)
			{
				layoutParams.leftMargin = 0;
				
			}
			if(y < 150 && layoutParams.topMargin<1)
			{
				layoutParams.topMargin = 0;
				
				
			}
			if(((x + 150) > mWindowWidth) && layoutParams.leftMargin+149>mWindowWidth)
			{
				layoutParams.leftMargin = mWindowWidth - 150;
				
			}
			if((y + 64) > mWindowHeight && layoutParams.topMargin+230>mWindowWidth)
			{
				layoutParams.topMargin = mWindowHeight - 230;
			
			}
			layoutParams.rightMargin = -150;
			
			layoutParams.bottomMargin = -150;

			this.setLayoutParams(layoutParams);
			
			break;

		}
		this.invalidate();
		return true;
	}
}
