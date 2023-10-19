package sk.tuke.kpi.oop.game;


public class ChainBomb extends TimeBomb implements ChainBombImpl {

    public ChainBomb(float time) {
        super(time);
    }

    @Override
    public void explodedTimeBomb() {
        super.explodedTimeBomb();
        interactNearby(this,setBorders(this,50,100));
    }
}
