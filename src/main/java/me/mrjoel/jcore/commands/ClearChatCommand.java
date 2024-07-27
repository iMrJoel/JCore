package me.mrjoel.jcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ClearChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (int i = 0; i < 100; i++) {
            Bukkit.broadcastMessage(" ");
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "");
        return true;
    }
}
