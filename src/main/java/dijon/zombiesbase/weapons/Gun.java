package dijon.zombiesbase.weapons;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Gun extends ItemStack {

    int gunVal;
    Color color; //TEMP
    double damage;

    int ammo;
    int ammoMax;
    double fireSpeed;

    public Gun(int gunVal, Color color, double damage, double fireSpeed, int ammoMax){
        super(Material.PRISMARINE_SHARD);
        this.gunVal = gunVal;
        this.color = color;
        this.damage = damage;
        this.fireSpeed = fireSpeed;
        this.ammoMax = ammoMax;
        ammo = ammoMax;
    }

    public void decreaseAmmo(){
        ammo--;
    }
    public void reload(){
        ammo = ammoMax;
    }


    public int getGunVal() {
        return gunVal;
    }
}
