package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * A timer part to be attached to an entity. Contains an expiration time that
 * counts down over time, and can be reduced by a specified delta. Once the
 * expiration time reaches zero, the attached entity's `LifePart` will be
 * set to zero.
 */
public class TimerPart implements EntityPart {

    /** The expiration time of the timer */
    private float expiration;

    /**
     * Create a new `TimerPart` with the specified expiration time.
     *
     * @param expiration The expiration time of the timer
     */
    public TimerPart(float expiration) {
        this.expiration = expiration;
    }

    /**
     * Get the current expiration time of the timer.
     *
     * @return The expiration time of the timer
     */
    public float getExpiration() {
        return expiration;
    }

    /**
     * Set the expiration time of the timer.
     *
     * @param expiration The new expiration time of the timer
     */
    public void setExpiration(float expiration) {
        this.expiration = expiration;
    }

    /**
     * Reduce the expiration time of the timer by the specified delta.
     *
     * @param delta The amount to reduce the expiration time by
     */
    public void reduceExpiration(float delta) {
        this.expiration -= delta;
    }

    /**
     * Process the timer part. If the expiration time is greater than zero,
     * reduce it by the delta time. If the expiration time is less than or
     * equal to zero, set the attached entity's `LifePart` to zero.
     *
     * @param gameData The current game data
     * @param entity The entity that this part is attached to
     */
    @Override
    public void process(GameData gameData, Entity entity) {
        if (expiration > 0) {
            reduceExpiration(gameData.getDelta());
        }

        if (expiration <= 0) {
            LifePart lifePart = entity.getPart(LifePart.class);
            lifePart.setLife(0);
        }
    }
}
