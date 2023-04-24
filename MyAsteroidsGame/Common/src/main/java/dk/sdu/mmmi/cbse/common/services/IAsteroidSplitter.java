package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface for an asteroid splitter service, which is responsible for creating
 * new asteroids when an asteroid is destroyed.
 */
public interface IAsteroidSplitter {
    /**
     * Creates new asteroids when an asteroid is destroyed.
     * @param e The entity representing the destroyed asteroid.
     * @param w The world in which the new asteroids will be created.
     */
    void createSplitAsteroid(Entity e, World w);
}