package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.Player;

import java.awt.geom.Rectangle2D;
import java.util.Objects;

public interface TeleportImpl {

    default Player findPlayer(Teleport teleport) {
        for (Actor actor : Objects.requireNonNull(teleport.getScene()).getActors())
            if (actor.getClass() == Player.class && teleport.getDestination() != null && playerInTeleport((Player) actor,teleport) )
                return ((Player) actor);
        return null;
    }

    private boolean playerInTeleport(Player actor, Teleport teleport) {
        Rectangle2D activationZone = new Rectangle2D.Float(teleport.getPosX(), teleport.getPosY(), teleport.getWidth(), teleport.getHeight());
        return activationZone.contains(actor.getPosX() + (float) (actor.getWidth() / 2), actor.getPosY() + (float) (actor.getHeight() / 2));
    }



}
