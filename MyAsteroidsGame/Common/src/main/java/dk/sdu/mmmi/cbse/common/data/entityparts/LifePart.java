package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * Entity part that handles the life of an entity in the game.
 * The life can be decreased by setting isHit to true.
 * If life reaches zero, the entity is considered dead.
 */
public class LifePart implements EntityPart {

    private int life;
    private boolean isHit = false;

    private boolean dead = false;

    public LifePart(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isDead() {
        return dead;
    }

    /**
     * Process the current state of the entity.
     * Decreases life if isHit is set to true, and sets dead to true if life is zero or below.
     */
    @Override
    public void process(GameData gameData, Entity entity) {
        if (isHit) {
            life = - 1;
            isHit = false;
        }
        if (life <= 0) {
            dead = true;
        }
    }
}
