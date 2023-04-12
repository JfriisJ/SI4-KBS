package dk.sdu.mmmi.cbse.asteroidsystem;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 * AsteroidPlugin is responsible for starting and stopping the asteroids.
 */
public class AsteroidPlugin implements IGamePluginService {
    private Entity asteroid;
    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;

    // Start Positions
    private float x = -1;
    private float y = -1;

    private int numPoints; // Contains the number of points an asteroid exists of.

    private int type;

    private int life;

    public AsteroidPlugin() {
        this.life = 3;
    }

    /**
     * AsteroidPlugin constructor is used for already created asteroid
     * to define the position and life of the split asteroid
     * @param asteroid
     */
    public AsteroidPlugin(Entity asteroid) {
        PositionPart positionPart = asteroid.getPart(PositionPart.class);
        LifePart lifePart = asteroid.getPart(LifePart.class);
        this.life = lifePart.getLife()-1;

        this.x = positionPart.getX();
        this.y = positionPart.getY();
    }

    /**
     * Start is responsible for adding the asteroid entity into the world
     * @param gameData
     * @param world
     */
    @Override
    public void start(GameData gameData, World world) {
        if (this.x == -1 || this.y == -1) {
            this.x = MathUtils.random(gameData.getDisplayWidth());
            this.y = MathUtils.random(gameData.getDisplayHeight());
        }

        // Add entities to the world
        this.asteroid = createAsteroid(gameData);
        world.addEntity(this.asteroid);
    }

    /**
     * createAsteroid is responsible for creating the asteroid before it can be added to the world
     * @param gameData
     * @return
     */
    private Entity createAsteroid(GameData gameData) {
        float deceleration = 10;
        float acceleration = 100;
        float maxSpeed = 0;
        float rotationSpeed = 0;
        float radians = MathUtils.random(MathUtils.PI2);

        Entity asteroid = new Asteroid();

        if (this.life == 3) {
            //System.out.println("Large Generated");
            asteroid.setRadius(15);
            this.numPoints = 12;
            maxSpeed = MathUtils.random(20,30);
        } else if (this.life == 2) {
            //System.out.println("Medium Generated");
            asteroid.setRadius(10);
            this.numPoints=10;
            maxSpeed = MathUtils.random(50,60);
        } else { // SMALL
            //System.out.println("Small Generated");
            asteroid.setRadius(5);
            this.numPoints=8;
            maxSpeed = MathUtils.random(70,100);
        }


        asteroid.setShapeX(new float[this.numPoints]);
        asteroid.setShapeY(new float[this.numPoints]);

        asteroid.add(new MovingPart(deceleration, acceleration, maxSpeed, rotationSpeed));
        asteroid.add(new PositionPart(this.x, this.y, radians));
        asteroid.add(new LifePart(this.life,0));

        float[] dists = new float[this.numPoints];
        for (int i = 0; i < this.numPoints; i++) {
            dists[i] = MathUtils.random(asteroid.getRadius()/2, asteroid.getRadius());
        }

        asteroid.setDists(dists);

        return asteroid;

    }

    /**
     * Stop is responsible for removing the asteroid entity into the world
     * @param gameData
     * @param world
     */
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(this.asteroid);
    }
}

