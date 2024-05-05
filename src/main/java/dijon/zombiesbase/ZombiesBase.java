package dijon.zombiesbase;

import dijon.zombiesbase.tick.TestRunnable;
import jdk.incubator.vector.VectorOperators;
import org.bukkit.plugin.java.JavaPlugin;

public final class ZombiesBase extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("If you're seeing this, it works");
        new TestRunnable().runTaskTimer(this, 0, 1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
