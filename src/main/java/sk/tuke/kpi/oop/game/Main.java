package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl.LwjglBackend;
import sk.tuke.kpi.gamelib.inspector.InspectableScene;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.scenarios.EscapeRoom;

import java.util.List;

public class Main {

    public static void main(String[] args) {





        // setting game window: window name and its dimensions
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 1000, 1000);

        // creating instance of game application
        // using class `GameApplication` as implementation of interface `Game`
        Game game = new GameApplication(windowSetup, new LwjglBackend()); // in case of macOS system use "new Lwjgl2Backend()" as the second parameter

        // creating scene for game
        // using class `World` as implementation of interface `Scene`
        Scene scene = new InspectableScene(new World("escape-room", "maps/escape-room.tmx", new EscapeRoom.Factory()), List.of("sk.tuke.kpi"));



        // adding scene into the game
        game.addScene(scene);

        //stop game by ESC
        game.getInput().onKeyPressed(Input.Key.ESCAPE, () -> game.stop());


//        TrainingGameplay trainingGameplay = new TrainingGameplay();
//        scene.addListener(trainingGameplay);




        EscapeRoom escapeRoom = new EscapeRoom(new Ripley());
        scene.addListener(escapeRoom);



        // running the game
        game.start();

    }

}
