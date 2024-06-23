package dijon.zombiesbase.perks;

import dijon.zombiesbase.perks.actions.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PerkType {

    public static Perk JUGGERNOG = new Perk("Juggernog", new JugAction());

    public static Perk SPEEDCOLA = new Perk("Speed Cola", new SpeedAction());

    public static Perk DOUBLETAP = new Perk("Double Tap Root Beer", new DoubleAction());

    public static Perk QUICKREVIVE = new Perk("Quick Revive", new QuickAction());

    public static Perk STAMINUP = new Perk("Stamin-Up Soda", new StamiAction());

    public static Perk PHDFLOPPER = new Perk("PhD Flopper", new PHDAction());

    public static Perk MULEKICK = new Perk("Mule Kick", new MuleAction());

    public static Perk ELECTRICCHERRY = new Perk("Electric Cherry", new ElectricAction());

    public static Perk WIDOWSWINE = new Perk("Widow's Wine", new WidowAction());

}
