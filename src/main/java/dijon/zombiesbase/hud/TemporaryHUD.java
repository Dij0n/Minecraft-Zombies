package dijon.zombiesbase.hud;

import dijon.zombiesbase.playerdata.PlayerDataManager;
import dijon.zombiesbase.weapons.Gun;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class TemporaryHUD extends BukkitRunnable {
    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()){

            Gun gun = PlayerDataManager.getMainGun(p);
            int ammo = gun.getAmmo();
            int reserveAmmo = gun.getReserveAmmo();


            p.sendActionBar("§l" + ammo + "§o§l|§r " + reserveAmmo);

        }
    }
}