package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author Jon Jakobsen
 */
public interface BulletSPI {
    Entity createBullet(Entity e, GameData gameData);
}
