package dbrighthd.nofirstpersonpotions;

import dbrighthd.nofirstpersonpotions.config.ConfigManager;
import dbrighthd.nofirstpersonpotions.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class NoFirstPersonPotionsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ConfigManager.load();
	}
}