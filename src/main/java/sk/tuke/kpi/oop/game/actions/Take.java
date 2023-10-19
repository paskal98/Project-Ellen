package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class Take<T extends Keeper> extends AbstractAction<T> {

    @Override
    public void execute(float deltaTime) {
        if(super.getActor() == null) {setDone(true); return;}

        try {
            for (Actor actor : super.getActor().getScene().getActors()) {
                if (actor instanceof Collectible && playerIntersectsItem(actor) && isBackPackNotFull() ) {
                    takeItem(actor);
                    setDone(true);
                    break;
                }
            }
        } catch (Exception e){
            setDone(true);
        }
        setDone(true);
    }



    private boolean playerIntersectsItem(Actor actor) {
        Rectangle2D activationZone = new Rectangle2D.Float(super.getActor().getPosX(), super.getActor().getPosY(), super.getActor().getWidth(), super.getActor().getHeight());
        return activationZone.contains(actor.getPosX() + (float) (actor.getWidth() / 2), actor.getPosY() + (float) (actor.getHeight() / 2));
    }

    private boolean isBackPackNotFull() {
        return Objects.requireNonNull(super.getActor()).getBackpack().getCapacity() > super.getActor().getBackpack().getSize();
    }

    private void takeItem(Actor actor) {
        Objects.requireNonNull(super.getActor()).getBackpack().add((Collectible) actor);
        Objects.requireNonNull(actor.getScene()).removeActor(actor);
    }


}
