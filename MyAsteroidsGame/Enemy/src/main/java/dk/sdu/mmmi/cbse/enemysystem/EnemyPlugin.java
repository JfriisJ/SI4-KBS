package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);
        world.addEntity(enemy);

    }

    private Entity createEnemy(GameData gameData) {

        float deacceleration = 10;
        float acceleration = 50;
        float maxSpeed = 50;
        float rotationSpeed = 5;
        float x = ((float) gameData.getDisplayWidth() / 2) + 200;
        float y = ((float) gameData.getDisplayHeight() / 2) + 200;
        float radians = 3.1415f / 2;

        Entity enemyShip = new Enemy();
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(x, y, radians));

        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {

        world.removeEntity(enemy);

    }
}
