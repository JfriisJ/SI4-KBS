package dk.sdu.mmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShootingPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;



public class BulletControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);
            ShootingPart shootingPart = bullet.getPart(ShootingPart.class);

            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);
            lifePart.process(gameData, bullet);
            shootingPart.process(gameData, bullet);

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

        shapex[0] = (float) (x + Math.cos(radians) * 4);
        shapey[0] = (float) (y + Math.sin(radians) * 4);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 4);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1415f / 5) * 4);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 9);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 9);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 4);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 4);

        bullet.setShapeX(shapex);
        bullet.setShapeY(shapey);
    }

}
