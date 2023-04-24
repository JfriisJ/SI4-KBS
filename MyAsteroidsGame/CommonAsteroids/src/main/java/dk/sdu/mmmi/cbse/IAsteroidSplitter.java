package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * @author Jon Jakobsen
 */
public interface IAsteroidSplitter {
    void createSplitAsteroid(Entity e, World w);
}