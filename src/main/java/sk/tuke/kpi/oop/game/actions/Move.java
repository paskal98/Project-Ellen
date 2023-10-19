package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move  <T extends Movable> implements Action<T> {


    private T actor;
    private Direction direction;
    private float duration;
    private boolean done;
    private boolean activated=false;

    public Move(Direction direction, float duration) {
        this.done=false;
        this.direction=direction;
        this.duration=duration;
        this.activated=false;
    }

    public void stop() {
        if(this.actor == null) return;

        this.done = true;
        this.actor.stoppedMoving();

    }


    private void playerMoving(){
        actor.setPosition(actor.getPosX() + actor.getSpeed() * this.direction.getDx(), actor.getPosY() + actor.getSpeed() * this.direction.getDy());
        if(isCollided()) {
            playerStopping();
            actor.collidedWithWall();
        }
    }

    private boolean isCollided(){
        return actor.getScene().getMap().intersectsWithWall(actor);
    }

    private void playerStopping(){
        actor.setPosition(actor.getPosX() - actor.getSpeed() * this.direction.getDx(), actor.getPosY() - actor.getSpeed() * this.direction.getDy());
    }

    private void playerMove(){
        actor.startedMoving(direction);
        activated = true;
    }

    @Nullable
    @Override
    public T getActor() {
        return this.actor;
    }

    @Override
    public void setActor(@Nullable T actor) {
        this.actor=actor;
    }

    @Override
    public boolean isDone() {
        return this.done;
    }

    @Override
    public void execute(float deltaTime) {
        if(actor==null) return;
        duration -= deltaTime;

        if(isDone()) return;

        if (!activated) this.playerMove();

        if (duration <= 0) stop();
        else playerMoving();

    }

    @Override
    public void reset() {
        done = false;
        activated = false;
        duration = 0;
    }
}
