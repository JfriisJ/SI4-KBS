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
    private AsteroidType type = LARGE;
    private int numPoints;

    public AsteroidPlugin() {

    }
    public void setType(AsteroidType type) {
        this.type = type;
    }

    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroid);

    }

    Entity createAsteroid(GameData gameData) {

        float deacceleration = 0;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 0.5f;
        float x = MathUtils.random(gameData.getDisplayWidth());
        float y = MathUtils.random( gameData.getDisplayHeight());
        float radians = MathUtils.random(2 * 3.1415f);

        if (type == null) {
            type = LARGE;
        }

        Entity asteroid = new Asteroid(type);

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

}
