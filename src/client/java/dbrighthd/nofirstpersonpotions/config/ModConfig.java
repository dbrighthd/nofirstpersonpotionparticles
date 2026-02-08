package dbrighthd.nofirstpersonpotions.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "nofirstpersonpotions")
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public boolean modEnabled = true;

    @ConfigEntry.Gui.Tooltip
    public boolean showParticesInThirdPerson = false;
}