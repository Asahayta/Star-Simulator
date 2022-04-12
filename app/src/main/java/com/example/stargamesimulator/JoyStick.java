package com.example.stargamesimulator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

 class JoyStick {
    private final Paint outerLayerPaint;
    private final Paint innerCirclePaint;

    private final int outerRadius;
    private final float outerPosX;
    private final float outerPosY;

    private final int innerRadius;
    private float innerPosX;
    private float innerPosY;

    private boolean pressed;

     public JoyStick(float centerX, float centerY, int outerRadius, int innerRadius){
        pressed = false;
        outerPosX = centerX;
        outerPosY = centerY;
        innerPosX = centerX;
        innerPosY  = centerY;

        this.outerRadius = outerRadius;
        this.innerRadius = innerRadius;

        outerLayerPaint = new Paint();
        outerLayerPaint.setColor(Color.GRAY);
        outerLayerPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.RED);
        innerCirclePaint.setStyle(Paint.Style.FILL);
    }

   public void draw(Canvas canvas) {
       canvas.drawCircle(outerPosX,outerPosY,outerRadius,outerLayerPaint);
       canvas.drawCircle(innerPosX,innerPosY,innerRadius,innerCirclePaint);
   }

   public void update(){

   }

     public boolean isPressed(float x, float y) {
         double joystickDistance = Math.sqrt((Math.pow(outerPosX - x, 2) + Math.pow(outerPosY - y, 2)));

        return joystickDistance < outerRadius;
     }

     public void setPos(float x, float y) {
         float dx,dy, deltaDist; // d for delta
         dx =  x - outerPosX;
         dy = y -outerPosY;
         deltaDist = (float) Math.sqrt((dx*dx) + (dy*dy));
         if(deltaDist < outerRadius){
             innerPosX += dx/outerRadius;
             innerPosY += dy/outerRadius;
         }
         else{
             innerPosX += dx/deltaDist;
             innerPosY += dy/deltaDist;
         }
       }

     public boolean getPressed() {
         return pressed;
     }

     public void setPressed(boolean pressed) {
         this.pressed = pressed;
     }

     public void stabilize() {
         pressed = false;
         this.innerPosX = outerPosX;
         this.innerPosY = outerPosY;
     }
 }
