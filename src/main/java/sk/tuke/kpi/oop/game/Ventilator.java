package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

import java.util.Objects;

public class Ventilator extends AbstractActor implements Repairable{

    private boolean isRepaired;
    private final Animation animation;
    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("ventilator repaired", Ventilator.class);
    public Ventilator(){
        this.animation = new Animation("sprites/ventilator.png", 32, 32, 0.1f);
        animation.pause();
        setAnimation(animation);
        this.isRepaired = false;
    }

    @Override
    public boolean repair() {
        if(this.isRepaired) return false;

        Objects.requireNonNull(getScene()).getMessageBus().publish(VENTILATOR_REPAIRED,this);
        this.isRepaired = true;
        this.getAnimation().play();
        return true;
    }


}
