package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetectionSystem implements IPostEntityProcessingService {

//    PositionPart positionPart1, positionPart2;
//    LifePart lifePart1, lifePart2;

    @Override
    public void process(GameData gameData, World world) {

        // two for loops for all entities in the world
        for (Entity entity : world.getEntities()) {
            for (Entity collisionDetection : world.getEntities()) {
                // get life parts on all entities
                LifePart entityLife = entity.getPart(LifePart.class);

                // if the two entities are identical, skip the iteration
                if (entity.getID().equals(collisionDetection.getID())) {
                    continue;

                    // remove entities with zero in expiration
                }

                // CollisionDetection
                if (this.collides(entity, collisionDetection)) {
                    // if entity has been hit, and should have its life reduced
                    if (entityLife.getLife() > 0) {
                        entityLife.setLife(entityLife.getLife() - 1);
                        entityLife.setIsHit(true);
                        // if entity is out of life - remove
                        if (entityLife.getLife() <= 0) {
                            world.removeEntity(entity);
                        }
                    }
                }
            }
        }
    }

    public Boolean collides(Entity entity, Entity entity2) {
        PositionPart entMov = entity.getPart(PositionPart.class);
        PositionPart entMov2 = entity2.getPart(PositionPart.class);
        float dx = (float) entMov.getX() - (float) entMov2.getX();
        float dy = (float) entMov.getY() - (float) entMov2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance < (entity.getRadius() + entity2.getRadius())) {
            return true;
        }
        return false;
    }
//        // Check for collisions between all pairs of entities
//        for (Entity entity1 : world.getEntities()) {
//            for (Entity entity2 : world.getEntities()) {
//                if (entity1 != entity2) {
//
//                    positionPart1 = entity1.getPart(PositionPart.class);
//                    positionPart2 = entity2.getPart(PositionPart.class);
//
//                    lifePart1 = entity1.getPart(LifePart.class);
//                    lifePart2 = entity2.getPart(LifePart.class);
//
//                    // Calculate the distance between the two entities
//                    float dx = positionPart1.getX() - positionPart2.getX();
//                    float dy = positionPart1.getY() - positionPart2.getY();
//                    float distance = (float) Math.sqrt(dx * dx + dy * dy);
////                    System.out.println("Distance: " + distance);
//
//
//                    // Check if the entities collide
//                    if (distance < entity1.getRadius() + entity2.getRadius()) {
//                        // Apply the appropriate logic based on the entities' types
//                        if (entity1.isType(Player.class) && entity2.isType(Asteroid.class)) {
//                            // Player collided with asteroid
//                            lifePart1.setIsHit(true);
//                        } else if (entity1.isType(Player.class) && entity2.isType(Enemy.class)) {
//                            lifePart1.setIsHit(true);
//                            lifePart2.setIsHit(true);
//                            // Player collided with enemy
//                        } else if (entity1.isType(Asteroid.class) && entity2.isType(Enemy.class)) {
//                            // Asteroid collided with enemy
//                            lifePart2.setIsHit(true);
//                        } else if (entity1.isType(Bullet.class) && entity2.isType(Asteroid.class)) {
//                            lifePart1.setIsHit(true);
//                            lifePart2.setIsHit(true);
//                            // Bullet collided with asteroid
//                        } else if (entity1.isType(Bullet.class) && entity2.isType(Enemy.class)) {
//                            lifePart1.setIsHit(true);
//                            lifePart2.setIsHit(true);
//                            // Bullet collided with enemy
//
//                        } else if (entity1.isType(Bullet.class) && entity2.isType(Player.class)) {
//                            lifePart1.setIsHit(true);
//                            lifePart2.setIsHit(true);
//                            // Bullet collided with enemy
//
//                        }
//                    }
//                }
//            }
//        }
//    }

}
