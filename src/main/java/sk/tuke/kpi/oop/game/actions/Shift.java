package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Shift<T extends Keeper> extends AbstractAction<T> {
    @Override
    public void execute(float deltaTime) {
        if(super.getActor() != null) super.getActor().getBackpack().shift();
        setDone(true);
    }
}
