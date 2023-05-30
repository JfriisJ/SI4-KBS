package dk.sdu.mmmi.cbse.components;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IProcessor {

	/**
	 * Process System
	 *
	 * @param gameData
	 * @param world
	 */
	void process(GameData gameData, World world);
}