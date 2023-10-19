package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

import java.util.Objects;

public class Ripley extends AbstractActor implements Movable, Keeper,Alive,Armed {

    private int X=101;
    private final int speed;
    private int ammo;
    private final Animation playerAnimation;
    private Firearm firearm;
    private Disposable disposable;

    private final Backpack backpack;
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);
    private int energy;
    private Health health;
    public Ripley() {
        super("Ellen");
        this.playerAnimation =new Animation("sprites/player.png",32,32,0.1f,Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(playerAnimation);
        this.playerAnimation.pause();

        this.speed=2;
        this.ammo=0;
        this.health = new Health(100);
        setFirearm(new Gun(100,500));

        this.backpack = new Backpack("Ripley's backpack", 3);
        this.disposable=null;

        this.health.onExhaustion(() -> {
            this.getScene().getMessageBus().publish(RIPLEY_DIED, this);
            this.setAnimation(new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE));
        });
    }


    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void showRipleyState(){
        int textY = (Objects.requireNonNull(getScene()).getGame().getWindowSetup().getHeight()) - GameApplication.STATUS_LINE_OFFSET;
        getScene().getGame().getOverlay().drawText("| Energy: " + Integer.toString(this.getHealth().getValue()), X+10, textY);
        getScene().getGame().getOverlay().drawText("| Ammo: " + Integer.toString(this.getFirearm().getAmmo()), 2*X+40+10, textY);
        getScene().getGame().getOverlay().drawText("| Max Ammo: " + Integer.toString(this.getFirearm().getMaxAmmo()), 3*X+50*2-10, textY);
    }

    public void decreaseEnergy() {

           this.disposable  = new Loop<>(
                new ActionSequence<>(
                    new Invoke<>(this::ripleyDying),
                    new Wait<>(1)
                )
            ).scheduleFor(this);
    }

    public Disposable stopDecreasingEnergy() {
        return disposable;
    }


    private void ripleyDying(){
        if (this.energy<= 0) {
            this.setAnimation(new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE));
            Objects.requireNonNull(getScene()).getMessageBus().publish(RIPLEY_DIED, this);
        }
        else this.energy-=2;
    }

    @Override
    public void startedMoving(Direction direction) {

        if(direction==null) return;
        if(direction == Direction.NONE) return;
        this.playerAnimation.setRotation(direction.getAngle());
        this.playerAnimation.play();

    }

    @Override
    public void stoppedMoving() {
        this.playerAnimation.pause();
    }


    @Override
    public int getSpeed() {
        return this.speed;
    }


    @Override
    public Backpack getBackpack() {
        return this.backpack;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        this.firearm = weapon;
    }

    @Override
    public Firearm getFirearm() {
        return firearm;
    }
}
