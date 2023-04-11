package dk.sdu.mmmi.cbse.bulletsystem;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

    /**
     * The bullet entity created by the plugin.
     */
    Entity bullet;

    /**
     * The shooter entity used to create the bullet.
     */
    Entity shooter;

    public BulletPlugin(Entity shooter) {
        this.shooter = shooter;
    }



    /**
     * Starts the plugin by adding the bullet entity to the game world.
     *
     * @param gameData The game data object.
     * @param world    The game world object.
     */
    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        bullet = createBullet(gameData);
        world.addEntity(bullet);
    }

    /**
     * Creates a new bullet entity based on the shooter entity's position.
     *
     * @param gameData The game data object.
     * @return The new bullet entity.
     */
    public Entity createBullet(GameData gameData) {
        PositionPart shooterPositionPart = this.shooter.getPart(PositionPart.class);
        MovingPart shooterMovingPart = this.shooter.getPart(MovingPart.class);

        float deacceleration = 0;
        float acceleration = 0;
        float maxSpeed = 350;
        float rotationSpeed = 0;
        float radians = shooterPositionPart.getRadians();

        Entity bullet = new Bullet();

        bullet.setRadius(1);

        float bx = MathUtils.cos(radians) * this.shooter.getRadius() * bullet.getRadius();
        float by = MathUtils.sin(radians) * this.shooter.getRadius() * bullet.getRadius();

        float x = bx + shooterPositionPart.getX();
        float y = by + shooterPositionPart.getY();

        float shootSpeed = maxSpeed + shooterMovingPart.getSpeed();

        bullet.setShapeX(new float[4]);
        bullet.setShapeY(new float[4]);
        bullet.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed, shootSpeed));
        bullet.add(new PositionPart(x, y, radians));
        bullet.add(new LifePart(1, 1));


        return bullet;
    }

    /**
     * Stops the plugin by removing the bullet entity from the game world.
     *
     * @param gameData The game data object.
     * @param world The game world object.
     */
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(this.bullet);

    }
}
