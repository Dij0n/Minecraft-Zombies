package dijon.zombiesbase.weapons;

import dijon.zombiesbase.ZombiesBase;
import dijon.zombiesbase.playerdata.PlayerDataManager;
import dijon.zombiesbase.utility.PluginGrabber;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class HoldingHandler implements Listener {

    public HoldingHandler(ZombiesBase plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void detectHolding(PlayerItemHeldEvent e){

        //---------THIS IS TEMPORARY-----------------------
        //---------GUNS ARE SET BY HOLDING FOR NOW---------
        //---------WILL BE CHANGED TO WALL BUYS AND BOX----

        if(e.getPlayer().getInventory().getItem(e.getNewSlot()) == null) return;
        ItemStack inHand = e.getPlayer().getInventory().getItem(e.getNewSlot());

        if(Gun.isGun(inHand)){
            int customMD = inHand.getItemMeta().getCustomModelData();
            Gun gun = GunType.getGun(customMD);
            PlayerDataManager.setMainGun(e.getPlayer(), gun);
        }else{
            PlayerDataManager.setMainGun(e.getPlayer(), GunType.getGun(0));
        }
    }
}
