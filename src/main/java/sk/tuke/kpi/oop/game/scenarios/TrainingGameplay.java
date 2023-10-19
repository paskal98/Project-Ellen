package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.items.Hammer;

public class TrainingGameplay extends Scenario implements SceneListener {

    private Ripley ripley;
    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        if(ripley != null){
            ripley.showRipleyState();
        }
    }

    @Override
    public void setupPlay(@NotNull Scene scene) {


    }
}
