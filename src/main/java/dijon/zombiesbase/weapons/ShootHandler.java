package dijon.zombiesbase.weapons;

import dijon.zombiesbase.ZombiesBase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ShootHandler implements Listener {

    public ShootHandler(ZombiesBase plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onShoot(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack mainHand = p.getInventory().getItemInMainHand();
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(mainHand instanceof Gun){
                ShootManager.shoot(p, (Gun) mainHand);
            }
        }

    }


}
