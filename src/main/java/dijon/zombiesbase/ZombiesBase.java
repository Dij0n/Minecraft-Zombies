package dijon.zombiesbase;

import dijon.zombiesbase.commands.givegun;
import dijon.zombiesbase.tick.TestRunnable;
import dijon.zombiesbase.utility.PluginGrabber;
import dijon.zombiesbase.weapons.GunType;
import dijon.zombiesbase.weapons.ShootHandler;
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


        //-------COMMANDS----------
        this.getCommand("givegun").setExecutor(new givegun());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
