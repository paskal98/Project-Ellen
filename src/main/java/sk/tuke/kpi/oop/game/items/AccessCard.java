package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class AccessCard extends AbstractActor implements Collectible,Usable<LockedDoor>{

    public AccessCard(){
        setAnimation(new Animation("sprites/key.png"));
    }

    @Override
    public void useWith(LockedDoor actor) {
        if (actor == null) return;
        actor.open();
    }

    @Override
    public Class<LockedDoor> getUsingActorClass() {
        return LockedDoor.class;
    }
}
