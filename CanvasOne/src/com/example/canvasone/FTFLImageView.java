package com.example.canvasone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FTFLImageView extends ImageView {

	int m_X;
	int m_Y;

	public FTFLImageView(Context context) {
		super(context);
		init();
	}
	
	 public FTFLImageView(Context context, AttributeSet attrs) {
	        super(context, attrs, 0);
	    }	
	 
	 private void init() {
			// TODO Auto-generated method stub
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
					150, 150);
			this.setImageResource(R.drawable.ic_launcher);
			this.setLayoutParams(layoutParams);
		}
	
	protected void onDraw(Canvas c){
		super.onDraw(c);
		Paint p = new Paint();
		p.setColor(Color.CYAN);
		c.drawCircle(100, 100, 50, p);
	}

	

	
}
