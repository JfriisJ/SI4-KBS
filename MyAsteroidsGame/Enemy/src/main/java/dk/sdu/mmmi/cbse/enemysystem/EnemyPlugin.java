package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 * EnemyPlugin is a plugin that adds an enemy to the game.
 */
public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;
    LifePart lifePart;

    public EnemyPlugin() {
    }

    /**
     * Adds an enemy to the game.
     *
     * @param gameData
     * @param world
     */
    @Override
    public void start(GameData gameData, World world) {

        enemy = createEnemy(gameData);
        world.addEntity(enemy);

    }

    /**
     * creates is responsible for creating the enemy before it can be added to the world
     * @param gameData
     * @return
     */
    public Entity createEnemy(GameData gameData) {
        float deacceleration = 10;
        float acceleration = 50;
        float maxSpeed = 50;
        float rotationSpeed = 1;
        float x = (gameData.getDisplayWidth() / 2) + 200;
        float y = (gameData.getDisplayHeight() / 2) + 200;
        float radians = 3.1415f / 2;

        lifePart = new LifePart(3,1);


        Entity enemyShip = new Enemy();
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(x, y, radians));
        enemyShip.add(lifePart);

        return enemyShip;
    }

    /**
     * Removes the enemy from the game.
     *
     * @param gameData
     * @param world
     */
    @Override
    public void stop(GameData gameData, World world) {

        world.removeEntity(enemy);

    }
}
