package dijon.zombiesbase.shooting;

import dijon.zombiesbase.perks.PerkType;
import dijon.zombiesbase.playerdata.PlayerDataController;
import dijon.zombiesbase.playerdata.PlayerDataManager;
import dijon.zombiesbase.playerdata.Status;
import dijon.zombiesbase.shooting.recoil.Recoiler;
import dijon.zombiesbase.utility.PluginGrabber;
import dijon.zombiesbase.utility.Raycaster;
import dijon.zombiesbase.shooting.listeners.ShootHandler;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Shooter extends BukkitRunnable {

    int slotId;
    Player p;
    Gun gunCopy;
    double firePerSecond;
    double timer;
    double fireRate;
    double holdTimer;
    Recoiler recoiler;


    PlayerDataController pd;


    public Shooter(Player p){
        this.p = p;
        this.gunCopy = new Gun(PlayerDataManager.getMainGun(p));
        this.firePerSecond = gunCopy.getFirePerSecond();

        pd = new PlayerDataController(p);
        recoiler = new Recoiler(p);

        if(pd.hasPerk(PerkType.DOUBLETAP)){ //PERK CHECK
            firePerSecond *= 1.33;
            gunCopy.damage *= 2;
        }



        if(firePerSecond != 0) fireRate = 1/firePerSecond;
        fireRate *= 20;
        timer = fireRate + 1;
        holdTimer = Math.max(5, fireRate);

        runTaskTimer(PluginGrabber.plugin, 0, 1);
    }

    @Override
    public void run() {
        recoiler.zoomIn();
        if(timer >= fireRate){
            shoot();
            timer = 0;
        }

        if(timer <= fireRate){
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

        recoiler.zoomOut();

        if(pd.getStatus().equals(Status.RELOADING)) return;

        if(pd.getMainGun().getAmmo() == 0){
            p.getWorld().spawnParticle(gunCopy.getParticle(), pd.getGunSmokeLocation(), 5, new Particle.DustOptions(Color.GRAY, 1.0F));
            p.playSound(p, Sound.ITEM_FLINTANDSTEEL_USE, 1, 0.75f);
            pd.reloadAttempt();
            return;
        } //Check if clip is empty

        pd.getMainGun().reduceAmmo();
        Raycaster ray = new Raycaster(p, 20, 4, gunCopy.getParticle(), gunCopy.getDust());
        p.getWorld().spawnParticle(gunCopy.getParticle(), ray.getFinalLoc(), 5, gunCopy.getDust());
        p.playSound(p, gunCopy.getSound(), 1, 2);

        if(ray.getEntities() != null) shotLanded(ray);

    }

    public void shotLanded(Raycaster ray){
        LivingEntity victim = (LivingEntity) ray.getEntity();
        if(victim.isDead()) return;

        if(ray.isHeadshot()){
            p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, ray.getFinalLoc(), 3);
            p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
        }

        pd.increasePoints(10);

        if(victim.getHealth() - gunCopy.getDamage() <= 0){
            if(ray.isHeadshot()){
                pd.increasePoints(90); //Ten less since they get the 10 points from the hit
            }else{
                pd.increasePoints(50);
            }
        }

        victim.damage(gunCopy.getDamage());
        victim.setNoDamageTicks(1);
    }

    public void fullCancel(){
        ShootHandler.holdMap.remove(p);
        if(!pd.getStatus().equals(Status.RELOADING)){
            pd.setStatus(Status.IDLE);
        }
        cancel();
    }

    
}
