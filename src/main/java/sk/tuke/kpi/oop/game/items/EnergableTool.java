package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

import java.util.Objects;

public abstract class EnergableTool <A extends Actor> extends AbstractActor implements Usable<A> {

    private int remainingUses;

    public void useWith(A actor) {
        this.remainingUses=this.remainingUses-1;
        if (this.remainingUses==0){
            Objects.requireNonNull(this.getScene()).removeActor(this);
        }
    }

    protected EnergableTool(int remainingUses) {
        this.remainingUses = remainingUses;
    }

    public int getRemainingUses() {
        return remainingUses;
    }

}
