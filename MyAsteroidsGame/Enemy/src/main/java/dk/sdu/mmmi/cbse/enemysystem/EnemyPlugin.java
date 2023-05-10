package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShootingPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 * EnemyPlugin is a plugin that adds an enemy to the game.
 */
public class EnemyPlugin implements IGamePluginService {

	private Entity enemy;

	@Override
	public void start(GameData gameData, World world) {

		enemy = createEnemy(gameData);
		world.addEntity(enemy);

	}

	/**
	 * creates is responsible for creating the enemy before it can be added to the world
	 * @param gameData the data of the game.
	 * @return the enemy entity.
	 */
	public Entity createEnemy(GameData gameData) {
		float deacceleration = 10;
		float acceleration = 50;
		float maxSpeed = 50;
		float rotationSpeed = 1;
		float x = (gameData.getDisplayWidth() / 2) + 200;
		float y = (gameData.getDisplayHeight() / 2) + 200;
		float radians = 3.1415f / 2;

		Entity enemyShip = new Enemy();
		enemyShip.setRadius(8);
		enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
		enemyShip.add(new PositionPart(x, y, radians));
		enemyShip.add(new LifePart(1));
		enemyShip.add(new ShootingPart(0.2f));

		return enemyShip;
	}

	@Override
	public void stop(GameData gameData, World world) {

		world.removeEntity(enemy);

	}
}
