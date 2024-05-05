package dijon.zombiesbase;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
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


    //These are found using the Raycast() method
    TargetType target;
    Location finalLoc;
    Block blockFound = null;
    Collection<Entity> entities = null;

    public Raycaster(Player p, double maxDist) {
        this.p = p;
        this.initalLoc = p.getEyeLocation();
        this.maxDist = maxDist;

        raycast();
    }

    public void moveForward(){
        initalLoc.add(p.getEyeLocation().getDirection().multiply(0.5));
    }
    public boolean blockCheck(){
        return p.getWorld().getBlockAt(initalLoc).getType() != Material.AIR;
    }
    public boolean entityCheck(){
        entities = p.getWorld().getNearbyEntities(initalLoc, 0.2, 0.2, 0.2);
        entities.remove(p);
        return !entities.isEmpty();
    }

    public void raycast(){
        dist += 0.5;
        if(!(blockCheck() || entityCheck())){
            if(dist >= maxDist){
                target = TargetType.AIR;
                finalLoc = initalLoc;
                return;
            };
            moveForward();
            raycast();
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
