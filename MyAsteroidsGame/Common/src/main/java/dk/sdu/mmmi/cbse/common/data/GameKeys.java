package dk.sdu.mmmi.cbse.common.data;

/**
 * Holds the state of the keyboard keys.
 * <p>
 * Includes the following keys: UP, LEFT, DOWN, RIGHT, ENTER, ESCAPE, SPACE, SHIFT.
 */
public class GameKeys {

	private static boolean[] keys;
	private static boolean[] pkeys;

	private static final int NUM_KEYS = 8;
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int ENTER = 4;
	public static final int ESCAPE = 5;
	public static final int SPACE = 6;
	public static final int SHIFT = 7;

	/**
	 * Constructs a new `GameKeys` object.
	 */
	public GameKeys() {
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}

	/**
	 * Updates the state of the keys.
	 */
	public void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			pkeys[i] = keys[i];
		}
	}

	/**
	 * Sets the state of a key.
	 * @param k the key to set
	 * @param b the state to set the key to
	 */
	public void setKey(int k, boolean b) {
		keys[k] = b;
	}

	/**
	 * Returns whether a key is currently down.
	 * @param k the key to check
	 * @return whether the key is down
	 */
	public boolean isDown(int k) {
		return keys[k];
	}

	/**
	 * Returns whether a key was just pressed.
	 * @param k the key to check
	 * @return whether the key was just pressed
	 */
	public boolean isPressed(int k) {
		return keys[k] && !pkeys[k];
	}

}

