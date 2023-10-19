package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class TimeBomb extends AbstractActor {

    private final float explodeTime;
    private boolean activated;


    public TimeBomb(float explodeTime) {
        this.explodeTime=explodeTime;
        this.activated = false;
        Animation timebomb = new Animation("sprites/bomb.png");
        setAnimation(timebomb);

    }

    private float getExplodeTime() {
        return explodeTime;
    }

    public  boolean isActivated() {
        return activated;
    }

    public void activate() {
        this.activated = true;
        setAnimation(new Animation("sprites/bomb_activated.png", 16, 16, 0.1f));
        new ActionSequence<>(
            new Wait<>(getExplodeTime()),
            new Invoke<>(this::explodedTimeBomb),
            new Wait<>(4),
            new Invoke<>(this::removeTimeBomb)
        ).scheduleFor(this);
    }

    private void removeTimeBomb() {
        Objects.requireNonNull(getScene()).removeActor(this);
    }

   public void explodedTimeBomb(){
        if (isActivated())
            setAnimation(new Animation("sprites/small_explosion.png", 16, 16, 0.5f, Animation.PlayMode.ONCE));
   }



}
