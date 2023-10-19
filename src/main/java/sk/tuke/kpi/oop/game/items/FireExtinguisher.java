package sk.tuke.kpi.oop.game.items;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible {

    public FireExtinguisher() {
        super(1);
        setAnimation(new Animation("sprites/extinguisher.png"));
    }


    public void useWith(Reactor extinguishable) {
        if (extinguishable != null && extinguishable.extinguish())
            super.useWith( extinguishable);
    }


    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }
}
