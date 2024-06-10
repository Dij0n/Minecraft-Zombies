package dijon.zombiesbase.utility;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.Collection;

public class Raycaster {

    enum TargetType{
        BLOCK,
        ENTITY,
        AIR
    }

    //These are Initalized
    Location initalLoc;
    Player p;
    double maxDist;
    double dist = 0;

    double interval;
    Particle particle;
    Particle.DustOptions dust;


    //These are found using the Raycast() method
    TargetType target;
    Location finalLoc;
    Block blockFound = null;
    Collection<Entity> entities = null;

    public Raycaster(Player p, double maxDist) {
        this.p = p;
        this.initalLoc = p.getEyeLocation();
        this.maxDist = maxDist;

        raycast(false);
    }

    public Raycaster(Player p, double maxDist, double interval, Particle particle, Particle.DustOptions dust) {
        this.p = p;
        this.initalLoc = p.getEyeLocation();
        this.maxDist = maxDist;
        this.particle = particle;
        this.dust = dust;
        this.interval = interval;

        raycast(true);
    }

    private void moveForward(){
        initalLoc.add(p.getEyeLocation().getDirection().multiply(0.5));
    }
    private boolean blockCheck(){
        return p.getWorld().getBlockAt(initalLoc).getType() != Material.AIR;
    }
    private boolean entityCheck(){
        entities = p.getWorld().getNearbyEntities(initalLoc, 0.2, 0.2, 0.2);
        entities.remove(p);
        for(Entity e : entities){
            if(!(e instanceof LivingEntity)) entities.remove(e);
        }
        return !entities.isEmpty();
    }

    private void raycast(boolean intervalEnabled){
        dist += 0.5;
        if(dist % interval == 0 && intervalEnabled){
            initalLoc.getWorld().spawnParticle(particle, initalLoc, 5, dust);
        }
        if(!(blockCheck() || entityCheck())){
            if(dist >= maxDist){
                target = TargetType.AIR;
                finalLoc = initalLoc;
                return;
            };
            moveForward();
            raycast(intervalEnabled);
        }else{
            if(blockCheck()){
                blockFound = p.getWorld().getBlockAt(initalLoc);
                target = TargetType.BLOCK;
            }
            if(entityCheck()){
                target = TargetType.ENTITY;
            }
            finalLoc = initalLoc;
        }

    }

    public boolean isHeadshot(){
        Entity victim = getEntity();
        if (victim == null) return false;
        double distToFloor = victim.getLocation().distance(finalLoc);

        if(distToFloor >= 1.64D){
            return true;
        }else{
            return false;
        }
    }

    public TargetType getTarget() {
        return target;
    }
    public Location getFinalLoc() {
        return finalLoc;
    }
    public Block getBlockFound() {
        return blockFound;
    }
    public Collection<Entity> getEntities() {
        if(entities.isEmpty()) return null;
        return entities;
    }
    public Entity getEntity() {
        if(entities.isEmpty()) return null;
        return entities.iterator().next();
    }
}
