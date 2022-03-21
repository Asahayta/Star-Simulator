package com.example.stargamesimulator;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private JoyStick joyStick;
    private GameLoop gameloop;
    private Context context;
    private CelestialBody myStar;

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.context = context;
        gameloop = new GameLoop(this, surfaceHolder);

        setFocusable(true);
    }

        @Override
        public void draw(Canvas canvas){
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        joyStick.draw(canvas);
        myStar.draw(canvas);
    }

    public void drawUPS(Canvas canvas){
        String ups = Double.toString(gameloop.getUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.warmBlue);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + ups,  200, 500, paint);
    }
    public void drawFPS(Canvas canvas){
        String fps = Double.toString(gameloop.getFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.warmBlue);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + fps,  100, 100, paint);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameloop.startGame();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    public void update() {

    }
        /*
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        joyStick = new JoyStick();

        setContentView(R.layout.activity_game);
    }
        */


}