package dijon.zombiesbase.weapons;

import dijon.zombiesbase.ZombiesBase;
import dijon.zombiesbase.playerdata.PlayerDataManager;
import dijon.zombiesbase.playerdata.Status;
import dijon.zombiesbase.utility.PluginGrabber;
import dijon.zombiesbase.utility.Raycaster;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ShootHandler implements Listener {

    public static HashMap<Player, Shooter> holdMap = new HashMap<>();
    public ShootHandler(ZombiesBase plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void detectShoot(PlayerInteractEvent e){

        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){

            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            if (!Gun.isGun(item)) return;
            Gun gun = GunType.getGun(item.getItemMeta().getCustomModelData());
            PlayerDataManager.setStatus(e.getPlayer(), Status.SHOOTING);

            refreshHolder(e.getPlayer());

            addHolder(e.getPlayer(), gun);

        }
    }

    public void refreshHolder(Player p){
        if(!holdMap.containsKey(p)) return;
        holdMap.get(p).refresh();
    }

    public void addHolder(Player p, Gun gun){
        if(holdMap.containsKey(p)) return;
        holdMap.put(p, new Shooter(p, gun));
    }









}
