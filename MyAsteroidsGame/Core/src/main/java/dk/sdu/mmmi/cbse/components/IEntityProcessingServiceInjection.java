package dk.sdu.mmmi.cbse.components;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IEntityProcessingServiceInjection")
public class IEntityProcessingServiceInjection implements IProcessor {

	@Override
	public void process(GameData gameData, World world) {
		List<IEntityProcessingService> processors = SPILocator.locateAll(IEntityProcessingService.class);
		processors.forEach((processor) -> processor.process(gameData, world));
	}
}