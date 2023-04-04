package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {

    /**
     * This interface is responsible for managing all the components of the game.
     *
     * - `start`: Starts the game.
     *     - Preconditions: The game has been initialized.
     *     - Postconditions: The game is running.
     *
     * @param GameData gameData
     * @param World world
     */
    void start(GameData gameData, World world);

    /**
     *- `stop`: Stops the game.
     *     - Preconditions: The game is running.
     *     - Postconditions: The game is stopped.
     *
     * @param GameData gameData
     * @param World world
     */

    void stop(GameData gameData, World world);
}
