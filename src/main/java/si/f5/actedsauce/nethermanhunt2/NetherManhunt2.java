package si.f5.actedsauce.nethermanhunt2;

import org.bukkit.plugin.java.JavaPlugin;

public final class NetherManhunt2 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Main main = new Main(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
