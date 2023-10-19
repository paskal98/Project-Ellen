package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable,Repairable,Extinguishable {

    private boolean turn;

    private int temperature;
    private int damage;
    private final Animation overhittingReactorAnimation ;
    private final Animation onReactorAnimation ;
    private final Animation offReactorAnimation ;
    private final Animation breakReactorAnimation ;
    private final Animation extinguishedReactorAnimation ;

    private final Set<EnergyConsumer> devices ;

    public Reactor() {
        this.overhittingReactorAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.onReactorAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.offReactorAnimation = new Animation("sprites/reactor.png", 80, 80, 0.1f, Animation.PlayMode.ONCE);
        this.extinguishedReactorAnimation = new Animation("sprites/reactor_extinguished.png", 80, 80, 0.1f, Animation.PlayMode.ONCE);
        this.breakReactorAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP);

        setAnimation(this.offReactorAnimation);
        this.temperature=0;
        this.damage=0;
        this.turn=false;

        this.devices = new HashSet<>();
    }

    @Override
    public void turnOn(){
        if(this.damage!=100){
            setAnimation(this.onReactorAnimation);
            this.turn=true;
            if(this.devices!=null){
                for(EnergyConsumer item : devices){
                    item.setPowered(true);
                }
            }

        } else {
            this.turn=false;
            setAnimation(this.breakReactorAnimation);
        }

    }

    @Override
    public void turnOff(){
        if(this.damage!=100) {
            this.turn = false;
            setAnimation(this.offReactorAnimation);
            if(this.devices!=null){
                for(EnergyConsumer item : devices){
                    item.setPowered(false);
                }
            }
        } else {
            this.turn=false;
            setAnimation(this.breakReactorAnimation);
        }

    }

    @Override
    public boolean isOn() {
        return this.turn;
    }

    public void increaseTemperature(int increment){
    int temp ;
       if (this.isOn()){
           // #case if increment negative number
           if(increment<0) return;

           //set koef for increment
           if (getDamage()>=33 && getDamage()<=66){
               temp=(int)(increment*1.5f);
           } else if (getDamage()>66){
               temp=increment*2;
           } else {
               temp=increment;
           }

           // set temperature after and working with above parameters
           this.temperature=getTemperature()+temp;
           //if (getTemperature()>6000) {this.temperature=6000; };

           //working while reactor is not damaged 100%. After damage reactor scripts dont go
           //if(getTemperature()<6000 && getDamage()<100) {

               //update animation reactor
               updateAnimation();

               //set damage
               updateDamage();

           //}
       }


    }

    public void decreaseTemperature(int decrement){
    int temp;
        if(this.isOn() && decrement >= 0 && getDamage()!=100){
            // #case if decrement negative number
//            if(decrement<0 && getDamage()==100) return;

            //set koef to decrement by damage
            if (getDamage()>50) temp=(int)(decrement/2);
            else temp=decrement;

            //set temperature and working with zero parameters
            this.temperature=getTemperature()-temp;
            if(getTemperature()<0) this.temperature=0;

            //working if damage below 100 and temperature not high
            if(getDamage()<100 && getTemperature()<6000) {

                //update animation reactor
                updateAnimation();

                //set damage
                //updateDamage();
            }
        }

    }


    public boolean repair (){
            if((getDamage()<=0 || getDamage()>=100)){
                return false;
            }
            if((getDamage()-50)<0) {
                this.damage=0;
                updateTemperature();
                updateAnimation();
                return true;
            } else {
                this.damage-=50;
                updateTemperature();
                updateAnimation();
                return true;
            }

    }

    public boolean extinguish(){
        if(this.getDamage()==100){
            this.temperature=4000;
            setAnimation(this.extinguishedReactorAnimation);
            return true;
        }
        return false;
    }

    public void addDevice(EnergyConsumer device){
        this.devices.add(device);
        if (damage != 100)
            device.setPowered(isOn());
    }

    public void removeDevice(EnergyConsumer device){
        device.setPowered(false);
        this.devices.remove(device);
    }

    private void updateAnimation(){

        float animationDuration = ((-8*getDamage()+1000)/200)*0.01f;
        if (getTemperature() >= 4000 && getTemperature() < 6000) {

            this.overhittingReactorAnimation.setFrameDuration(animationDuration);
            setAnimation(this.overhittingReactorAnimation);
        } else if (getTemperature() >= 6000) {
            this.turnOff();
            setAnimation(this.breakReactorAnimation);

        } else {
            this.onReactorAnimation.setFrameDuration(animationDuration);
            setAnimation(this.onReactorAnimation);
        }

    }

    private void updateDamage(){
        if (getTemperature() > 2000 ) {
            this.damage = (int) ((2.5f * getTemperature() - 5000) / 100);
            if(this.damage>100) this.damage=100;
        } else if (getTemperature() <= 2000) {
            this.damage = 0;
        }
    }

    private void updateTemperature(){
        if (getDamage() > 0 && getDamage() < 100) {
            this.temperature=(int)(((getDamage()*100)+5000)/2.5f);
        } else if (getDamage() <= 0) {
            this.temperature = 0;
        }
    }



    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }


}
