package me.mrjoel.jcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Apenas jogadores podem usar este comando.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("jcore.command.fly")) {
            player.sendMessage(ChatColor.RED + "Você não tem permissão para usar este comando.");
            return true;
        }

        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(ChatColor.RED + "Você desativou o modo de voo desativado.");
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(ChatColor.GREEN + "Você ativou o modo de voo.");
        }
        return true;
    }
}
