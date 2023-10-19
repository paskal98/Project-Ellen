package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Helicopter extends AbstractActor implements HelicopterImpl {
   private  Player player ;
    public Helicopter() {
        setAnimation(new Animation("sprites/heli.png", 64, 64, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
    }

    public void searchAndDestroy(){

        this.player= getPlayerFromScene(this);

        if (player!=null) {
            new Loop<>(
                new Invoke<>(() -> { startHunt(player,this);})
            ).scheduleFor(this);
        }
    }

}
