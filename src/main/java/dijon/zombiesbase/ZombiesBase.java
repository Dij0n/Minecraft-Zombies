package dijon.zombiesbase;

import dijon.zombiesbase.commands.givegun;
import dijon.zombiesbase.hud.TemporaryHUD;
import dijon.zombiesbase.tick.TestRunnable;
import dijon.zombiesbase.utility.PluginGrabber;
import dijon.zombiesbase.shooting.GunType;
import dijon.zombiesbase.shooting.listeners.HoldingHandler;
import dijon.zombiesbase.shooting.listeners.ReloadHandler;
import dijon.zombiesbase.shooting.listeners.ShootHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class ZombiesBase extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("If you're seeing this, it works");

        new TestRunnable().runTaskTimer(this, 0, 1);

        //-------UTILITY---------
        new PluginGrabber(this);
        new GunType();

        //-------HANDLERS---------
        new ShootHandler(this);
        new HoldingHandler(this);
        new ReloadHandler(this);

        //-------COMMANDS----------
        this.getCommand("givegun").setExecutor(new givegun());

        //------RUNNABLES----------
        new TemporaryHUD().runTaskTimer(this, 0, 1);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
