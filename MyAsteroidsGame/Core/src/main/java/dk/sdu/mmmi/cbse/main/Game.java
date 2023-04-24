package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidPlugin;
import dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShootingPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;
import dk.sdu.mmmi.cbse.playersystem.Player;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
import dk.sdu.mmmi.cbse.enemysystem.Enemy;
import dk.sdu.mmmi.cbse.enemysystem.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The Game class is the main class for the game application. It creates a new game world and updates and draws the game world
 * in each iteration of the main loop. The class also holds a list of entity processors and plugins that are used to process and update
 * the entities in the game world.
 */
public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;
    private GameData gameData = new GameData();
    private List<IEntityProcessingService> entityProcessors = new ArrayList<>();
    private List<IGamePluginService> entityPlugins = new ArrayList<>();
    private List<IPostEntityProcessingService> postEntityProcessors = new ArrayList<>();
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
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }


//        // Create and add the player to the world
//        addPlayer();
//
//        // Create and add the enemy to the world
//        addEnemies(1);
//
//        // Create and add the asteroids to the world
//        addAsteroids(4);
//
//        // Create and add the bullets to the world
//        IEntityProcessingService bulletProcess = new BulletControlSystem();
//        entityProcessors.add(bulletProcess);
//
//        // Create and add the collision detection system to the world
//        IPostEntityProcessingService collisionPostProcessor = new CollisionDetectionSystem();
//        postEntityProcessors.add(collisionPostProcessor);
//
//        // Start all the plugins
//        for (IGamePluginService iGamePlugin : entityPlugins) {
//            iGamePlugin.start(gameData, this.world);
//        }
    }

    /**
     * Add the player to the game world.
     */
    private void addPlayer() {
        IGamePluginService playerPlugin = new PlayerPlugin();
        IEntityProcessingService playerProcess = new PlayerControlSystem();
        entityPlugins.add(playerPlugin);
        entityProcessors.add(playerProcess);
    }

    /**
     * Adds the asteroids to the game world.
     */
    public void addAsteroids(int amount) {
        for (int i = 0; i < amount; i++) {
            IGamePluginService asteroidPlugin = new AsteroidPlugin();
            IEntityProcessingService asteroidProcess = new AsteroidControlSystem();
            entityPlugins.add(asteroidPlugin);
            entityProcessors.add(asteroidProcess);
        }
    }

    /**
     * Adds the enemies to the game world.
     */
    public void addEnemies(int amount) {
        for (int i = 0; i < amount; i++) {
            IGamePluginService enemyPlugin = new EnemyPlugin();
            IEntityProcessingService enemyProcess = new EnemyControlSystem();
            entityPlugins.add(enemyPlugin);
            entityProcessors.add(enemyProcess);
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

        update();

        draw();

        gameData.getKeys().update();
    }

    /**
     * Updates the game world by processing all entity processors and post-entity processors.
     */
    private void update() {

        for (Entity entity : world.getEntities()) {
            try {
                ShootingPart shootingPart = entity.getPart(ShootingPart.class);
                if (shootingPart.getShooting()) {
                    IGamePluginService bulletPlugin = new BulletPlugin(entity);
                    this.entityPlugins.add(bulletPlugin);
                    bulletPlugin.start(gameData, world);
                }
            }
            catch (NullPointerException error) {
            }
        }


        // Update
        for (IEntityProcessingService entityProcessorService : entityProcessors) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessors) {
            postEntityProcessorService.process(gameData, world);

        }
    }

    /**
     * Draws the game world by drawing all entities in the world.
     */
    private void draw() {

        for (Entity entity : world.getEntities()) {

            if (entity.isType(Player.class)) {
                sr.setColor(1, 0, 0, 1);
            } else if (entity.isType(Enemy.class)) {
                sr.setColor(0, 1, 0, 1);
            } else {
                sr.setColor(1, 1, 1, 1);
            }

//            sr.setColor(1, 1, 1, 1);

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

    private Collection<? extends IGamePluginService> getPluginServices() {
        return SPILocator.locateAll(IGamePluginService.class);
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return SPILocator.locateAll(IEntityProcessingService.class);
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return SPILocator.locateAll(IPostEntityProcessingService.class);
    }
}
