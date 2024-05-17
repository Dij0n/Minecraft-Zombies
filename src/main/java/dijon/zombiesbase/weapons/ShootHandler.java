package dijon.zombiesbase.weapons;

import dijon.zombiesbase.ZombiesBase;
import dijon.zombiesbase.playerdata.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class ShootHandler implements Listener {

    public ShootHandler(ZombiesBase plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void detectHolding(PlayerItemHeldEvent e){

        if(e.getPlayer().getInventory().getItem(e.getNewSlot()) == null) return;
        ItemStack inHand = e.getPlayer().getInventory().getItem(e.getNewSlot());

        if(inHand.getItemMeta().hasCustomModelData() && inHand.getType().equals(Material.PRISMARINE_SHARD)){
            int customMD = inHand.getItemMeta().getCustomModelData();
            PlayerDataManager.setMainGun(e.getPlayer(), GunType.getGun(customMD));

        }else{
            PlayerDataManager.setMainGun(e.getPlayer(), GunType.getGun(0));
        }
    }

    @EventHandler
    public void detectShoot(PlayerInteractEvent e){
        if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){

            int gunID = PlayerDataManager.getMainGun(e.getPlayer()).customModelData;
            e.getPlayer().sendMessage(String.valueOf(gunID));
        }
    }



}
