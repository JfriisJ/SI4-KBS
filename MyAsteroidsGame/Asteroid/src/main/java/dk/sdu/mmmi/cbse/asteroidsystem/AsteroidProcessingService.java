package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.services.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 * AsteroidControlSystem is responsible for updating the position of the asteroids.
 */
public class AsteroidProcessingService implements IEntityProcessingService {

	private IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();

	/**
	 * Updates the position, Life and movement of the asteroids.
	 * @param gameData
	 * @param world
	 */
	@Override
	public void process(GameData gameData, World world) {

		for (Entity asteroid : world.getEntities(Asteroid.class)) {
			PositionPart positionPart = asteroid.getPart(PositionPart.class);
			MovingPart movingPart = asteroid.getPart(MovingPart.class);
			LifePart lifePart = asteroid.getPart(LifePart.class);

			int numPoints = 12;
			float speed = (float) Math.random() * 10f + 20f;
			if (lifePart.getLife() == 1) {
				numPoints = 8;
				speed = (float) Math.random() * 30f + 70f;
			} else if (lifePart.getLife() == 2) {
				numPoints = 10;
				speed = (float) Math.random() * 10f + 50f;
			}

			movingPart.setSpeed(speed);
			movingPart.setUp(true);

			movingPart.process(gameData, asteroid);
			positionPart.process(gameData, asteroid);

			if (lifePart.isIsHit()){
				asteroidSplitter.createSplitAsteroid(asteroid, world);
			}

			updateShape(asteroid, numPoints);
		}
	}
	/**
	 * Dependency Injection using OSGi Declarative Services
	 */
	public void setAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
		this.asteroidSplitter = asteroidSplitter;
	}

	public void removeAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
		this.asteroidSplitter = null;
	}

	/**
	 * Updates the shape of the asteroid.
	 * @param entity
	 */
	private void updateShape(Entity entity, int numPoints) {
		PositionPart position = entity.getPart(PositionPart.class);
		float[] shapex = new float[numPoints];
		float[] shapey = new float[numPoints];

		float radians = position.getRadians();
		float x = position.getX();
		float y = position.getY();
		float angle = 0;
		float radius = entity.getRadius();

		for (int i = 0; i < numPoints; i++) {
			shapex[i] = x + (float) Math.cos(angle + radians) * radius;
			shapey[i] = y + (float) Math.sin(angle + radians) * radius;
			angle += 2 * 3.1415f / numPoints;
		}

		entity.setShapeX(shapex);
		entity.setShapeY(shapey);
	}
}
