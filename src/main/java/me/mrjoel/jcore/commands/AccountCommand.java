package me.mrjoel.jcore.commands;

import me.mrjoel.jcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AccountCommand implements CommandExecutor {

    private final Main plugin;

    public AccountCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Apenas jogadores podem usar este comando.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            showAccountInfo(player, player.getUniqueId());
        } else if (args.length == 1 && player.hasPermission("jcore.command.others")) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                showAccountInfo(player, target.getUniqueId());
            } else {
                player.sendMessage(ChatColor.RED + "Jogador não encontrado.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "Uso: /acc <jogador>");
        }
        return true;
    }

    private void showAccountInfo(Player requester, UUID uuid) {
        String firstJoin = plugin.getConfig().getString("players." + uuid + ".firstJoin");
        String lastJoin = plugin.getConfig().getString("players." + uuid + ".lastJoin");
        String nick = plugin.getConfig().getString("players." + uuid + ".nick");
        String accountType = plugin.getConfig().getString("players." + uuid + ".accountType");
        String rank = plugin.getConfig().getString("players." + uuid + ".rank");

        requester.sendMessage(ChatColor.GOLD + " * Informações da conta:");
        requester.sendMessage(ChatColor.GREEN + "Usuário: " + ChatColor.WHITE + nick);
        requester.sendMessage(ChatColor.GREEN + "Sessão: " + ChatColor.WHITE + accountType);
        requester.sendMessage(ChatColor.GREEN + "Data da primeira vez que ingressou: " + ChatColor.WHITE + firstJoin);
        requester.sendMessage(ChatColor.GREEN + "Data da última vez que ingressou: " + ChatColor.WHITE + lastJoin);
        requester.sendMessage(ChatColor.GREEN + "Rank da conta: " + ChatColor.WHITE + rank);
    }
}
