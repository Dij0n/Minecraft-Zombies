package dijon.zombiesbase;

import dijon.zombiesbase.tick.TestRunnable;
import dijon.zombiesbase.weapons.ShootHandler;
import jdk.incubator.vector.VectorOperators;
import org.bukkit.plugin.java.JavaPlugin;

public final class ZombiesBase extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("If you're seeing this, it works");
        new TestRunnable().runTaskTimer(this, 0, 1);

        new ShootHandler(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
