package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import static java.lang.Math.sqrt;

public class EnemyControlSystem implements IEntityProcessingService {

    LifePart lifePart;
    int tickCounter = 0;
    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            lifePart = enemy.getPart(LifePart.class);

            tickCounter += 1;

            if (tickCounter > 30) {
                //Random direction of a enemy
                if (Math.random() < 0.8) {
                    movingPart.setLeft(Math.random() < 0.8);
                    tickCounter = 0;
                }
            }else if (tickCounter < 30) {
                if (Math.random() > 0.2) {
                    movingPart.setRight(Math.random() > 0.2);

                }
            }
            movingPart.setUp(true);

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
        }
    }

    public Entity createEnemy(GameData gameData) {
        float deacceleration = 10;
        float acceleration = 50;
        float maxSpeed = 150;
        float rotationSpeed = 5;
        float x = (gameData.getDisplayWidth() / 2) + 200;
        float y = (gameData.getDisplayHeight() / 2) + 200;
        float radians = 3.1415f / 2;

        lifePart = new LifePart(3,1);


        Entity enemyShip = new Enemy();
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(x, y, radians));
        enemyShip.add(lifePart);

        return enemyShip;
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();



        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        //set the radius of the enemy
        entity.setRadius((float) sqrt(8 * 8 + 8 * 8));

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
