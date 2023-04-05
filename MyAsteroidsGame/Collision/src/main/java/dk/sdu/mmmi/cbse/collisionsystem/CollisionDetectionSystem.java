package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.asteroidsystem.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.enemysystem.Enemy;
import dk.sdu.mmmi.cbse.playersystem.Player;

public class CollisionDetectionSystem implements IPostEntityProcessingService {
        @Override
    public void process(GameData gameData, World world) {

        // Check for collisions between all pairs of entities
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (entity1 != entity2) {
                    PositionPart positionPart1 = entity1.getPart(PositionPart.class);
                    PositionPart positionPart2 = entity2.getPart(PositionPart.class);

                    // Calculate the distance between the two entities
                    float dx = positionPart1.getX() - positionPart2.getX();
                    float dy = positionPart1.getY() - positionPart2.getY();
                    float distance = (float) Math.sqrt(dx * dx + dy * dy);
//                    System.out.println("Distance: " + distance);


                    // Check if the entities collide
                    if (distance < entity1.getRadius() + entity2.getRadius()) {
                        System.out.println("Collision detected between " + entity1.getID() + " and " + entity2.getID());
                        System.out.println("Distance: " + distance);
                        System.out.println("Radius1: " + entity1.getRadius() + " Radius2: " + entity2.getRadius());
                        // Apply the appropriate logic based on the entities' types
                        if (entity1.isType(Player.class) && entity2.isType(Asteroid.class)) {
                            // Player collided with asteroid
                            System.out.println("Player collided with asteroid!");
                        } else if (entity1.isType(Player.class) && entity2.isType(Enemy.class)) {
                            // Player collided with enemy
                            System.out.println("Player collided with enemy!");
                        } else if (entity1.isType(Asteroid.class) && entity2.isType(Enemy.class)) {
                            // Asteroid collided with enemy
                            System.out.println("Asteroid collided with enemy!");
                        }
                    }
                }
            }
        }
    }

}
