package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

import java.util.Objects;

public class Alien extends AbstractActor implements Movable,Alive,Enemy {
    private Health health;
    private Animation animation;
    private final int speed = 1;


    public Alien(){
        animation = new Animation("sprites/alien.png", 32, 32, 0.1f);
        setAnimation(animation);
        health = new Health(100, 100);
        health.onExhaustion(() -> Objects.requireNonNull(getScene()).removeActor(this));
    }

    public Alien(int healthPoint){
        animation = new Animation("sprites/alien.png", 32, 32, 0.1f);
        setAnimation(animation);
        health = new Health(healthPoint, 100);
        health.onExhaustion(() -> Objects.requireNonNull(getScene()).removeActor(this));
    }


    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(
            new ActionSequence<>(
                new Invoke<>(() -> fight()),
                new Wait<>(0.2f)
            )).scheduleFor(this);

    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        this.animation.play();
    }

    @Override
    public void stoppedMoving() {
        this.animation.pause();
    }

    @Override
    public Health getHealth() {
        return health;
    }


    public void fight() {
        for (Actor aliveActor : Objects.requireNonNull(getScene()).getActors())
            if (aliveActor instanceof Alive && !(aliveActor instanceof Enemy) && this.intersects(aliveActor) && ((Alive) aliveActor).getHealth()!=null)
                    ((Alive) aliveActor).getHealth().drain(2);
    }



}
