package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * Represents an entity's position in the game world
 */
public class PositionPart implements EntityPart {

    private float x;
    private float y;
    private float radians;

    /**
     * Creates a new PositionPart object with the specified starting position and rotation
     *
     * @param x The starting x position
     * @param y The starting y position
     * @param radians The starting rotation in radians
     */
    public PositionPart(float x, float y, float radians) {
        this.x = x;
        this.y = y;
        this.radians = radians;
    }

    /**
     * Returns the current x position
     *
     * @return The current x position
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the current y position
     *
     * @return The current y position
     */
    public float getY() {
        return y;
    }

    /**
     * Returns the current rotation in radians
     *
     * @return The current rotation in radians
     */
    public float getRadians() {
        return radians;
    }

    /**
     * Sets the x position to the specified value
     *
     * @param newX The new x position
     */
    public void setX(float newX) {
        this.x = newX;
    }

    /**
     * Sets the y position to the specified value
     *
     * @param newY The new y position
     */
    public void setY(float newY) {
        this.y = newY;
    }

    /**
     * Sets both the x and y position to the specified values
     *
     * @param newX The new x position
     * @param newY The new y position
     */
    public void setPosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    /**
     * Sets the rotation to the specified value
     *
     * @param radians The new rotation in radians
     */
    public void setRadians(float radians) {
        this.radians = radians;
    }

    /**
     * Does not perform any processing for this part
     *
     * @param gameData The current game data
     * @param entity The entity to which this part belongs
     */
    @Override
    public void process(GameData gameData, Entity entity) {
    }
}
