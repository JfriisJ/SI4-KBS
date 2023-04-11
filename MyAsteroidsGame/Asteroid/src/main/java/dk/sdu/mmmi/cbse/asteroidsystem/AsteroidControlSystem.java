package dk.sdu.mmmi.cbse.asteroidsystem;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);

            movingPart.setUp(true);
            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            if (lifePart.isIsHit()){
                lifePart.setIsHit(false);
                lifePart.setLife(lifePart.getLife() - 1);
                if (lifePart.getLife() == 2){
                    AsteroidPlugin asteroidPlugin = new AsteroidPlugin();
                    asteroidPlugin.setType(AsteroidType.valueOf("MEDIUM"));
                }
                if (lifePart.getLife() <= 0){
                    world.removeEntity(asteroid);
                }

            }

            updateShape(asteroid);

        }

    }
    private void updateShape(Entity asteroid) {
        float[] shapex = asteroid.getShapeX();
        float[] shapey = asteroid.getShapeY();
        PositionPart positionPart = asteroid.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        float angle = 0;
        float radius = asteroid.getRadius();

        float[] dists = asteroid.getDists();

        for (int i = 0; i < dists.length; i++) {
            shapex[i] = x + MathUtils.cos(angle + radians) * dists[i];
            shapey[i] = y + MathUtils.sin(angle + radians) * dists[i];
            angle += MathUtils.PI2 / shapex.length;
        }

        asteroid.setShapeX(shapex);
        asteroid.setShapeY(shapey);

    }
}
