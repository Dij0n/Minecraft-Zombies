package dijon.zombiesbase.weapons;
import org.bukkit.Color;

public class GunType {

    public static Gun[] gunTypes = new Gun[4];

    public static Gun NONE = new Gun(0, 0, 0, 0, 0, Color.BLACK);
    public static Gun RED = new Gun(1, 20, 120, 5, 5, Color.RED);
    public static Gun GREEN = new Gun(2, 120, 540, 3, 10, Color.LIME);
    public static Gun BLUE = new Gun(3, 6, 84, 20, 2, Color.BLUE);

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
