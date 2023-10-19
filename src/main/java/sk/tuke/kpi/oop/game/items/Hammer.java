package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Repairable;


public class Hammer extends BreakableTool<Repairable> implements  Collectible{

    public Hammer() {
        super(1);
        setAnimation(new Animation("sprites/hammer.png"));
    }

    public Hammer(int uses) {
        super(uses);
        setAnimation(new Animation("sprites/hammer.png"));
    }


    public void useWith(Repairable repairable) {
        if(repairable!=null && repairable.repair())
            super.useWith(repairable);
    }

    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }




}
