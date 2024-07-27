package me.mrjoel.jcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Apenas jogadores podem usar este comando.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Uso correto: /gm <0,1,3>");
            return true;
        }

        GameMode targetGameMode;
        String modeName;
        switch (args[0]) {
            case "0":
                targetGameMode = GameMode.SURVIVAL;
                modeName = "SURVIVAL";
                break;
            case "1":
                targetGameMode = GameMode.CREATIVE;
                modeName = "CREATIVE";
                break;
            case "3":
                targetGameMode = GameMode.SPECTATOR;
                modeName = "SPECTATOR";
                break;
            default:
                player.sendMessage(ChatColor.RED + "Modo de jogo inválido. Use 0, 1 ou 3.");
                return true;
        }

        player.setGameMode(targetGameMode);
        player.sendMessage(ChatColor.GREEN + "Você alterou seu modo de jogo para " + modeName);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.hasPermission("jcore.logs")) {
                onlinePlayer.sendMessage(ChatColor.GRAY + player.getName() + " alterou seu modo de jogo para " + modeName);
            }
        }
        return true;
    }
}
