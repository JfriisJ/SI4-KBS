package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShootingPart;
import dk.sdu.mmmi.cbse.common.services.IBulletCreateService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;

import static java.lang.Math.sqrt;

/**
 * EnemyControlSystem is a system that controls the enemy.
 */
public class EnemyControlSystem implements IEntityProcessingService {

	int tickCounter = 0;
	/**
	 * Updates the position, Life and movement of the enemy.
	 *
	 * @param gameData the game data
	 * @param world the game world
	 */
	@Override
	public void process(GameData gameData, World world) {

		for (Entity enemy : world.getEntities(Enemy.class)) {
			PositionPart positionPart = enemy.getPart(PositionPart.class);
			MovingPart movingPart = enemy.getPart(MovingPart.class);
			ShootingPart shootingPart = enemy.getPart(ShootingPart.class);
			LifePart lifePart = enemy.getPart(LifePart.class);


			tickCounter += 1;

			if (tickCounter > 30) {
				//Random direction of a enemy
				if (Math.random() > 0.8) {
					movingPart.setLeft(Math.random() < 0.8);
					tickCounter = 0;
				}
			}
			if (tickCounter < 30) {
				if (Math.random() < 0.2) {
					movingPart.setRight(Math.random() > 0.2);

				}
			}
			movingPart.setUp(true);

			shootingPart.setShooting((float)Math.random() > 0.99f);

			if (shootingPart.getShooting()){
				for (IBulletCreateService bullet : SPILocator.locateAll(IBulletCreateService.class)) {
					world.addEntity(bullet.createBullet(enemy, gameData));

				}
			}

			shootingPart.process(gameData, enemy);
			movingPart.process(gameData, enemy);
			positionPart.process(gameData, enemy);
			lifePart.process(gameData, enemy);


			updateShape(enemy);
		}
	}

	/**
	 * Updates the shape of the entity.
	 *
	 * @param entity The entity to update the shape of.
	 */
	private void updateShape(Entity entity) {
		float[] shapex = entity.getShapeX();
		float[] shapey = entity.getShapeY();
		PositionPart positionPart = entity.getPart(PositionPart.class);
		float x = positionPart.getX();
		float y = positionPart.getY();
		float radians = positionPart.getRadians();



		shapex[0] = (float) (x + Math.cos(radians) * 16);
		shapey[0] = (float) (y + Math.sin(radians) * 16);

		shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 16);
		shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 16);

		shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 16);
		shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 16);

		shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 16);
		shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 16);

		entity.setRadius((float) sqrt(16 * 16+ 16 * 16));
		entity.setShapeX(shapex);
		entity.setShapeY(shapey);
	}
}
