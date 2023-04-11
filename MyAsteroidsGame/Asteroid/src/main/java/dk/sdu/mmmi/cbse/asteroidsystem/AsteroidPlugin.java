package dk.sdu.mmmi.cbse.asteroidsystem;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import static dk.sdu.mmmi.cbse.asteroidsystem.AsteroidType.LARGE;
import static dk.sdu.mmmi.cbse.asteroidsystem.AsteroidType.MEDIUM;
import static dk.sdu.mmmi.cbse.asteroidsystem.AsteroidType.SMALL;

public class AsteroidPlugin implements IGamePluginService {
    private Entity asteroid;
    private int type = 2;
    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;
    private int numPoints;

    public AsteroidPlugin() {

    }
    public void setType(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData, this.type);
        world.addEntity(asteroid);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroid);

    }

    public Entity createAsteroid(GameData gameData, int type) {

        System.out.println(this.type);
        this.type = type;

        float deacceleration = 0;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 0.5f;
        float x = MathUtils.random(gameData.getDisplayWidth());
        float y = MathUtils.random( gameData.getDisplayHeight());
        float radians = MathUtils.random(2 * 3.1415f);

        Entity asteroid = new Asteroid();

        if (type == LARGE) {
            numPoints = 12;
            asteroid.setRadius(15);
            maxSpeed= MathUtils.random(10, 30);
        } else if (type == MEDIUM) {
            numPoints = 10;
            asteroid.setRadius(10);
            maxSpeed= MathUtils.random(25, 35);
        } else if (type == SMALL) {
            numPoints = 8;
            asteroid.setRadius(5);
            maxSpeed= MathUtils.random(10, 11);
        }

        asteroid.setShapeX(new float[numPoints]);
        asteroid.setShapeY(new float[numPoints]);

        asteroid.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroid.add(new PositionPart(x, y, radians));
        asteroid.add(new LifePart(1,0));

        float[] dists = new float[numPoints];
        for (int i = 0; i < numPoints; i++) {
            dists[i] = MathUtils.random(asteroid.getRadius() / 2, asteroid.getRadius());
        }
        asteroid.setDists(dists);

        return asteroid;
    }

    public void asteroidsSplitter(int type){
        this.type = type;
    }

}
