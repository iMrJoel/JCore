package me.mrjoel.jcore.commands;

import me.mrjoel.jcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor {

    private final Main plugin;

    public ChatCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Apenas jogadores podem usar este comando.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("jcore.command.chat")) {
            player.sendMessage(ChatColor.RED + "Você não tem permissão para usar este comando.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Uso correto: /chat <on, off>");
            return true;
        }

        if (args[0].equalsIgnoreCase("off")) {
            plugin.getConfig().set("chat.enabled", false);
            plugin.saveConfig();
            Bukkit.broadcastMessage(ChatColor.RED + "O bate-papo deste servidor foi desabilitado.");
        } else if (args[0].equalsIgnoreCase("on")) {
            plugin.getConfig().set("chat.enabled", true);
            plugin.saveConfig();
            Bukkit.broadcastMessage(ChatColor.GREEN + "O bate-papo deste servidor foi habilitado");
        } else {
            player.sendMessage(ChatColor.RED + "Uso correto: /chat <on, off>");
        }

        return true;
    }
}
