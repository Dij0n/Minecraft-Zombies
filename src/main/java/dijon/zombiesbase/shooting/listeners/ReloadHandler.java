package dijon.zombiesbase.shooting.listeners;

import dijon.zombiesbase.ZombiesBase;
import dijon.zombiesbase.playerdata.PlayerData;
import dijon.zombiesbase.playerdata.PlayerDataManager;
import dijon.zombiesbase.playerdata.Status;
import dijon.zombiesbase.shooting.GunType;
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
        Player p = e.getPlayer();

        if(e.getOffHandItem() == null) return;

        if(GunType.isGun(e.getOffHandItem())){

            PlayerDataManager.reload(p);

            e.setCancelled(true);

        }
    }

}
