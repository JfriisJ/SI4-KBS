package dk.sdu.mmmi.cbse.bulletsystem;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);

            movingPart.setUp(true);
            if (timerPart.getExpiration() < 0) {
                world.removeEntity(bullet);
            }

            timerPart.process(gameData, bullet);
            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);

//            LifePart lifePart = bullet.getPart(LifePart.class);

//            lifePart.reduceExpiration(gameData.getDelta());
//            lifePart.process(gameData, bullet);



//            if (lifePart.getExpiration() < 0 || lifePart.isIsHit()){
//                world.removeEntity(bullet);
//            }
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

        shapex[0] = x;
        shapey[0] = y;

        shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5);
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1415f / 5);

//        shapex[0] = x + 2 * MathUtils.cos(radians + 3.1415f / 2);
//        shapey[0] = y + 2 * MathUtils.sin(radians + 3.1415f / 2);
//
//        shapex[1] = x + 2 * MathUtils.cos(radians + 3.1415f + 3.1415f / 4);
//        shapey[1] = y + 2 * MathUtils.sin(radians + 3.1415f + 3.1415f / 4);
//
//        shapex[2] = x + 2 * MathUtils.cos(radians + 3.1415f);
//        shapey[2] = y + 2 * MathUtils.sin(radians + 3.1415f);
//
//        shapex[3] = x + 2 * MathUtils.cos(radians + 3.1415f - 3.1415f / 4);
//        shapey[3] = y + 2 * MathUtils.sin(radians + 3.1415f - 3.1415f / 4);

        bullet.setShapeX(shapex);
        bullet.setShapeY(shapey);

    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {

        PositionPart positionPart = shooter.getPart(PositionPart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();

        float radians = positionPart.getRadians();
        float dt = gameData.getDelta();
        float speed = 350;

        Entity bullet = new Bullet();
        bullet.setRadius(2);

        float bx = MathUtils.cos(radians) * shooter.getRadius() * bullet.getRadius();
        float by = MathUtils.sin(radians) * shooter.getRadius() * bullet.getRadius();

        bullet.add(new PositionPart(bx + x, by + y, radians));
        bullet.add(new LifePart(1));
        bullet.add(new MovingPart(0, 50000, speed, 5));
        bullet.add(new TimerPart(1));

        bullet.setShapeX(new float[2]);
        bullet.setShapeY(new float[2]);

        return bullet;
    }
}
