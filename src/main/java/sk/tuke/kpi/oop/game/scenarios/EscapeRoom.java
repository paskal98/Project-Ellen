package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;


public class EscapeRoom implements SceneListener {

    private Ripley ripley;

    public EscapeRoom(Ripley ripley){
        this.ripley=ripley;
    }


    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        for (Actor actor : scene.getActors()) {

            if(actor instanceof Ripley){
                this.ripley= (Ripley) actor;
            }

        }
        ripley.getHealth().restore();

        scene.getGame().pushActorContainer(ripley.getBackpack());




        scene.getGame().pushActorContainer(ripley.getBackpack());

        MovableController movableController = new MovableController(ripley);
        KeeperController keeperController = new KeeperController(ripley);
        ShooterController shooterController = new ShooterController(ripley);

        Disposable movableControllerDispose = scene.getInput().registerListener(movableController);
        Disposable keeperControllerDispose =  scene.getInput().registerListener(keeperController);
        Disposable shooterControllerDispose =  scene.getInput().registerListener(shooterController);


        scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Ripley) -> ripley.decreaseEnergy());

        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> movableControllerDispose.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> keeperControllerDispose.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> shooterControllerDispose.dispose());

        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, (Ripley) -> ripley.stopDecreasingEnergy().dispose());



    }

    @Override
    public void sceneCreated(@NotNull Scene scene) {

        scene.getMessageBus().subscribe(World.ACTOR_ADDED_TOPIC, actor -> {
            if(actor instanceof Alien){
                randomMovement((Alien) actor);
            }
        });
    }

    public void randomMovement(Alien alien){


            new Loop<>(
                new ActionSequence<>(
                    new Move<>(Direction.WEST, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.SOUTH, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.EAST, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.NORTH, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.WEST, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.SOUTH, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.EAST, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.NORTH, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.WEST, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.SOUTH, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.EAST, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.NORTH, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.WEST, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.SOUTH, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.EAST, (float) (Math.random() - 0.2f) * 2),
                    new Move<>(Direction.NORTH, (float) (Math.random() - 0.2f) * 2)
                )
            ).scheduleFor(alien);

    }




    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        if(ripley != null){
            ripley.showRipleyState();
        }
    }

    static public class Factory implements ActorFactory {

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            if(name == null){
                return null;
            }

            Actor actor;
            if(name.equals("ellen")){
                actor = new Ripley();
            }
            else if(name.equals("energy")){
                actor = new Energy();
            }
            else if(name.equals("alien")){
                actor = new Alien();
            }
            else if(name.equals("alien mother")){
                actor = new AlienMother();
            }
            else if(name.equals("ammo")){
                actor = new Ammo();
            }
            else{
                actor = null;
            }

            return actor;
        }
    }

}
