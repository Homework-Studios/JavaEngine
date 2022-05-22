package net.rebix.engine.util.enums;

public enum SkyDirection {
    NORTH(180f),
    EAST(-90f),
    SOUTH(0),
    WEST(90),
    NONE(180);

    public float yaw;

    public float getYaw() {
        return this.yaw;
    }

    SkyDirection(float yaw) {
        this.yaw = yaw;
    }
}
