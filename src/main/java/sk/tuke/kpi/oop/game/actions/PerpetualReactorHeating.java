package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {

    private final int temperatureIncrease;

    public PerpetualReactorHeating(int temperatureIncrease) {
        this.temperatureIncrease = temperatureIncrease;
    }

    public int getTemperatureIncrease() {
        return temperatureIncrease;
    }

    @Override
    public void execute(float deltaTime) {
        Reactor reactor = this.getActor();
        if(reactor !=null ){
            reactor.increaseTemperature(getTemperatureIncrease());
        }
    }

    @Override
    public @Nullable Reactor getActor() {
        return super.getActor();
    }
}
