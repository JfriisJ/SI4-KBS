package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 * A plugin that adds a bullet entity to the game world.
 */
public class BulletPlugin implements IGamePluginService {

    /**
     * Starts the plugin by adding the bullet entity to the game world.
     *
     * @param gameData The game data object.
     * @param world    The game world object.
     */
    @Override
    public void start(GameData gameData, World world) {

    }

    /**
     * Stops the plugin by removing the bullet entity from the game world.
     *
     * @param gameData The game data object.
     * @param world The game world object.
     */
    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }

}
