package dijon.zombiesbase.weapons;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.data.type.Bed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gun {

    int maxAmmo;
    int maxClip;
    int ammo;
    int reserveAmmo;
    int damage;
    int firePerSecond;
    int customModelData;
    Sound sound;
    Particle particle;
    Particle.DustOptions dust;



    public Gun(int customModelData, int maxAmmo, int maxClip, int damage, int firePerSecond, Sound sound, Particle particle, Particle.DustOptions dust) {
        this.maxAmmo = maxAmmo;
        this.maxClip = maxClip;
        this.damage = damage;
        this.firePerSecond = firePerSecond;
        this.customModelData = customModelData;
        this.particle = particle;
        this.dust = dust;
        this.sound = sound;

        this.ammo = maxClip;
        this.reserveAmmo = maxAmmo;
    }

    public Gun(Gun gun){
        this.maxAmmo = gun.maxAmmo;
        this.maxClip = gun.maxClip;
        this.damage = gun.damage;
        this.firePerSecond = gun.firePerSecond;
        this.customModelData = gun.customModelData;
        this.particle = gun.particle;
        this.dust = gun.dust;
        this.sound = gun.sound;

        this.ammo = gun.maxClip;
        this.reserveAmmo = maxAmmo;
    }

    public ItemStack getItemStack(){
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);
        return item;
    }

    public void reduceAmmo(){
        this.ammo--;
    }

    public void reload(){

        if(ammo == maxClip) return; //Don't reload on a full clip
        if(reserveAmmo == 0) return; //Don't reload if reserve is empty

        int ammoToReload = maxClip - ammo;

        if(reserveAmmo < ammoToReload){ //If reserve is not full enough to fully reload, do a partial reload
            ammo += reserveAmmo;
            reserveAmmo = 0;
        }else{
            reserveAmmo -= ammoToReload; //If all's good all's good
            ammo = maxClip;
        }

    }



    //----------------------------
    //---GETTER AND SETTER HELL---
    //----------------------------

    //"This is where we send the evil ones"


    public int getMaxAmmo() {
        return maxAmmo;
    }
    public int getMaxClip() {
        return maxClip;
    }
    public int getAmmo() {
        return ammo;
    }
    public int getReserveAmmo() {
        return reserveAmmo;
    }
    public int getDamage() {
        return damage;
    }
    public int getFirePerSecond() {
        return firePerSecond;
    }
    public int getCustomModelData() {
        return customModelData;
    }
    public Sound getSound() {
        return sound;
    }
    public Particle getParticle() {
        return particle;
    }
    public Particle.DustOptions getDust() {
        return dust;
    }
}
