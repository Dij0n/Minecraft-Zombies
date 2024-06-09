package dijon.zombiesbase.weapons;

import dijon.zombiesbase.playerdata.PlayerData;
import dijon.zombiesbase.playerdata.PlayerDataManager;
import dijon.zombiesbase.playerdata.Status;
import dijon.zombiesbase.utility.PluginGrabber;
import dijon.zombiesbase.utility.Raycaster;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class Shooter extends BukkitRunnable {

    int slotId;
    Player p;
    Gun gunCopy;
    double firePerSecond;
    double timer;
    int holdTimer;

    public Shooter(Player p){
        this.p = p;
        this.gunCopy = new Gun(PlayerDataManager.getMainGun(p));
        this.firePerSecond = gunCopy.firePerSecond;
        if(firePerSecond != 0) firePerSecond = 1/firePerSecond;
        firePerSecond *= 20;
        timer = firePerSecond + 1;
        holdTimer = (int) Math.max(5, firePerSecond);
        runTaskTimer(PluginGrabber.plugin, 0, 1);
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
            fullCancel();
        }

    }

    public void refresh(){
        if(holdTimer <= 5) holdTimer = 5;
    }

    public void shoot(){
        p.sendMessage(String.valueOf(PlayerDataManager.getMainGun(p).ammo));

        if(PlayerDataManager.getMainGun(p).ammo == 0){


            p.getWorld().spawnParticle(gunCopy.particle, PlayerDataManager.getGunSmokeLocation(p), 5, new Particle.DustOptions(Color.GRAY, 1.0F));

            p.playSound(p, Sound.ITEM_FLINTANDSTEEL_USE, 1, 0.75f);
            return;
        } //Clip is empty

        PlayerDataManager.getMainGun(p).reduceAmmo();

        Raycaster ray = new Raycaster(p, 20, 4, gunCopy.particle, gunCopy.dust);

        p.getWorld().spawnParticle(gunCopy.particle, ray.getFinalLoc(), 5, gunCopy.dust);
        p.playSound(p, gunCopy.sound, 1, 2);

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

    public void fullCancel(){
        ShootHandler.holdMap.remove(p);
        PlayerDataManager.setStatus(p, Status.IDLE);
        cancel();
    }

    
}
