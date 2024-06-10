package dijon.zombiesbase.shooting;

import dijon.zombiesbase.playerdata.PlayerDataManager;
import dijon.zombiesbase.playerdata.Status;
import dijon.zombiesbase.utility.PluginGrabber;
import dijon.zombiesbase.utility.Raycaster;
import dijon.zombiesbase.shooting.listeners.ShootHandler;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
        this.firePerSecond = gunCopy.getFirePerSecond();
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

        if(PlayerDataManager.getMainGun(p).getAmmo() == 0){

            p.getWorld().spawnParticle(gunCopy.getParticle(), PlayerDataManager.getGunSmokeLocation(p), 5, new Particle.DustOptions(Color.GRAY, 1.0F));
            p.playSound(p, Sound.ITEM_FLINTANDSTEEL_USE, 1, 0.75f);
            return;
        } //Check clip is empty

        PlayerDataManager.getMainGun(p).reduceAmmo();
        Raycaster ray = new Raycaster(p, 20, 4, gunCopy.getParticle(), gunCopy.getDust());
        p.getWorld().spawnParticle(gunCopy.getParticle(), ray.getFinalLoc(), 5, gunCopy.getDust());
        p.playSound(p, gunCopy.getSound(), 1, 2);

        if(ray.getEntities() != null) shotLanded(ray);

    }

    public void shotLanded(Raycaster ray){
        LivingEntity victim = (LivingEntity) ray.getEntity();

        if(ray.isHeadshot()){
            p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, ray.getFinalLoc(), 3);
            p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
        } //TEMP --- Particle stuff

        PlayerDataManager.increasePoints(p, 10);

        if(victim.getHealth() - gunCopy.getDamage() <= 0){
            if(ray.isHeadshot()){
                PlayerDataManager.increasePoints(p, 90); //Ten less since they get the 10 points from the hit
            }else{
                PlayerDataManager.increasePoints(p, 50);
            }
        }

        victim.damage(gunCopy.getDamage());
    }

    public void fullCancel(){
        ShootHandler.holdMap.remove(p);
        PlayerDataManager.setStatus(p, Status.IDLE);
        cancel();
    }

    
}