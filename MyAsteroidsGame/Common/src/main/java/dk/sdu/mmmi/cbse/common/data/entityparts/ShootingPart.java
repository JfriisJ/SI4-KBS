package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * This class represents the shooting part of an entity. It handles managing the cooldown
 * period between shots and the shooting state of the entity.
 */
public class ShootingPart implements EntityPart {

	private final float cooldownTime;
	private float cooldown;
	private boolean shooting;

	/**
	 * Constructs a new ShootingPart with the specified cooldown time.
	 *
	 * @param cooldownTime the time between shots in seconds
	 */
	public ShootingPart(float cooldownTime) {
		this.cooldownTime = cooldownTime;
	}

	/**
	 * Sets the shooting state of the entity. If shooting is set to true, the ShootingPart
	 * will check if it is currently on cooldown and return false if that is the case. If
	 * the ShootingPart is not on cooldown, it will set the shooting state to true and start
	 * the cooldown timer.
	 *
	 * @param shooting the shooting state to set
	 */
	public void setShooting(boolean shooting) {
		if (!shooting) {
			this.shooting = false;
			return;
		}

		if (cooldown > 0) {
			this.shooting = false;
			return;
		}

		this.shooting = true;
		this.cooldown = this.cooldownTime;
	}

	/**
	 * Returns the current shooting state of the entity.
	 *
	 * @return the shooting state of the entity
	 */
	public boolean getShooting() {
		return this.shooting;
	}

	/**
	 * Updates the ShootingPart by reducing the cooldown timer and updating the shooting
	 * state if necessary.
	 *
	 * @param gameData the current game data
	 * @param entity the entity that this part belongs to
	 */
	@Override
	public void process(GameData gameData, Entity entity) {
		if (this.cooldown > 0) {
			this.cooldown -= gameData.getDelta();
			this.shooting = false;
		} else {
			this.cooldown = 0;
		}
	}
}
