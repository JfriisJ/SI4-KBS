package dk.sdu.mmmi.cbse.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {

	private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();

	/**
	 * Add an entity to the world.
	 * @param entity The entity to add to the world.
	 * @return The ID of the added entity.
	 */
	public String addEntity(Entity entity) {
		entityMap.put(entity.getID(), entity);
		return entity.getID();
	}

	/**
	 * Remove an entity from the world by ID.
	 * @param entityID The ID of the entity to remove.
	 */
	public void removeEntity(String entityID) {
		entityMap.remove(entityID);
	}

	/**
	 * Remove an entity from the world.
	 * @param entity The entity to remove.
	 */
	public void removeEntity(Entity entity) {
		entityMap.remove(entity.getID());
	}

	/**
	 * Get all entities in the world.
	 * @return A collection of all entities in the world.
	 */
	public Collection<Entity> getEntities() {
		return entityMap.values();
	}

	/**
	 * Get all entities in the world of a given type.
	 * @param entityTypes The types of entities to get.
	 * @return A list of all entities in the world of one of the given types.
	 */
	public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
		List<Entity> r = new ArrayList<>();
		for (Entity e : getEntities()) {
			for (Class<E> entityType : entityTypes) {
				if (entityType.equals(e.getClass())) {
					r.add(e);
				}
			}
		}
		return r;
	}

	/**
	 * Get an entity from the world by ID.
	 * @param ID The ID of the entity to get.
	 * @return The entity with the given ID, or null if not found.
	 */
	public Entity getEntity(String ID) {
		return entityMap.get(ID);
	}

}
