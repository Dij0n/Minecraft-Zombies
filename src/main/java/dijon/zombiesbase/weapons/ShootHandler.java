package dijon.zombiesbase.weapons;

import dijon.zombiesbase.ZombiesBase;
import dijon.zombiesbase.playerdata.PlayerDataManager;
import dijon.zombiesbase.utility.Raycaster;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ShootHandler implements Listener {

    public ShootHandler(ZombiesBase plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void detectShoot(PlayerInteractEvent e){
        if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.PHYSICAL)){

            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            if (!Gun.isGun(item)) return;
            Gun gun = PlayerDataManager.getMainGun(e.getPlayer());

            Raycaster ray = new Raycaster(e.getPlayer(), 20, 3, gun.particle, gun.dust);

            e.getPlayer().getWorld().spawnParticle(gun.particle, ray.getFinalLoc(), 5, gun.dust);
            e.getPlayer().playSound(e.getPlayer(), gun.sound, 1, 2);

            if(ray.getBlockFound() != null){
                e.getPlayer().sendMessage("Block Found - " + ray.getBlockFound().getType());
            }
            if(ray.getEntities() != null){
                EntityType victim = ray.getEntity().getType();
                if(ray.isHeadshot()){
                    e.getPlayer().sendMessage("Entity Found - " + victim + " HEADSHOT!!!");
                    e.getPlayer().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, ray.getFinalLoc(), 3);
                    e.getPlayer().playSound(e.getPlayer(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
                }else{
                    e.getPlayer().sendMessage("Entity Found - " + victim);
                }
            }

        }
    }



}
