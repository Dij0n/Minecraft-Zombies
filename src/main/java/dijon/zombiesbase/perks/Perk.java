package dijon.zombiesbase.perks;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Perk {

    String name;

    PerkRunnable action;

    public Perk(String name, PerkRunnable action) {
        this.name = name;
        this.action = action;
    }

    public PerkRunnable getAction() {
        return action;
    }

    public String getName() {
        return name;
    }
}
