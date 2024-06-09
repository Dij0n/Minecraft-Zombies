package dijon.zombiesbase.playerdata;

import dijon.zombiesbase.weapons.Gun;
import dijon.zombiesbase.weapons.GunType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class PlayerDataManager {
    public static HashMap<Player, PlayerData> playerData = new HashMap<>();

    public static void setMainGun(Player p, Gun gun){
        if(playerData.containsKey(p)){
            playerData.get(p).setGun(gun);
        }else{
            PlayerData pd = new PlayerData();
            pd.setGun(gun);
            playerData.put(p, pd);
        }
    }
    public static Gun getMainGun(Player p){
        if(playerData.containsKey(p)){
            return playerData.get(p).getGun();
        }else{
            PlayerData pd = new PlayerData();
            pd.setGun(GunType.NONE);
            playerData.put(p, pd);
            return GunType.NONE;
        }
    }

    public static void setStatus(Player p, Status s){
        if(playerData.containsKey(p)){
            playerData.get(p).setStatus(s);
        }else{
            PlayerData pd = new PlayerData();
            pd.setStatus(s);
            playerData.put(p, pd);
        }
    }
    public static Status getStatus(Player p){
        if(playerData.containsKey(p)){
            return playerData.get(p).getStatus();
        }else{
            PlayerData pd = new PlayerData();
            pd.setStatus(Status.IDLE);
            playerData.put(p, pd);
            return Status.IDLE;
        }
    }






    //HELPERS

    public static Location getGunSmokeLocation(Player p){
        //---Vector magic to place the empty ammo smoke---
        Vector playerDirectionYZeroed = p.getEyeLocation().getDirection().setY(0);
        playerDirectionYZeroed.rotateAroundY(Math.PI/2);
        Vector playerLookPerpAxis = p.getEyeLocation().getDirection().crossProduct(playerDirectionYZeroed);
        Vector rotatedAroundPep = p.getEyeLocation().getDirection().rotateAroundAxis(playerLookPerpAxis, -Math.PI/4);
        rotatedAroundPep.rotateAroundAxis(p.getEyeLocation().getDirection(), Math.PI/9);
        return p.getEyeLocation().add(rotatedAroundPep.multiply(0.7));
        //---Vector magic to place the empty ammo smoke---
    }


}
