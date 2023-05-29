package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;

import java.util.Collection;

/**
 * The Game class is the main class for the game application. It creates a new game world and updates and draws the game world
 * in each iteration of the main loop. The class also holds a list of entity processors and plugins that are used to process and update
 * the entities in the game world.
 */
public class Game implements ApplicationListener {

	private static OrthographicCamera cam;
	private ShapeRenderer sr;
	private GameData gameData = new GameData();
	private World world = new World();

	/**
	 * Called when the game is first created. Initializes the game world, entity processors, and plugins.
	 */
	@Override
	public void create() {

		gameData.setDisplayWidth(Gdx.graphics.getWidth());
		gameData.setDisplayHeight(Gdx.graphics.getHeight());

		cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
		cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
		cam.update();

		sr = new ShapeRenderer();

		Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

		// Lookup all Game Plugins using ServiceLoader
		try {
			for (IGamePluginService iGamePlugin : getPluginServices()) {
				iGamePlugin.start(gameData, world);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	/**
	 * Called in each iteration of the main loop. Updates and draws the game world.
	 */
	@Override
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameData.setDelta(Gdx.graphics.getDeltaTime());

		updateProcess();

		draw();

		gameData.getKeys().update();
		updatePostProcess();

	}

	private void updatePostProcess() {
		try {
			for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
				postEntityProcessorService.process(gameData, world);
			}
	}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Updates the game world by processing all entity processors and post-entity processors.
	 */
	private void updateProcess() {

		try {
			for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
				entityProcessorService.process(gameData, world);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Draws the game world by drawing all entities in the world.
	 */
	private void draw() {

		for (Entity entity : world.getEntities()) {
			sr.setColor(1, 1, 1, 1);

			sr.begin(ShapeRenderer.ShapeType.Line);

			float[] shapex = entity.getShapeX();
			float[] shapey = entity.getShapeY();

			for (int i = 0, j = shapex.length - 1; i < shapex.length; j = i++) {

				sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
			}

			sr.end();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	/**
	 * This method returns a collection of all the game plugin services that have been located using ServiceLoader.
	 *
	 * @return a collection of all the game plugin services
	 */
	private Collection<? extends IGamePluginService> getPluginServices() {
		return SPILocator.locateAll(IGamePluginService.class);
	}

	/**
	 * This method returns a collection of all the entity processing services that have been located using ServiceLoader.
	 *
	 * @return a collection of all the entity processing services
	 */
	private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
		return SPILocator.locateAll(IEntityProcessingService.class);
	}

	/**
	 * This method returns a collection of all the post-entity processing services that have been located using ServiceLoader.
	 *
	 * @return a collection of all the post-entity processing services
	 */
	private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
		return SPILocator.locateAll(IPostEntityProcessingService.class);
	}
}
