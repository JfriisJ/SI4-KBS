package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import static dk.sdu.mmmi.cbse.asteroidsystem.AsteroidType.LARGE;

public class AsteroidPlugin implements IGamePluginService {
    Entity asteroid;
    AsteroidControlSystem asteroidControlSystem;

    @Override
    public void start(GameData gameData, World world) {
        asteroidControlSystem = new AsteroidControlSystem();
        asteroid = asteroidControlSystem.createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

}
