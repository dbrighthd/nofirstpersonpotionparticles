package dbrighthd.nofirstpersonpotions;

import dbrighthd.nofirstpersonpotions.config.ConfigManager;
import dbrighthd.nofirstpersonpotions.config.ConfigScreenBuilder;
import dbrighthd.nofirstpersonpotions.config.FallbackConfigMessageScreen;
import dbrighthd.nofirstpersonpotions.config.ModConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfigClient;
import net.fabricmc.loader.api.FabricLoader;

import static dbrighthd.nofirstpersonpotions.config.ConfigManager.getConfig;

public class NoFirstPersonPotionsModMenu implements ModMenuApi {
    public static final boolean CLOTH_LOADED = FabricLoader.getInstance().isModLoaded("cloth-config");

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if(CLOTH_LOADED) {
            return parent -> ConfigScreenBuilder.buildConfigScreen(parent, getConfig());
        }
        return FallbackConfigMessageScreen::new;
    }
}