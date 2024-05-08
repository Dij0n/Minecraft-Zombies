package dijon.zombiesbase.commands;

import dijon.zombiesbase.weapons.GunType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class givegun implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(strings.length != 1){
            commandSender.sendMessage(ChatColor.RED + "Incorrect Usage!");
            return true;
        }

        Player p = (Player) commandSender;

        switch (strings[0]){
            case "red":
                p.getInventory().addItem(GunType.REDGUN);
                break;
            case "green":
                p.getInventory().addItem(GunType.GREENGUN);
                break;
            case "blue":
                p.getInventory().addItem(GunType.BLUEGUN);
                break;

        }
        return false;
    }
}
