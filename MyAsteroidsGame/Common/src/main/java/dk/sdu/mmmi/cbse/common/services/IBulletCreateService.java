package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public interface IBulletCreateService {
    /**
     * Interface for a service that creates bullets.
     *
     * @param entity The entity that the bullet will be attached to.
     * @param gameData The game data containing information on the game state.
     * @return An Entity representing the bullet that was created.
     */
    Entity createBullet(Entity entity, GameData gameData);

}
