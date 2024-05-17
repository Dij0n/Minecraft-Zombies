package dijon.zombiesbase.tick;

import dijon.zombiesbase.utility.Raycaster;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TestRunnable extends BukkitRunnable {


    public TestRunnable() {

    }

    @Override
    public void run() {

        for(Player p : Bukkit.getOnlinePlayers()){
            Raycaster ray = new Raycaster(p, 20);
            Particle.DustOptions red = new Particle.DustOptions(Color.RED, 1.0F);
            Particle.DustOptions blue = new Particle.DustOptions(Color.BLUE, 1.0F);
            Particle.DustOptions green = new Particle.DustOptions(Color.LIME, 1.0F);


            if(ray.getBlockFound() != null){
                p.sendMessage("Block Found - " + ray.getBlockFound().getType());
                p.getWorld().spawnParticle(Particle.REDSTONE, ray.getFinalLoc(), 5, red);
            }
            if(ray.getEntities() != null){
                Entity victim = ray.getEntity();
                double distToFloor = victim.getLocation().distance(ray.getFinalLoc());

                if(distToFloor >= 1.64D){
                    p.getWorld().spawnParticle(Particle.REDSTONE, ray.getFinalLoc(), 5, blue);
                    p.sendMessage("Entity Found - " + victim.getType() + " HEADSHOT!!!");
                }else{
                    p.getWorld().spawnParticle(Particle.REDSTONE, ray.getFinalLoc(), 5, green);
                    p.sendMessage("Entity Found - " + victim.getType());
                }
            }

        }

    }
}
