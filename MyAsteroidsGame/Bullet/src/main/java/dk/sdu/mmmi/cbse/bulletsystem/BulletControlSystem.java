package dk.sdu.mmmi.cbse.bulletsystem;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);

            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);
            lifePart.reduceExpiration(gameData.getDelta());
            lifePart.process(gameData, bullet);

            movingPart.setUp(true);

            if (lifePart.getExpiration() < 0 || lifePart.isIsHit()){
                world.removeEntity(bullet);
            }
            updateShape(bullet);
        }

    }

    private void updateShape(Entity bullet) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];

        PositionPart positionPart = bullet.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = x + 2 * MathUtils.cos(radians + 3.1415f / 2);
        shapey[0] = y + 2 * MathUtils.sin(radians + 3.1415f / 2);

        shapex[1] = x + 2 * MathUtils.cos(radians + 3.1415f + 3.1415f / 4);
        shapey[1] = y + 2 * MathUtils.sin(radians + 3.1415f + 3.1415f / 4);

        shapex[2] = x + 2 * MathUtils.cos(radians + 3.1415f);
        shapey[2] = y + 2 * MathUtils.sin(radians + 3.1415f);

        shapex[3] = x + 2 * MathUtils.cos(radians + 3.1415f - 3.1415f / 4);
        shapey[3] = y + 2 * MathUtils.sin(radians + 3.1415f - 3.1415f / 4);

        bullet.setShapeX(shapex);
        bullet.setShapeY(shapey);

    }

}
