package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;


public class KeeperController implements KeyboardListener {

    private final Keeper keeper;

    public KeeperController(Keeper keeper){
        this.keeper=keeper;
    }

    @Override
    public void keyPressed(@NotNull  Input.Key key) {
        if(key == Input.Key.ENTER){
            new Take<>().scheduleFor(keeper);
        }
         else if(key == Input.Key.BACKSPACE){
            new Drop<>().scheduleFor(keeper);
        }
         else if(key == Input.Key.S){
            new Shift<>().scheduleFor(keeper);
        }
         else if(key == Input.Key.U){

            for (Actor find : keeper.getScene()) {
                if (find instanceof Usable) {
                    new Use<>((Usable<?>) find).scheduleForIntersectingWith(keeper);
                    break;
                }
            }

        }
         else if (key == Input.Key.B && keeper.getBackpack().peek() instanceof Usable<?>) {
             new Use<>((Usable<?>)keeper.getBackpack().peek()).scheduleForIntersectingWith(keeper);
        }

    }
}
