package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {

    private final Switchable switchable;


    public PowerSwitch(Switchable switchable) {
        this.switchable = switchable;
        //    private Reactor reactor;
        Animation animationController = new Animation("sprites/switch.png", 16, 16, 0.1f, Animation.PlayMode.ONCE);
        setAnimation(animationController);
    }

//    public void toggle(){
//        if(reactor.isRunning()){
//            reactor.turnOff();
//        } else {
//            reactor.turnOn();
//        }
//    }

    public Switchable getDevice(){
        return this.switchable;
    }

    public void switchOn() {
        if (switchable != null)
            this.switchable.turnOn();
            getAnimation().setTint(Color.WHITE);
    }

    public void switchOff() {
        if (switchable != null)
            this.switchable.turnOff();
            getAnimation().setTint(Color.GRAY);
    }


}
