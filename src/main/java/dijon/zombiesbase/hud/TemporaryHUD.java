package dijon.zombiesbase.hud;

import dijon.zombiesbase.playerdata.PlayerDataController;
import dijon.zombiesbase.shooting.Gun;
import net.kyori.adventure.audience.Audience;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TemporaryHUD extends BukkitRunnable {

    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()){
            PlayerDataController pd = new PlayerDataController(p);

            Gun gun = pd.getMainGun();
            int ammo = gun.getAmmo();
            int reserveAmmo = gun.getReserveAmmo();
            int points = pd.getPoints();

            p.sendActionBar("§b§o§l" + points + "   §r§l" + ammo + " §o| §r" + reserveAmmo);


        }
    }
}
