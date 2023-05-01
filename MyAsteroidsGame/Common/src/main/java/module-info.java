import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.services.IBulletCreateService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Common {

	uses IAsteroidSplitter;
	uses IBulletCreateService;
	uses IEntityProcessingService;
	uses IGamePluginService;
	uses IPostEntityProcessingService;

	exports dk.sdu.mmmi.cbse.common.data;
	exports dk.sdu.mmmi.cbse.common.data.entityparts;
	exports dk.sdu.mmmi.cbse.common.events;
	exports dk.sdu.mmmi.cbse.common.services;
	exports dk.sdu.mmmi.cbse.common.util;

}