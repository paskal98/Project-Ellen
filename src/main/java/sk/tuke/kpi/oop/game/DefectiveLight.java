package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class DefectiveLight extends Light implements Repairable{

    private boolean repaired;
    private Disposable dispose;

    public DefectiveLight() {
        super();
        this.repaired=false;
    }

    private void changeLight() {
        if ((int) (Math.random() * 20) == 1)
            super.toggle();
    }

    private boolean processDispose(){
        this.repaired = true;
        dispose.dispose();
        dispose = new ActionSequence<>(new Wait<>(10),new Loop<>(new Invoke<>(this::changeLight))).scheduleFor(this);
        return true;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        dispose=new Loop<>(new Invoke<>(this::changeLight)).scheduleFor(this);
    }

    @Override
    public boolean repair() {
        return !repaired && dispose != null && processDispose();
    }


}
