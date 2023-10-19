package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

public class LockedDoor extends Door {

    private boolean isLocked;

    public LockedDoor(){
        super();
        this.isLocked = true;
    }

    @Override
    public void useWith(Actor actor) {

    }

    public void lock(){

    }

    public void unlock(){

    }

    public boolean isLocked(){
        return isLocked;
    }


}
