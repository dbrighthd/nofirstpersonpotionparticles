package dbrighthd.nofirstpersonpotions.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreenBuilder {
    private static final ModConfig defaultModConfig = new ModConfig();

    public static Screen buildConfigScreen(Screen parent, ModConfig config) {

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("text.nofirstpersonpotions.title"));
        ConfigCategory general = builder.getOrCreateCategory(Component.translatable("text.nofirstpersonpotions.category.general"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("text.nofirstpersonpotions.option.modEnabled"), config.modEnabled)
                .setDefaultValue(defaultModConfig.modEnabled)
                .setTooltip(Component.translatable("text.nofirstpersonpotions.option.modEnabled.@Tooltip"))
                .setSaveConsumer(newValue -> config.modEnabled = newValue)
                .build());
        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("text.nofirstpersonpotions.option.showParticesInThirdPerson"), config.showParticesInThirdPerson)
                .setDefaultValue(defaultModConfig.showParticesInThirdPerson)
                .setTooltip(Component.translatable("text.nofirstpersonpotions.option.showParticesInThirdPerson.@Tooltip"))
                .setSaveConsumer(newValue -> config.showParticesInThirdPerson = newValue)
                .build());
        builder.setSavingRunnable(ConfigManager::save);
        return builder.build();
    }
}