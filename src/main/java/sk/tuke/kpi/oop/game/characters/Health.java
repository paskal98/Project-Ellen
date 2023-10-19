package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {

    private int currentHP;
    private int maxHP;
    private List<ExhaustionEffect> exhaustionEffects = new ArrayList<>();

    public Health(int startHP, int maxHP){
        this.currentHP = Math.min(startHP, maxHP);
        this.maxHP = maxHP;
    }

    public Health(int maxHP){
        this.currentHP = maxHP;
        this.maxHP = maxHP;
    }

    public int getValue(){
        return this.currentHP;
    }

    public void refill(int amount){
        this.currentHP = Math.min(this.currentHP + amount, maxHP);
    }

    public void restore(){
        this.currentHP = this.maxHP;
    }

    public void drain(int amount){
        if(this.currentHP == 0 )  return;

        if (currentHP >amount)  currentHP =currentHP - amount;
        else exhaust();

    }

    public void exhaust(){
        if (currentHP == 0 || exhaustionEffects==null ) return;

        currentHP = 0;

        for(ExhaustionEffect effect : exhaustionEffects)
            effect.apply();

    }

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public void onExhaustion(ExhaustionEffect effect){
        this.exhaustionEffects.add(effect);
    }

}
