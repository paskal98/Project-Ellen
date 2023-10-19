package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer {

    private boolean lighting;
    private Animation animationLight;
    private boolean powerIn;


    public Light() {
        this.animationLight = new Animation("sprites/light_off.png", 16, 16, 0.1f, Animation.PlayMode.ONCE);
        setAnimation(this.animationLight);
        this.lighting =false;
        this.powerIn =false;
    }

    private void setUpLightAnimation(){
        if(this.lighting && this.powerIn){
            this.animationLight = new Animation("sprites/light_on.png", 16, 16, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(this.animationLight);
        } else {
            this.animationLight = new Animation("sprites/light_off.png", 16, 16, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(this.animationLight);
        }
    }


    // update light in
    public void toggle() {
        if (this.lighting) turnOff();
        else turnOn();
    }

//    public void setElectricityFlow(boolean powered) {
//        this.powerin=powered;
//
//        if (this.light == true && powered==false) {
//            this.toggle();
//        }
//
//    }

    @Override
    public void turnOn() {
        this.lighting = true;
        setUpLightAnimation();
    }

    @Override
    public void turnOff() {
        this.lighting = false;
        setUpLightAnimation();
    }

    @Override
    public boolean isOn() {
        return this.lighting;
    }

    @Override
    public void setPowered(boolean powered) {
        this.powerIn =powered;
        setUpLightAnimation();
    }
}
