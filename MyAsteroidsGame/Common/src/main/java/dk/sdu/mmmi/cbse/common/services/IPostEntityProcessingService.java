package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IPostEntityProcessingService  {

	/**
	 * This interface is responsible for postprocessing entities in the game.
	 * - Preconditions: The entity exists and has been processed by ProcessingService.
	 * - Postconditions: The entity has been PostProcessed.
	 *
	 * @param gameData The game data containing information on the game state.
	 * @param world The game world containing all entities.
	 */
	void process(GameData gameData, World world);
}

