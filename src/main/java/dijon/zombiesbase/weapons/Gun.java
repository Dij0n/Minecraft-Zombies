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
        this.ammo = this.maxClip;
    }


}
