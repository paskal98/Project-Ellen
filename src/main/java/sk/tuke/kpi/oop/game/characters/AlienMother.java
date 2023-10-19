package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class AlienMother extends Alien {

    private Animation animation;

    public AlienMother(){
        super(200);
        animation = new Animation("sprites/mother.png", 112, 162, 0.2f);
        setAnimation(animation);
        super.getHealth().onExhaustion(() -> {
            Objects.requireNonNull(this.getScene()).removeActor(this);
        });
    }
}
