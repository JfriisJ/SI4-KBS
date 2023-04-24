
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
/**
 * The EntityPart interface defines the process method, which all classes implementing
 * this interface must implement. The process method updates the state of an entity based on
 * the current game data.
 */
public interface EntityPart {
    /**
     * Updates the state of an entity based on the current game data.
     * @param gameData the current game data
     * @param entity the entity to be updated
     */
    void process(GameData gameData, Entity entity);
}
