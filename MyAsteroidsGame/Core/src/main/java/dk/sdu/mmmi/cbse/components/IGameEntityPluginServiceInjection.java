package dk.sdu.mmmi.cbse.components;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IGameEntityPluginServiceInjection")
public class IGameEntityPluginServiceInjection {

	public void startPlugins(GameData gameData, World world) {
		List<IGamePluginService> plugins = SPILocator.locateAll(IGamePluginService.class);
		plugins.forEach((plugin) -> plugin.start(gameData, world));
	}
}

