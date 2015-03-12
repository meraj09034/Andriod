package com.ftfl.bouncingball;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;

import com.example.bouncingball.R;
   
public class BouncingBallView extends View {
	
	private Bitmap bitmap1;
	/*private Bitmap bitmap2;
	private Bitmap bitmap3;
	private Bitmap bitmap4;*/
	
	
	private BitmapShader BMPshader1;
   private int xMin = 0;          // This view's bounds
   private int xMax;
   private int yMin = 0;
   private int yMax;
   private float ballRadius = 80; // Ball's radius
   private float ballX = ballRadius + 20;  // Ball's center (x,y)
   private float ballY = ballRadius + 40;
   private float ballSpeedX = 15;  // Ball's speed (x,y)
   private float ballSpeedY = 13;
   private RectF ballBounds; 
   
   
  /* private float ball1Radius = 120; // Ball's radius
   private float ball1X = ballRadius + 20;  // Ball's center (x,y)
   private float ball1Y = ballRadius + 40;
   private float ball1SpeedX = 8;  // Ball's speed (x,y)
   private float ball1SpeedY = 5;*/
   
   
   // Needed for Canvas.drawOval
   private RectF ballBounds1;  
   private Paint paint;   
   private Paint paint1;   // The paint (e.g. style, color) used for drawing
   
 
  
   // Constructor
   public BouncingBallView(Context context) {
      super(context);
      ballBounds = new RectF();
      ballBounds1 = new RectF();
      paint = new Paint();
      paint1 = new Paint();
   }
  
   // Called back to draw the view. Also called by invalidate().
   @Override
   protected void onDraw(Canvas canvas) {
      // Draw the ball
      ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
      /*ballBounds1.set(ball1X-ball1Radius, ball1Y-ball1Radius, ball1X+ball1Radius, ball1Y+ball1Radius);*/
      
      bitmap1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.ic_launcher);
		// Initialize the BitmapShader with the Bitmap object and set the
		// texture tile mode
      BMPshader1 = new BitmapShader(bitmap1, Shader.TileMode.REPEAT,
				Shader.TileMode.REPEAT);
      paint.setColor(Color.GREEN);
      paint.setShader(BMPshader1);
      paint1.setColor(Color.RED);
      canvas.drawOval(ballBounds, paint);
      
      //canvas.drawOval(ballBounds1, paint1);
      
        
     /* // Update the position of the ball, including collision detection and reaction.
      update();
    //  update1();
  
      // Delay
      try {  
         Thread.sleep(30);  
      } catch (InterruptedException e) { }
      
      invalidate(); */ // Force a re-draw
   }
   
   // Detect collision and update the position of the ball.
   
   public void setValue(float x,float y){
	   this.ballSpeedX=x;
	   this.ballSpeedX=y;
	   
	   update();
	   invalidate(); 
	   
	   
   }
   private void update() {
      // Get new (x,y) position
      ballX += ballSpeedX;
      ballY += ballSpeedY;
      // Detect collision and react 
      if (ballX + ballRadius > xMax) {
         ballSpeedX = -ballSpeedX;
         ballX = xMax-ballRadius;
      } else if (ballX - ballRadius < xMin) {
         ballSpeedX = -ballSpeedX;
         ballX = xMin+ballRadius;
      }
      if (ballY + ballRadius > yMax) {
         ballSpeedY = -ballSpeedY;
         ballY = yMax - ballRadius;
      } else if (ballY - ballRadius < yMin) {
         ballSpeedY = -ballSpeedY;
         ballY = yMin + ballRadius;
      }
      /*
      
      ball1X += ball1SpeedX;
      ball1Y += ball1SpeedY;
      // Detect collision and react
      if (ball1X + ball1Radius > xMax) {
         ball1SpeedX = -ball1SpeedX;
         ball1X = xMax-ball1Radius;
      } else if (ball1X - ball1Radius < xMin) {
         ball1SpeedX = -ball1SpeedX;
         ball1X = xMin+ball1Radius;
      }
      if (ball1Y + ball1Radius > yMax) {
         ball1SpeedY = -ball1SpeedY;
         ball1Y = yMax - ball1Radius;
      } else if (ball1Y - ball1Radius < yMin) {
         ball1SpeedY = -ball1SpeedY;
         ball1Y = yMin + ball1Radius;
      }
      
      if(ballBounds.intersect(ballBounds1)){
    	  paint.setColor(Color.CYAN);
      }*/
   }
   
  
   
   // Called back when the view is first created or its size changes.
   @Override
   public void onSizeChanged(int w, int h, int oldW, int oldH) {
      // Set the movement bounds for the ball
      xMax = w-1;
      yMax = h-1;
   }
   
   
}