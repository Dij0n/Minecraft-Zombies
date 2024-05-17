package dijon.zombiesbase.weapons;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gun {

    int maxAmmo;
    int maxClip;
    int damage;
    int fireRate;
    int customModelData;
    //TEMP!!!!
    Color color;

    public Gun(int customModelData, int maxAmmo, int maxClip, int damage, int fireRate, Color color) {
        this.maxAmmo = maxAmmo;
        this.maxClip = maxClip;
        this.damage = damage;
        this.fireRate = fireRate;
        this.customModelData = customModelData;
        this.color = color;
    }

    public ItemStack getItemStack(){
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);
        return item;
    }


}
