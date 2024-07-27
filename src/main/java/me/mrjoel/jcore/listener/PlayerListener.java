package me.mrjoel.jcore.listener;

import me.mrjoel.jcore.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PlayerListener implements Listener {

    private final Main plugin;

    public PlayerListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        String nick = event.getPlayer().getName();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

        if (!plugin.getConfig().contains("players." + uuid)) {
            plugin.getConfig().set("players." + uuid + ".firstJoin", currentDate);
            plugin.getConfig().set("players." + uuid + ".accountType", event.getPlayer().isOp() ? "Original" : "Pirata");
            plugin.getConfig().set("players." + uuid + ".rank", event.getPlayer().hasPermission("vip") ? "VIP" : "Normal");
        }

        plugin.getConfig().set("players." + uuid + ".lastJoin", currentDate);
        plugin.getConfig().set("players." + uuid + ".nick", nick);
        plugin.saveConfig();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        plugin.getConfig().set("players." + uuid + ".lastJoin", currentDate);
        plugin.saveConfig();
    }
}
