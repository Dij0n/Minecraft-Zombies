package dijon.zombiesbase.weapons;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;

public class GunType {

    public static Gun[] gunTypes = new Gun[4];

    public static Gun NONE = new Gun(0, 0, 0, 0, 0, Sound.BLOCK_CHAIN_HIT, Particle.REDSTONE, new Particle.DustOptions(Color.BLACK, 1.0F));
    public static Gun RED = new Gun(1, 20, 120, 5, 5, Sound.BLOCK_CHAIN_HIT, Particle.REDSTONE, new Particle.DustOptions(Color.RED, 1.0F));
    public static Gun GREEN = new Gun(2, 120, 540, 3, 10, Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, Particle.REDSTONE, new Particle.DustOptions(Color.GREEN, 1.0F));
    public static Gun BLUE = new Gun(3, 6, 84, 20, 2, Sound.ENTITY_PLAYER_ATTACK_WEAK, Particle.REDSTONE, new Particle.DustOptions(Color.BLUE, 1.0F));

    public GunType(){
        gunTypes[0] = NONE;
        gunTypes[1] = RED;
        gunTypes[2] = GREEN;
        gunTypes[3] = BLUE;
    }

    public static Gun getGun(int index){
        return gunTypes[index];
    }

}
