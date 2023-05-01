package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {
	/**
	 * This interface is responsible for processing entities in the game.
	 *
	 * - Pre-conditions: The entity exists.
	 * - Post-conditions: The entity has been processed.
	 *
	 * @param gameData The game data containing information on the game state.
	 * @param world The game world containing all entities.
	 */

	void process(GameData gameData, World world);
}
