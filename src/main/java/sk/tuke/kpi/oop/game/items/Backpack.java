package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {

    private final String name;
    private final int capacity;
    private final List<Collectible> backPackCapacity = new ArrayList<>();

    public Backpack(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
    }


    @Override
    public @NotNull List<Collectible> getContent() {
        return new ArrayList<>(this.backPackCapacity);
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getSize() {
        return this.backPackCapacity.size();
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public void add(@NotNull Collectible actor) {


        if (this.backPackCapacity.size() < getCapacity())
            this.backPackCapacity.add(actor);
        else
            throw new IllegalStateException(this.getName()+" is full");
    }

    @Override
    public void remove(@NotNull Collectible  actor) {
        this.backPackCapacity.remove(actor);
    }

    @Override
    public @Nullable Collectible  peek() {
        if(this.getSize() == 0) return null;
        else return this.backPackCapacity.get(getSize() - 1);
    }

    private Collectible getLastItem(){
        return this.backPackCapacity.get(getSize() - 1);
    }

    @Override
    public void shift() {
        if(getSize() > 1){
            Collectible item = this.getLastItem();
            this.backPackCapacity.remove(item);
            this.backPackCapacity.add(0, item);
        }
    }

    @Override
    public Iterator<Collectible> iterator() {
        return this.backPackCapacity.iterator();
    }
}
