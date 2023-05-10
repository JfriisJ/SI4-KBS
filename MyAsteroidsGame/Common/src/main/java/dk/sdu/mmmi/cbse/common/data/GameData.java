package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.events.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData {

	/**
	 * The delta time since the last frame.
	 */
	private float delta;
	/**
	 * The width of the display frame.
	 */
	private float displayWidth;
	/**
	 * The height of the display frame.
	 */
	private float displayHeight;
	/**
	 * The game keys.
	 */
	private final GameKeys keys = new GameKeys();
	/**
	 * The list of events.
	 */
	private final List<Event> events = new CopyOnWriteArrayList<>();

	/**
	 * Adds an event to the list.
	 * @param e the event to add.
	 */
	public void addEvent(Event e) {
		events.add(e);
	}

	/**
	 * Removes an event from the list.
	 * @param e the event to remove.
	 */
	public void removeEvent(Event e) {
		events.remove(e);
	}

	/**
	 * Returns the list of events.
	 * @return the list of events.
	 */
	public List<Event> getEvents() {
		return events;
	}

	/**
	 * Returns the game keys.
	 * @return the game keys.
	 */
	public GameKeys getKeys() {
		return keys;
	}

	/**
	 * Sets the delta time since the last frame.
	 * @param delta the delta time.
	 */
	public void setDelta(float delta) {
		this.delta = delta;
	}

	/**
	 * Returns the delta time since the last frame.
	 * @return the delta time.
	 */
	public float getDelta() {
		return delta;
	}

	/**
	 * Sets the width of the display frame.
	 * @param width the width of the display frame.
	 */
	public void setDisplayWidth(float width) {
		this.displayWidth = width;
	}

	/**
	 * Returns the width of the display frame.
	 * @return the width of the display frame.
	 */
	public float getDisplayWidth() {
		return displayWidth;
	}

	/**
	 * Sets the height of the display frame.
	 * @param height the height of the display frame.
	 */
	public void setDisplayHeight(float height) {
		this.displayHeight = height;
	}

	/**
	 * Returns the height of the display frame.
	 * @return the height of the display frame.
	 */
	public float getDisplayHeight() {
		return displayHeight;
	}

	/**
	 * Returns a list of events of a given type that have a given entity ID as their source.
	 * @param type the type of event.
	 * @param sourceID the entity ID of the source.
	 * @return the list of events.
	 */
	public <E extends Event> List<Event> getEvents(Class<E> type, String sourceID) {
		List<Event> r = new ArrayList();
		for (Event event : events) {
			if (event.getClass().equals(type) && event.getSource().getID().equals(sourceID)) {
				r.add(event);
			}
		}

		return r;
	}
}
