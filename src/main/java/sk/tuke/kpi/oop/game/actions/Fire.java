package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;

import java.util.Objects;

public class Fire<A extends Armed> extends AbstractAction<A> {

    private Fireable bullet;

    private int bulletX;
    private int bulletY;


    private void  bulletSetUp(){
        this.bullet = Objects.requireNonNull(super.getActor()).getFirearm().fire();

        this.bulletX = super.getActor().getPosX();
        this.bulletY = super.getActor().getPosY();

        setDone(true);
    }

    @Override
    public void execute(float deltaTime) {
        if( super.getActor()==null ||  super.getActor().getFirearm().fire()==null) {
            setDone(true);
            return;
        }

        this.bulletSetUp();

        Objects.requireNonNull(super.getActor().getScene()).addActor(this.bullet, this.bulletX, this.bulletY);

        new Move<>(Direction.fromAngle(super.getActor().getAnimation().getRotation()), Float.MAX_VALUE).scheduleFor(this.bullet);
    }
}
