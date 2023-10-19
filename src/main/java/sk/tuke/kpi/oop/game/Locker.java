package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;

public class Locker extends AbstractActor implements Usable<Ripley> {


    private boolean isOpened;
    private final Animation animation;
    private Hammer hammer;

    public Locker() {
        this.animation = new Animation("sprites/locker.png", 16, 16);
        setAnimation(this.animation);
        this.isOpened = false;
        this.hammer=new Hammer();
    }

    @Override
    public void useWith(Ripley actor) {
        if(!this.isOpened){
            this.isOpened=true;
            Objects.requireNonNull(actor.getScene()).addActor(hammer,getPosX(),getPosY());
        }

    }


    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
