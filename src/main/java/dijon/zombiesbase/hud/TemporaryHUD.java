package dijon.zombiesbase.hud;

import dijon.zombiesbase.playerdata.PlayerDataManager;
import dijon.zombiesbase.shooting.Gun;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TemporaryHUD extends BukkitRunnable {
    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()){

            Gun gun = PlayerDataManager.getMainGun(p);
            int ammo = gun.getAmmo();
            int reserveAmmo = gun.getReserveAmmo();
            int points = PlayerDataManager.getPoints(p);


            p.sendActionBar("§b§o§l" + points + "               §r§l" + ammo + " §o| §r" + reserveAmmo);


        }
    }
}
