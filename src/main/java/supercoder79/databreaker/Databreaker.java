package supercoder79.databreaker;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class Databreaker implements ModInitializer {

	@Override
	public void onInitialize() {
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			System.out.println("I am become Databreaker, destroyer of loading times");
		} else {
			System.out.println("SUPERCODER79 IS NOT RESPONSIBLE FOR ANY DAMAGE CAUSED BY USING DATABREAKER IN A PRODUCTION ENVIRONMENT.");
			System.out.println("THERE IS A GOOD CHANCE THAT YOUR GAME WILL BE COMPLETELY SCREWED UP IF YOU LOAD UP A WORLD WITHOUT OPTIMIZING.");
			System.out.println("DO NOT USE DATABREAKER IN A NON-DEVELOPMENT ENVIRONMENT.");
		}
	}
}
