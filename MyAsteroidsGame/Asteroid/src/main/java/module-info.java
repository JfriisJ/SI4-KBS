import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IAsteroidSplitter;

module Asteroid {
	requires Common;

	provides IGamePluginService with dk.sdu.mmmi.cbse.asteroidsystem.AsteroidPlugin;
	provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroidsystem.AsteroidProcessingService;
	provides IAsteroidSplitter with dk.sdu.mmmi.cbse.asteroidsystem.AsteroidSplitterImpl;

}