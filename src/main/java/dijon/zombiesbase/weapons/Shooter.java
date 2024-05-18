package dijon.zombiesbase.weapons;

import dijon.zombiesbase.playerdata.PlayerData;
import dijon.zombiesbase.playerdata.PlayerDataManager;
import dijon.zombiesbase.playerdata.Status;
import dijon.zombiesbase.utility.Raycaster;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Shooter extends BukkitRunnable {

    int slotId;
    Player p;
    Gun gun;
    double fireRate;
    double timer;

    public Shooter(Player p, Gun gun, int slotId){
        this.slotId = slotId;
        this.p = p;
        this.gun = gun;
        fireRate = gun.fireRate;
        fireRate = 1/fireRate;
        fireRate *= 20;
        timer = fireRate + 1;
    }

    @Override
    public void run() {

        int heldSlot = p.getInventory().getHeldItemSlot();

        if(heldSlot != slotId){
            cancel();
        }

        if(PlayerDataManager.getStatus(p) == Status.SHOOTING && timer >= fireRate){
            shoot();
            timer = 0;
        }

        if(timer <= fireRate){
            timer++;
        }

    }

    public void shoot(){
        Raycaster ray = new Raycaster(p, 20, 4, gun.particle, gun.dust);

        p.getWorld().spawnParticle(gun.particle, ray.getFinalLoc(), 5, gun.dust);
        p.playSound(p, gun.sound, 1, 2);

        if(ray.getBlockFound() != null){
            p.sendMessage("Block Found - " + ray.getBlockFound().getType());
        }
        if(ray.getEntities() != null){
            EntityType victim = ray.getEntity().getType();
            if(ray.isHeadshot()){
                p.sendMessage("Entity Found - " + victim + " HEADSHOT!!!");
                p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, ray.getFinalLoc(), 3);
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
            }else{
                p.sendMessage("Entity Found - " + victim);
            }
        }
    }
}
