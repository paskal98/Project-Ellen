package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Teleport extends AbstractActor implements TeleportImpl {

    private Teleport destination;
    private final TeleportData teleportData = new TeleportData();

    public Teleport(Teleport destination){
        if (destination != this)
            this.destination = destination;
        setAnimation(new Animation("sprites/lift.png"));
        teleportData.setStatusActive(true);

    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<Teleport>(this::teleportProcess)).scheduleFor(this);

    }

    public Teleport getDestination(){
        return destination;
    }

    public void setDestination(Teleport destinationTeleport){
        if (destinationTeleport != this)
            this.destination = destinationTeleport;
    }

    private void teleportProcess(){
        if(getDestination()!=null && findPlayer(this)!=null ){
            if (teleportData.isStatusActive())
                getDestination().teleportPlayer(findPlayer(this));
        } else teleportData.setStatusActive(true);


    }

    public void teleportPlayer( Player player) {
        if(teleportData.isStatusActive())
            player.setPosition(this.getPosX() + (this.getHeight() / 2) - (player.getHeight() / 2), this.getPosY() + (this.getWidth() / 2) - (player.getWidth() / 2));
        teleportData.setStatusActive(false);
    }


}
