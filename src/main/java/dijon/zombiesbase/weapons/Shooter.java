package dijon.zombiesbase.weapons;

import dijon.zombiesbase.playerdata.PlayerData;
import dijon.zombiesbase.playerdata.PlayerDataManager;
import dijon.zombiesbase.playerdata.Status;
import dijon.zombiesbase.utility.PluginGrabber;
import dijon.zombiesbase.utility.Raycaster;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Shooter extends BukkitRunnable {

    int slotId;
    Player p;
    Gun gun;
    double firePerSecond;
    double timer;
    int holdTimer;
    int holdMax;

    public Shooter(Player p, Gun gun){
        this.p = p;
        this.gun = gun;
        this.firePerSecond = gun.firePerSecond;
        if(firePerSecond != 0) firePerSecond = 1/firePerSecond;
        firePerSecond *= 20;
        timer = firePerSecond + 1;
        runTaskTimer(PluginGrabber.plugin, 0, 1);

        holdTimer = (int) Math.max(5, firePerSecond);
        holdMax = holdTimer;
    }

    @Override
    public void run() {

        if(timer >= firePerSecond){
            shoot();
            timer = 0;
        }

        if(timer <= firePerSecond){
            timer++;
        }

        holdTimer--;
        if(holdTimer <= 0){
            ShootHandler.holdMap.remove(p);
            cancel();
        }

    }

    public void refresh(){
        if(holdTimer <= 5) holdTimer = holdMax;
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
