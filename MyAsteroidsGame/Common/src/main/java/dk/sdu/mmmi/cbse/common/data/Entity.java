package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents an entity in the game world. An entity is a collection of {@link EntityPart}s, which define the entity's behavior and characteristics.
 */
public class Entity implements Serializable {
	private final UUID ID = UUID.randomUUID();

	private float[] shapeX = new float[4];
	private float[] shapeY = new float[4];
	private float radius;
	private Map<Class, EntityPart> parts;
	private float[] dists;

	/**
	 * Creates a new Entity instance with an empty collection of EntityParts.
	 */
	public Entity() {
		parts = new ConcurrentHashMap<>();
	}

	/**
	 * Adds an EntityPart to this Entity instance.
	 *
	 * @param part the EntityPart to add to this Entity.
	 */
	public void add(EntityPart part) {
		parts.put(part.getClass(), part);
	}

	/**
	 * Removes an EntityPart from this Entity instance.
	 *
	 * @param partClass the Class of the EntityPart to remove.
	 */
	public void remove(Class partClass) {
		parts.remove(partClass);
	}

	/**
	 * Retrieves an EntityPart of a given class from this Entity instance.
	 *
	 * @param partClass the Class of the EntityPart to retrieve.
	 * @param <E>       the type of the EntityPart to retrieve.
	 * @return the EntityPart of type E.
	 */
	public <E extends EntityPart> E getPart(Class partClass) {
		return (E) parts.get(partClass);
	}

	/**
	 * Sets the radius of this Entity instance.
	 *
	 * @param r the new radius value.
	 */
	public void setRadius(float r) {
		this.radius = r;
	}

	/**
	 * Retrieves the radius of this Entity instance.
	 *
	 * @return the radius of this Entity instance.
	 */
	public float getRadius() {
		return radius;
	}

	/**
	 * Retrieves the ID of this Entity instance.
	 *
	 * @return the ID of this Entity instance.
	 */
	public String getID() {
		return ID.toString();
	}

	/**
	 * Retrieves the x coordinates of the vertices of the shape of this Entity instance.
	 *
	 * @return the x coordinates of the vertices of the shape of this Entity instance.
	 */
	public float[] getShapeX() {
		return shapeX;
	}

	/**
	 * Sets the x coordinates of the vertices of the shape of this Entity instance.
	 *
	 * @param shapeX the new x coordinates of the vertices of the shape of this Entity instance.
	 */
	public void setShapeX(float[] shapeX) {
		this.shapeX = shapeX;
	}

	/**
	 * Retrieves the y coordinates of the vertices of the shape of this Entity instance.
	 *
	 * @return the y coordinates of the vertices of the shape of this Entity instance.
	 */
	public float[] getShapeY() {
		return shapeY;
	}

	/**
	 * Sets the y coordinates of the vertices of the shape of this Entity instance.
	 *
	 * @param shapeY the new y coordinates of the vertices of the shape of this Entity instance.
	 */
	public void setShapeY(float[] shapeY) {
		this.shapeY = shapeY;
	}

	/**
	 * Determines if this Entity instance is of a given type.
	 *
	 * @param type the Type to compare against.
	 * @return true if this Entity instance is of the given Type, false otherwise.
	 */
	public boolean isType(Type type) {
		return type.equals(this.getClass());
	}
	/**
	 * Retrieves the distances between this Entity instance and other entities.
	 * @return the distances between this Entity instance and other entities.
	 */
	public float[] getDists() {
		return dists;
	}
	/**
	 * sets the distances between this Entity instance and other entities.
	 * @return the distances between this Entity instance and other entities.
	 */
	public void setDists(float[] dists) {
		this.dists = dists;
	}
}
