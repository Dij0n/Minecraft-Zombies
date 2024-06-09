package dijon.zombiesbase.weapons;

import dijon.zombiesbase.ZombiesBase;
import dijon.zombiesbase.playerdata.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class ReloadHandler implements Listener {

    public ReloadHandler(ZombiesBase plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void reloadKeyPress(PlayerSwapHandItemsEvent e){
        if(e.getOffHandItem() == null) return;

        if(GunType.isGun(e.getOffHandItem())){
            Player p = e.getPlayer();
            PlayerDataManager.getMainGun(e.getPlayer()).reload();
            p.playSound(p, Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, 10, 0.75f);
            p.getWorld().spawnParticle(PlayerDataManager.getMainGun(p).particle, PlayerDataManager.getGunSmokeLocation(p), 5, new Particle.DustOptions(Color.YELLOW, 1.0F));
            e.setCancelled(true);
        }
    }

}
