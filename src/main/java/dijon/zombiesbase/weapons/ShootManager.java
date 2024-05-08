package dijon.zombiesbase.weapons;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ShootManager {

    public static void shoot(Player p, Gun gun){

        Bukkit.getLogger().info(String.valueOf(gun.getGunVal()));

    }

}
