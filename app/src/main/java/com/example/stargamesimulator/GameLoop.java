package com.example.stargamesimulator;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{

    private static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;

    private boolean isPlaying = false;
    private final SurfaceHolder surfaceHolder;

    private final Game game;
    private double ups;
    private  double fps;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }


    public double getUPS() {

        return ups;
    }

    public double getFPS() {

        return fps;
    }

    public void startGame(){
        isPlaying = true;
        start();
    }

    @Override
    public void run(){
        super.run();
        Canvas canvas=null;

        int updateCount=0, frameCount=0;
        long startTime, elapsedTime, sleepTime;

        startTime = System.currentTimeMillis();
        while(isPlaying){
            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    game.update();
                    game.draw(canvas);
                    updateCount++;

                }
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            }finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            }


            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);

            if(sleepTime >0){
                try{
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while(sleepTime<0 && updateCount < MAX_UPS-1){
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
            }

            if(elapsedTime >= 1000) {
                ups = updateCount / (1E-3 * elapsedTime);
                fps = frameCount / (1E-3* elapsedTime);
                updateCount =0;
                frameCount =0;
                startTime = System.currentTimeMillis();

            }

        }
    }

}
