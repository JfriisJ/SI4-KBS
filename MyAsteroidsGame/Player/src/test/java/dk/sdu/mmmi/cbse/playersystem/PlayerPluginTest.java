package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerPluginTest {

	@Test
	void verifyExistence() {
		// Create mocks
		Entity player = mock(Player.class);
		GameData gameData = mock(GameData.class);
		World world = mock(World.class);

		//add a lifepart to the player
		LifePart lifePart = mock(LifePart.class);

		// Set up mocks
		when(player.getPart(LifePart.class)).thenReturn(lifePart);
		when(lifePart.getLife()).thenReturn(2);

		// Create the plugin
		PlayerPlugin playerPlugin = new PlayerPlugin();
		playerPlugin.start(gameData, world);

		// Test that the entity is added to the world
		verify(world).addEntity(any(Player.class));

		// Check that the entity has one life
		assertEquals(2, lifePart.getLife());
	}

	@Disabled
	void stop() {
	}

}