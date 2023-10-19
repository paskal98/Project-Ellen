package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {

    private int currentAmmo;
    private int maxAmmo;


    public Firearm(int currentAmmo, int maxAmmo) {
        this.currentAmmo = currentAmmo;
        this.maxAmmo=maxAmmo;
    }

    public Firearm(int maxAmmo) {
        this.currentAmmo = maxAmmo;
        this.maxAmmo=maxAmmo;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public Fireable fire(){
        if(this.currentAmmo == 0) return null;
        this.currentAmmo--;
        return createBullet();
    }

    public int getAmmo() {
        return this.currentAmmo;
    }

    public void reload(int newAmmo) {
        this.currentAmmo = (getAmmo() + newAmmo < maxAmmo) ? currentAmmo+newAmmo : this.currentAmmo;
    }

    protected abstract Fireable createBullet();


}
