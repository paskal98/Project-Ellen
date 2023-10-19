package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {

    private boolean powered;

    public Computer() {
        Animation normalAnimation = new Animation("sprites/computer.png", 80, 48, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(normalAnimation);
    }


    public int add (int a, int b){
        return  this.powered ? a+b : 0 ;
    }

    public int sub (int a, int b){
        return this.powered ? a-b : 0;
    }

    public float add (float a,float b){
        return this.powered ? a+b : 0;
    }

    public float sub (float a,float b){
        return this.powered ? a-b : 0;
    }


    @Override
    public void setPowered(boolean powered) {
        this.powered=powered;
    }
}
