package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.Movable;

import java.util.*;

public class MovableController implements KeyboardListener {


    private final Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST)

    );
    private final Movable movable;
    private Move<Movable> move;

    private final List<Input.Key> keyList = new ArrayList<>();

    public MovableController(Movable movable) {
        this.movable = movable;
    }

    private void nullCheck() {
        if (this.move != null)
            this.move.stop();

        if (this.movable != null)
            this.movable.stoppedMoving();
    }

    private void keySetUp(Map.Entry<Input.Key, Direction> entry, String type) {
        if (Objects.equals(type, "add"))
            keyList.add(entry.getKey());
        else if (Objects.equals(type, "remove"))
            keyList.remove(entry.getKey());
    }

    private Direction directionSetUp() {
        Direction direction = Direction.NONE;
        for (Input.Key k : keyList)
            direction = direction.combine(keyDirectionMap.get(k));
        return direction;
    }

    private void keyProcess(Input.Key key, String type) {


        for (Map.Entry<Input.Key, Direction> entry : this.keyDirectionMap.entrySet()) {
            if (entry.getKey() == key) {

                this.keySetUp(entry, type);

                this.nullCheck();

                this.move = new Move<>(this.directionSetUp(), Float.MAX_VALUE);

                assert this.movable != null;
                this.move.scheduleFor(this.movable);

            }
        }

    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {

        if (keyDirectionMap.containsKey(key))
            this.keyProcess(key, "add");

    }


    @Override
    public void keyReleased(@NotNull Input.Key key) {

        if (keyDirectionMap.containsKey(key))
            this.keyProcess(key, "remove");

    }


}
