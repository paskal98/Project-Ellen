package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public interface ChainBombImpl {

    default void interactNearby( ChainBomb chainBomb, Ellipse2D.Float borders){
        for (Actor actor : Objects.requireNonNull(chainBomb.getScene()).getActors())
            if (actor.getClass() == ChainBomb.class && !((ChainBomb) actor).isActivated() && borders.intersects(nearbyBomb(actor)) )
                ((ChainBomb) actor).activate();
    }

    default Ellipse2D.Float setBorders( ChainBomb chainBomb, int radius, int widthHeight){
        return new Ellipse2D.Float(chainBomb.getPosX() - radius + (float) chainBomb.getWidth() / 2, chainBomb.getPosY() - radius + (float) chainBomb.getHeight() / 2, widthHeight, widthHeight);
    }

    private Rectangle2D.Float nearbyBomb(@NotNull Actor actor){
        return new Rectangle2D.Float(actor.getPosX(), actor.getPosY(), actor.getWidth(), actor.getHeight());
    }

}
