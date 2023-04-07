package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;
    EnemyControlSystem enemyControlSystem;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        enemyControlSystem = new EnemyControlSystem();
        enemy = enemyControlSystem.createEnemy(gameData);
        world.addEntity(enemy);

    }

    @Override
    public void stop(GameData gameData, World world) {

        world.removeEntity(enemy);

    }
}
