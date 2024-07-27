package me.mrjoel.jcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.Plugin;

public class ClearDropsCommand implements CommandExecutor {

    private final Plugin plugin;

    public ClearDropsCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("jcore.command.cleardrops")) {
            sender.sendMessage(ChatColor.RED + "Os itens do chão serão limpos em 5 segundos.");
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
                    if (entity instanceof Item) {
                        entity.remove();
                    }
                }
                Bukkit.broadcastMessage(ChatColor.RED + "Todos os itens do chão foram removidos!");
            }, 100L);
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Você não tem permissão para usar este comando.");
            return true;
        }
    }
}
