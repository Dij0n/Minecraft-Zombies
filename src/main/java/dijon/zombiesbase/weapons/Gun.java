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
    int damage;
    int fireRate;
    int customModelData;
    Sound sound;
    Particle particle;
    Particle.DustOptions dust;



    public Gun(int customModelData, int maxAmmo, int maxClip, int damage, int fireRate, Sound sound, Particle particle, Particle.DustOptions dust) {
        this.maxAmmo = maxAmmo;
        this.maxClip = maxClip;
        this.damage = damage;
        this.fireRate = fireRate;
        this.customModelData = customModelData;
        this.particle = particle;
        this.dust = dust;
        this.sound = sound;
    }

    public ItemStack getItemStack(){
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);
        return item;
    }

    public static boolean isGun(ItemStack item){
        return (item.getItemMeta().hasCustomModelData() && (item.getType().equals(Material.PRISMARINE_SHARD)));
    }


}
