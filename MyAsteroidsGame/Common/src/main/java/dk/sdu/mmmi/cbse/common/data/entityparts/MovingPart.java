package dk.sdu.mmmi.cbse.common.data.entityparts;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * MovingPart is responsible for handling movement of entities in game.
 */
public class MovingPart
		implements EntityPart {

	private float dx, dy;
	private float deceleration, acceleration;
	private float maxSpeed, rotationSpeed, speed;
	private boolean left, right, up, startingSpeedSet;

	/**
	 * Constructs a new MovingPart object.
	 *
	 * @param deceleration The rate of deceleration when the entity stops accelerating.
	 * @param acceleration The rate of acceleration when the entity begins moving.
	 * @param maxSpeed The maximum speed the entity can reach.
	 * @param rotationSpeed The rate of rotation when the entity turns.
	 */
	public MovingPart(float deceleration, float acceleration, float maxSpeed, float rotationSpeed) {
		this.deceleration = deceleration;
		this.acceleration = acceleration;
		this.maxSpeed = maxSpeed;
		this.rotationSpeed = rotationSpeed;
	}

	/**
	 * Constructs a new MovingPart object with a starting speed.
	 *
	 * @param deacceleration The rate of deceleration when the entity stops accelerating.
	 * @param acceleration The rate of acceleration when the entity begins moving.
	 * @param maxSpeed The maximum speed the entity can reach.
	 * @param rotationSpeed The rate of rotation when the entity turns.
	 * @param speed The starting speed of the entity.
	 */
	public MovingPart(float deacceleration, float acceleration, float maxSpeed, float rotationSpeed, float speed) {
		this.deceleration = deacceleration;
		this.acceleration = acceleration;
		this.maxSpeed = maxSpeed;
		this.rotationSpeed = rotationSpeed;
		this.speed = speed;
		this.startingSpeedSet = !(this.speed > 0);
	}

	public void setDeceleration(float deceleration) {
		this.deceleration = deceleration;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setRotationSpeed(float rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	/**
	 * Gets the current speed of the entity.
	 *
	 * @return The current speed of the entity.
	 */
	public float getSpeed() {
		this.speed = (float) sqrt(dx * dx + dy * dy);
		return this.speed;
	}

	/**
	 * Sets the speed of the entity.
	 *
	 * @param speed The new speed of the entity.
	 */
	public void setSpeed(float speed){
		this.speed = speed;
	}

	/**
	 * Overrides EntityPart.process. Handles movement of entities in game.
	 *
	 * @param gameData The current game state data.
	 * @param entity The entity to be processed.
	 */
	@Override
	public void process(GameData gameData, Entity entity) {
		PositionPart positionPart = entity.getPart(PositionPart.class);
		float x = positionPart.getX();
		float y = positionPart.getY();
		float radians = positionPart.getRadians();
		float dt = gameData.getDelta();

		if (!this.startingSpeedSet){
			dx = MathUtils.cos(radians) * speed;
			dy = MathUtils.sin(radians) * speed;
			this.startingSpeedSet = true;
		}

		// turning
		if (left) {
			radians += rotationSpeed * dt;
		}

		if (right) {
			radians -= rotationSpeed * dt;
		}

		// accelerating
		if (up) {
			dx += cos(radians) * acceleration * dt;
			dy += sin(radians) * acceleration * dt;
		}

		// deccelerating
		float vec = (float) sqrt(dx * dx + dy * dy);
		if (vec > 0) {
			dx -= (dx / vec) * deceleration * dt;
			dy -= (dy / vec) * deceleration * dt;
		}
		if (vec > maxSpeed) {
			dx = (dx / vec) * maxSpeed;
			dy = (dy / vec) * maxSpeed;
		}

		// set position
		x += dx * dt;
		if (x > gameData.getDisplayWidth()) {
			x = 0;
		}
		else if (x < 0) {
			x = gameData.getDisplayWidth();
		}

		y += dy * dt;
		if (y > gameData.getDisplayHeight()) {
			y = 0;
		}
		else if (y < 0) {
			y = gameData.getDisplayHeight();
		}

		positionPart.setX(x);
		positionPart.setY(y);

		positionPart.setRadians(radians);
	}

}