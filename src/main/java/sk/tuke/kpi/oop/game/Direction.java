package sk.tuke.kpi.oop.game;

public enum Direction {

    NORTHEAST(1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NORTHWEST(-1, 1),

    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),
    NONE(0, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction fromAngle(float angle) {
        switch ((int) angle) {
            case 0:
                return Direction.NORTH;
            case 90:
                return Direction.WEST;
            case 180:
                return Direction.SOUTH;
            case 270:
                return Direction.EAST;
            case 45:
                return Direction.NORTHWEST;
            case 135:
                return Direction.SOUTHWEST;
            case 225:
                return Direction.SOUTHEAST;
            case 315:
                return Direction.NORTHEAST;
            default:
                return Direction.NONE;
        }
    }



    public Direction combine(Direction direction){

        Direction newDirection = NONE;

        for (Direction value : Direction.values())
                newDirection= (value.getDx() == ((getDx()==direction.getDx()) ? this.getDx() :this.getDx()+direction.getDx()) && value.getDy() == ((getDy()==direction.getDy()) ? this.getDy() :this.getDy()+direction.getDy())) ? value : newDirection;

        return newDirection;

    }

    public float getAngle() {

        float res = (float) Math.toDegrees(Math.atan2(this.dx, this.dy)) > 0 ? (float) Math.toDegrees(Math.atan2(this.dx, this.dy)) : 360 + (float) Math.toDegrees(Math.atan2(this.dx, this.dy));

        if (res == 90) return 270;
        if (res == 270) return 90;
        if (res == 360) return 0;
        if( res == 315) return 45;
        if( res == 45) return 315;
        if( res == 135) return 225;
        if( res == 225) return 135;


        return res;


    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

}


