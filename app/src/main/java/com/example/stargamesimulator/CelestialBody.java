package com.example.stargamesimulator;

import android.graphics.Canvas;

abstract class CelestialBody {
    static final double G = (6.6743 * 10E-11); // Gravitation constant
    float mass, force, accel, velocity, size;
    int posX, posY;

    public void draw(Canvas canvas) {}
    public void setPos(CelestialBody otherBody,float theta) {}

}
class Player extends CelestialBody{

    public Player(float mass,float size, int initialX, int initialY){
       this.mass = mass;
       this.size = size;
       this.posX = initialX;
       this.posY = initialY;
    }
   public void attractionForce(CelestialBody otherBody){//Gets the attraction force between 2 bodies and sets their force
        float radius = (float) (Math.sqrt(Math.pow((this.posX - otherBody.posX),2) + Math.pow((this.posY - otherBody.posY),2)));
        float gravForce = (float)(G*this.mass*otherBody.mass/(radius*radius));

        this.force = gravForce/ otherBody.mass;
        //otherBody.force = gravForce/this.mass;

        float theta = (float)Math.PI;
        setPos(otherBody,theta);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
    @Override
    public void setPos(CelestialBody otherBody,float theta){

        this.posX += otherBody.velocity*Math.cos(theta);
        this.posY += otherBody.velocity*Math.sin(theta);
    }
}

class Star extends CelestialBody {

}
class BlackHole extends CelestialBody {

}
class Planet extends CelestialBody {

}