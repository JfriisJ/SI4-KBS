package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IPostEntityProcessingService  {

    /**
     * This interface is responsible for processing entities in the game.
     * - Preconditions: The entity exists and has been processed by ProcessingService.
     * - Postconditions: The entity has been PostProcessed.
     *
     * @param GameData gameData
     * @param World world
     */
    void process(GameData gameData, World world);
}

