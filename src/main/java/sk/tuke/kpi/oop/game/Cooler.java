package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {

    private final Reactor reactor;
    private boolean isOn;

    public Cooler(Reactor reactor) {
        this.reactor = reactor;
        this.isOn =false;
        Animation animationColler = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(animationColler);

    }

    @Override
    public void turnOn(){
        this.isOn =true;
    }

    @Override
    public void turnOff(){
        this.isOn =false;
    }

    @Override
    public boolean isOn(){
        return this.isOn;
    }

    private void coolReactor(){
        if (reactor != null && isOn)
            this.reactor.decreaseTemperature(1);
    }

    public Reactor getReactor() {
        return reactor;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);

    }

}
