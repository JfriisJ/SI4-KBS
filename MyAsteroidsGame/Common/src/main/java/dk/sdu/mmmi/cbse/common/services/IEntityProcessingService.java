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
	 * @param gameData
	 * @param world
	 */

	void process(GameData gameData, World world);
}
