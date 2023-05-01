package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShootingPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 * The PlayerPlugin is a plugin that adds a player to the game.
 */
public class PlayerPlugin implements IGamePluginService {

	private Entity player;

	/**
	 * Adds a player to the game.
	 *
	 * @param gameData The game data containing information on the game state.
	 * @param world The game world containing all entities.
	 */
	@Override
	public void start(GameData gameData, World world) {

		// Add entities to the world
		player = createPlayerShip(gameData);
		world.addEntity(player);
	}

	/**
	 * Creates a player before adding it to the world.
	 *
	 * @param gameData The game data containing information on the game state.
	 * @return The player entity.
	 */
	private Entity createPlayerShip(GameData gameData) {

		float deacceleration = 10;
		float acceleration = 200;
		float maxSpeed = 300;
		float rotationSpeed = 5;
		float x = gameData.getDisplayWidth() / 2;
		float y = gameData.getDisplayHeight() / 2;
		float radians = 3.1415f / 2;

		Entity playerShip = new Player();
		playerShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
		playerShip.add(new PositionPart(x, y, radians));
		playerShip.add(new LifePart(1));
		playerShip.add(new ShootingPart(0.2f));

		return playerShip;
	}

	/**
	 * Removes the player from the game.
	 *
	 * @param gameData The game data containing information on the game state.
	 * @param world The game world containing all entities.
	 */
	@Override
	public void stop(GameData gameData, World world) {
		// Remove entities
		world.removeEntity(player);
	}

}
