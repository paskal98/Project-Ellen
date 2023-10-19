package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.Objects;


public class Drop <T extends Keeper> extends AbstractAction<T> {

    private Collectible getBackPackPeek(){

        if( super.getActor() != null && super.getActor().getBackpack().peek()!=null)
            return super.getActor().getBackpack().peek();

        setDone(true);
        return null;
    }

    @Override
    public void execute(float deltaTime) {
        Collectible item =getBackPackPeek();

        if(item == null)
            return;

        Objects.requireNonNull(super.getActor().getScene()).addActor(item,super.getActor().getPosX() + super.getActor().getWidth() / 2 - item.getWidth() / 2, super.getActor().getPosY() + super.getActor().getHeight() / 2 - item.getHeight() / 2);
        super.getActor().getBackpack().remove(item);
        setDone(true);
    }
}
