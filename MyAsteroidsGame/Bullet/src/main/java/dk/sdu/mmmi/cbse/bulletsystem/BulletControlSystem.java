package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IBulletCreateService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
/**
 * The BulletControlSystem class controls the behavior of bullets in the game.
 * It implements IEntityProcessingService and IBulletCreateService interfaces.
 * The process() method updates the position and movement of all bullets in the game.
 * The createBullet() method creates a new bullet entity when called.
 */
public class BulletControlSystem implements IEntityProcessingService, IBulletCreateService {

	/**
	 * Constructs a BulletControlSystem.
	 */
	public BulletControlSystem() {

	}

	@Override
	public void process(GameData gameData, World world) {

		for (Entity bullet : world.getEntities(Bullet.class)) {
			PositionPart positionPart = bullet.getPart(PositionPart.class);
			MovingPart movingPart = bullet.getPart(MovingPart.class);
			TimerPart timerPart = bullet.getPart(TimerPart.class);

			movingPart.setUp(true);

			if (timerPart.getExpiration() < 0) {
				world.removeEntity(bullet);
			}

			timerPart.process(gameData, bullet);
			movingPart.process(gameData, bullet);
			positionPart.process(gameData, bullet);

			updateShape(bullet);
		}

	}

	/**
	 * Updates the shape of a bullet entity based on its position and movement.
	 * @param bullet The bullet entity.
	 */
	private void updateShape(Entity bullet) {
		float[] shapex = bullet.getShapeX();
		float[] shapey = bullet.getShapeY();
		PositionPart positionPart = bullet.getPart(PositionPart.class);
		float x = positionPart.getX();
		float y = positionPart.getY();
		float radians = positionPart.getRadians();

		shapex[0] = x;
		shapey[0] = y;

		shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5));
		shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5));

		bullet.setShapeX(shapex);
		bullet.setShapeY(shapey);

	}

	@Override
	public Entity createBullet(Entity shooter, GameData gameData) {
		PositionPart positionPart = shooter.getPart(PositionPart.class);

		float x = positionPart.getX();
		float y = positionPart.getY();

		float radians = positionPart.getRadians();
		float speed = 350;

		Entity bullet = new Bullet();
		bullet.setRadius(2);

		float bx = (float) Math.cos(radians) * shooter.getRadius() * bullet.getRadius();
		float by = (float) Math.sin(radians) * shooter.getRadius() * bullet.getRadius();

		bullet.add(new PositionPart(bx + x, by + y, radians));
		bullet.add(new LifePart(1));
		bullet.add(new MovingPart(0, 50000, speed, 5));
		bullet.add(new TimerPart(1));

		bullet.setShapeX(new float[2]);
		bullet.setShapeY(new float[2]);

		return bullet;
	}
}
