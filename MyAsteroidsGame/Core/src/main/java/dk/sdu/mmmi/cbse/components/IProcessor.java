package dk.sdu.mmmi.cbse.components;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IProcessor {

	/**
	 * Process System
	 *
	 * @param gameData data about the game
	 * @param world   the game world
	 */
	void process(GameData gameData, World world);
}