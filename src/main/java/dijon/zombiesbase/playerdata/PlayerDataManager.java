package dijon.zombiesbase.playerdata;

import dijon.zombiesbase.weapons.Gun;
import dijon.zombiesbase.weapons.GunType;
import org.bukkit.entity.Player;

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


}
