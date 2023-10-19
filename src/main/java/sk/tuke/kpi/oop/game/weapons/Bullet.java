package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

import java.util.Objects;

public class Bullet extends AbstractActor implements Fireable {
    private int speed;
    private int damage;

    public Bullet() {
        setAnimation(new Animation("sprites/bullet.png", 16, 16));
        this.setSpeed(4);
        this.setDamage(30);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(
            new Invoke<>(this::fire)
        ).scheduleFor(this);

    }


    private void fire() {
        for (Actor actor : Objects.requireNonNull(getScene()).getActors())
            if ( !(actor instanceof Ripley) && (actor instanceof Alive) && this.intersects(actor) )
                aliveHitted((Alive)actor);

    }

    private void aliveHitted(Alive actor){
        actor.getHealth().drain(damage);
        collidedWithWall();
    }


    @Override
    public void startedMoving(Direction direction) {
        if (direction != null && direction != Direction.NONE)
            this.getAnimation().setRotation(direction.getAngle());
    }

    @Override
    public int getSpeed() {
        return speed;
    }


    @Override
    public void collidedWithWall() {
        Objects.requireNonNull(getScene()).removeActor(this);
    }
}
