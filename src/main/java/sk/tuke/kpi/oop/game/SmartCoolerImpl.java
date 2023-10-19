package sk.tuke.kpi.oop.game;


import org.jetbrains.annotations.NotNull;

public interface SmartCoolerImpl {

    default void smartCooling(@NotNull SmartCooler smartCooler){
        Reactor reactor = smartCooler.getReactor();
        if(reactor!=null) settingSmartCooler(smartCooler,reactor);
    }

    private void  settingSmartCooler(SmartCooler smartCooler, @NotNull Reactor reactor){
        if (reactor.getTemperature() <= 1500)
            smartCooler.turnOff();
        else if (reactor.getTemperature() >= 2500)
            smartCooler.turnOn();
    }

}
