package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public interface IBulletCreateService {
	/**
	 * Interface for a service that creates bullets.
	 * - Preconditions: The entity exists and has been processed by ProcessingService.
	 * - Postconditions: A bullet has been created and attached to the entity.
	 *
	 * @param entity The entity that the bullet will be attached to.
	 * @param gameData The game data containing information on the game state.
	 * @return An Entity representing the bullet that was created.
	 */
	Entity createBullet(Entity entity, GameData gameData);

}
