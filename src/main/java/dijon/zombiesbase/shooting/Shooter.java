package dijon.zombiesbase.shooting;

import dijon.zombiesbase.playerdata.PlayerData;
import dijon.zombiesbase.playerdata.PlayerDataController;
import dijon.zombiesbase.playerdata.PlayerDataManager;
import dijon.zombiesbase.playerdata.Status;
import dijon.zombiesbase.utility.PluginGrabber;
import dijon.zombiesbase.utility.Raycaster;
import dijon.zombiesbase.shooting.listeners.ShootHandler;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Shooter extends BukkitRunnable {

    int slotId;
    Player p;
    Gun gunCopy;
    double firePerSecond;
    double timer;
    double holdTimer;

    PlayerDataController pd;

    public Shooter(Player p){
        pd = new PlayerDataController(p);
        this.p = p;
        this.gunCopy = new Gun(pd.getMainGun());
        this.firePerSecond = gunCopy.getFirePerSecond();
        if(firePerSecond != 0) firePerSecond = 1/firePerSecond;
        firePerSecond *= 20;
        timer = firePerSecond + 1;
        holdTimer = Math.max(5, firePerSecond);
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

        if(pd.getStatus().equals(Status.RELOADING)) return;

        if(pd.getMainGun().getAmmo() == 0){
            p.getWorld().spawnParticle(gunCopy.getParticle(), pd.getGunSmokeLocation(), 5, new Particle.DustOptions(Color.GRAY, 1.0F));
            p.playSound(p, Sound.ITEM_FLINTANDSTEEL_USE, 1, 0.75f);
            pd.reload();
            return;
        } //Check if clip is empty


        pd.getMainGun().reduceAmmo();

        Raycaster ray = new Raycaster(p, 20, 8, gunCopy.getParticle(), gunCopy.getDust());
        p.getWorld().spawnParticle(gunCopy.getParticle(), ray.getFinalLoc(), 5, gunCopy.dust);
        p.playSound(p, gunCopy.getSound(), 1, 2);

        if(ray.getEntities() != null) shotLanded(ray);

    }

    public void shotLanded(Raycaster ray){
        LivingEntity victim = (LivingEntity) ray.getEntity();

        if (victim.isDead()) return;

        if(ray.isHeadshot()){
            p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, ray.getFinalLoc(), 3);
            p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
        } //TEMP --- Particle stuff

        pd.increasePoints(10);


        if(victim.getHealth() - gunCopy.getDamage() <= 0){
            if(ray.isHeadshot()){
                pd.increasePoints(90); //Ten less since they get the 10 points from the hit
            }else{
                pd.increasePoints(50);
            }
        }

        victim.damage(gunCopy.getDamage());
        p.sendMessage(String.valueOf(victim.getNoDamageTicks()));
        Bukkit.getScheduler().runTaskLater(PluginGrabber.plugin, () -> {

            p.sendMessage(String.valueOf(victim.getNoDamageTicks()));
            victim.setNoDamageTicks(0);

        }, 1);


    }

    public void fullCancel(){
        ShootHandler.holdMap.remove(p);
        if(!pd.getStatus().equals(Status.RELOADING)) pd.setStatus(Status.IDLE);
        cancel();
    }

    
}
