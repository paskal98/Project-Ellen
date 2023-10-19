package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;


public class Use<T extends Actor> extends AbstractAction<T> {

    private Usable<T> usable;

    public Use(Usable<T> usable){
        this.usable = usable;
    }

    @Override
    public void execute(float deltaTime) {

//        if(super.getActor().getClass() == Ripley.class){
//
//            if(playerIntersectsItem((Actor) this.usable)){
//                this.usable.useWith(super.getActor());
//                setDone(true);
//            }
//
//        }
//        else {
//            System.out.println("+++");
//            this.usable.useWith(super.getActor());
//            setDone(true);
//        }


//        if (!isDone()) {
//
//            if(Objects.requireNonNull(super.getActor()).getClass() == Ripley.class){
//                System.out.println(this.usable);
//                if(playerIntersectsItem((Actor) this.usable)){
//                    this.usable.useWith(super.getActor());
//                    setDone(true);
//                }
//                return;
//            }
//
//            setDone(true);
//            this.usable.useWith(getActor());
//        }
//        setDone(true);

        if(isDone()) return;
        setDone(true);
        this.usable.useWith(getActor());

    }

//    private void useProcess(){
//
//        for (Actor actor : Objects.requireNonNull(super.getActor().getScene()).getActors()) {
//            if ((actor.getClass() == Energy.class )&& playerIntersectsItem( actor)) {
//
//                this.usable.useWith(super.getActor());
//                setDone(true);
//
//                break;
//
//            }
//        }
//    }

//    private boolean playerIntersectsItem( Actor actor) {
//        Rectangle2D activationZone = new Rectangle2D.Float(super.getActor().getPosX(), super.getActor().getPosY(), super.getActor().getWidth(), super.getActor().getHeight());
//        return activationZone.contains(actor.getPosX() + (float) (actor.getWidth() / 2), actor.getPosY() + (float) (actor.getHeight() / 2));
//    }

    public Disposable scheduleForIntersectingWith(Actor mediatingActor) {

        Scene scene = mediatingActor.getScene();

        if (scene == null) return null;
        Class<T> usingActorClass = usable.getUsingActorClass();  // `usable` je spominana clenska premenna

        for (Actor actor : scene) {
            if (mediatingActor.intersects(actor) && usingActorClass.isInstance(actor)) {
                System.out.println("actor");
                return this.scheduleFor(usingActorClass.cast(actor));  // naplanovanie akcie v pripade, ze sa nasiel vhodny akter
            }
        }
        return null;
    }


}
