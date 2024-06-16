package dijon.zombiesbase.playerdata;

import dijon.zombiesbase.shooting.Gun;
import dijon.zombiesbase.shooting.GunType;
import dijon.zombiesbase.utility.PluginGrabber;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class PlayerDataManager {
    private static HashMap<Player, PlayerData> playerData = new HashMap<>();

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
    public static void increasePoints(Player p, int points){
        if(playerData.containsKey(p)){
            playerData.get(p).incPoints(points);
        }else{
            PlayerData pd = new PlayerData();
            playerData.put(p, pd);
        }
    }
    public static void decreasePoints(Player p, int points){
        if(playerData.containsKey(p)){
            playerData.get(p).decPoints(points);
        }else{
            PlayerData pd = new PlayerData();
            playerData.put(p, pd);
        }
    }
    public static int getPoints(Player p){
        if(playerData.containsKey(p)){
            return playerData.get(p).getPoints();
        }else{
            PlayerData pd = new PlayerData();
            playerData.put(p, pd);
            return 0;
        }
    }
    public static void reload(Player p){

        if(getMainGun(p).getAmmo() >= getMainGun(p).getMaxClip()) return;
        if(getStatus(p).equals(Status.RELOADING)) return;

        long reloadTicks = (long) (getMainGun(p).getReloadTime() * 20);
        setStatus(p, Status.RELOADING);

        //PRE-RELOAD EFFECTS

        p.playSound(p, Sound.BLOCK_WOODEN_TRAPDOOR_CLOSE, 10, 0.75f);
        p.getWorld().spawnParticle(getMainGun(p).getParticle(), getGunSmokeLocation(p), 5, new Particle.DustOptions(Color.SILVER, 1.0F));

        //POST-RELOAD EFFECTS

        Bukkit.getScheduler().runTaskLater(PluginGrabber.plugin, () -> {
            p.playSound(p, Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, 10, 0.75f);
            p.getWorld().spawnParticle(getMainGun(p).getParticle(), getGunSmokeLocation(p), 5, new Particle.DustOptions(Color.YELLOW, 1.0F));
            setStatus(p, Status.IDLE);
            getMainGun(p).reload();
        }, reloadTicks);


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
