package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;

import java.util.Objects;

public class Ammo extends AbstractActor implements Usable<Armed>{


    public Ammo() {
        setAnimation(new Animation("sprites/ammo.png",16,16));
    }

    private boolean isNotAmmoLimit(Armed ripley){
        return ripley.getFirearm().getAmmo()<500;
    }

    @Override
    public void useWith(Armed ripley) {
        if(ripley==null) return;

        if(isNotAmmoLimit(ripley)){
            ripley.getFirearm().reload(Math.min((ripley.getFirearm().getAmmo() + 50), 500));
            Objects.requireNonNull(this.getScene()).removeActor(this);
        }

    }

    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }

}
