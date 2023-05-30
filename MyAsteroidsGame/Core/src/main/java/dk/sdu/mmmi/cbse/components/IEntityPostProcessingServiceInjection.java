package dk.sdu.mmmi.cbse.components;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IEntityPostProcessingServiceInjection")
public class IEntityPostProcessingServiceInjection implements IProcessor {

	@Override
	public void process(GameData gameData, World world) {
		List<IPostEntityProcessingService> processors = SPILocator.locateAll(IPostEntityProcessingService.class);
		processors.forEach((processor) -> processor.process(gameData, world));
	}
}
