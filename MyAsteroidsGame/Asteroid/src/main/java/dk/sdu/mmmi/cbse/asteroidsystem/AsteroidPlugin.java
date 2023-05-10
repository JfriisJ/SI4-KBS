package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

/**
 * AsteroidPlugin is responsible for starting and stopping the asteroids.
 */
public class AsteroidPlugin implements IGamePluginService {
	int numberOfAsteroids = 10;

	@Override
	public void start(GameData gameData, World world) {
		// Add entities to the world
		for (int i = 0; i < numberOfAsteroids; i++) {
			Entity asteroid = createAsteroid(gameData);
			world.addEntity(asteroid);
		}

	}

	@Override
	public void stop(GameData gameData, World world) {
		// Remove entities
		for (Entity asteroid : world.getEntities(Asteroid.class)) {
			world.removeEntity(asteroid);
		}
	}

	/**
	 * Creates a new asteroid entity with a random speed and direction.
	 * @param gameData The current game data.
	 * @return The new asteroid entity.
	 */
	private Entity createAsteroid(GameData gameData) {
		Random rand = new Random();
		Entity asteroid = new Asteroid();
		float radians = (float) Math.random() * 2 * 3.1415f;
		float speed = (float) Math.random() * 10f + 20f;

		asteroid.setRadius(20);
		asteroid.add(new MovingPart(0, speed, speed, 0));
		asteroid.add(new PositionPart(rand.nextFloat(gameData.getDisplayWidth()), rand.nextFloat(gameData.getDisplayHeight()), radians));
		asteroid.add(new LifePart(3));

		return asteroid;
	}

}

