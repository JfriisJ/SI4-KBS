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
import dk.sdu.mmmi.cbse.components.IGameEntityPluginServiceInjection;
import dk.sdu.mmmi.cbse.components.IProcessor;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;


/**
 * The Game class is the main class for the game application. It creates a new game world and updates and draws the game world
 * in each iteration of the main loop. The class also holds a list of entity processors and plugins that are used to process and update
 * the entities in the game world.
 */
@Component("Game")
public class Game implements ApplicationListener {

	private static OrthographicCamera cam;
	private ShapeRenderer sr;
	private final GameData gameData = new GameData();
	private final World world = new World();
	private final AnnotationConfigApplicationContext components;

	public Game(){
		this.components = new AnnotationConfigApplicationContext();
		this.components.scan("dk.sdu.mmmi.cbse.components");
		this.components.refresh();
	}

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
		((IGameEntityPluginServiceInjection) components.
				getBean("IGameEntityPluginServiceInjection")).startPlugins(gameData, world);
	}

	/**
	 * Called in each iteration of the main loop. Updates and draws the game world.
	 */
	@Override
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameData.setDelta(Gdx.graphics.getDeltaTime());

		update();

		draw();

		gameData.getKeys().update();
		updatePost();
	}

	private void updatePost() {
		//post update
		((IProcessor) components.getBean("IEntityPostProcessingServiceInjection")).process(gameData, world);
	}

	/**
	 * Updates the game world by processing all entity processors and post-entity processors.
	 */
	private void update() {

		// Update
		((IProcessor) components.getBean("IEntityProcessingServiceInjection")).process(gameData, world);

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


}
