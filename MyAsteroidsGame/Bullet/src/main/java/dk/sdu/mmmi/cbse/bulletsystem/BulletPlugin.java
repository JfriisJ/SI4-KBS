package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

    Bullet bullet;
    @Override
    public void start(GameData gameData, World world) {
        this.bullet = new Bullet();
        bullet.add(new MovingPart(10, 200, 300, 5));
        world.addEntity(new Bullet());


    }

    public Bullet createBullet(GameData gameData, Entity shooter) {
        PositionPart positionPart = shooter.getPart(PositionPart.class);
        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = 3.1415f / 2;

        bullet = new Bullet();
        bullet.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        bullet.add(new PositionPart(x, y, radians));

        return bullet;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(this.bullet);

    }
}
