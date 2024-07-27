package me.mrjoel.jcore.commands;

import org.bukkit.Bukkit;
import me.mrjoel.jcore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {

    private final Main plugin;

    public InvseeCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem usar este comando.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("jcore.command.invsee")) {
            player.sendMessage("§cVocê não tem permissão para usar este comando.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("§cUso correto: /invsee <jogador>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("§cJogador não encontrado.");
            return true;
        }

        player.openInventory(target.getInventory());
        return true;
    }
}
