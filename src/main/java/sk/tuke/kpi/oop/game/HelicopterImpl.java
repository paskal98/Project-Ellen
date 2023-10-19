package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.Player;

import java.util.Objects;

public interface HelicopterImpl {

    default Player getPlayerFromScene(@NotNull Helicopter helicopter) {
        for (Actor actor : Objects.requireNonNull(helicopter.getScene()).getActors())
            if (actor.getClass() == Player.class)
                return (Player) actor;
        return null;
    }

    default void startHunt(Player player, @NotNull Helicopter helicopter) {
        helicopter.setPosition(this.initCordX(player, helicopter), this.initCordY(player, helicopter));
        if (helicopter.intersects(player)) player.setEnergy(player.getEnergy() - 1);
    }


    private int initCordX(@NotNull Player player, @NotNull Helicopter helicopter) {
        return (helicopter.getPosX() + helicopter.getWidth() - (player.getPosX() + (player.getWidth() / 2)) > 0) ? helicopter.getPosX() - 1 : helicopter.getPosX() + 1;
    }

    private int initCordY(@NotNull Player player, @NotNull Helicopter helicopter) {
        return (helicopter.getPosY() + helicopter.getHeight() - (player.getPosY() + (player.getHeight() / 2)) > 0) ? helicopter.getPosY() - 1 : helicopter.getPosY() + 1;
    }


}
