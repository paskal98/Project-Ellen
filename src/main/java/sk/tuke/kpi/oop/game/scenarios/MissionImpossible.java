package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class MissionImpossible implements SceneListener {

    private Ripley ripley;

    public MissionImpossible(Ripley ripley){
        this.ripley=ripley;

    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        if(ripley != null){
            ripley.showRipleyState();
        }
    }


    @Override
    public void sceneInitialized(@NotNull Scene scene) {





    }

    static public class Factory implements ActorFactory {

        @Override
        public Actor create(String type, String name) {


            return null;

        }
    }



}
