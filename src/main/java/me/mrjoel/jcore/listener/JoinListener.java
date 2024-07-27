package me.mrjoel.jcore.listener;

import me.mrjoel.jcore.JoinMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration config = JoinMain.getInstance().getMessagesConfig();
        String playerName = event.getPlayer().getName();

        config.getConfigurationSection("joinMessages").getKeys(false).forEach(key -> {
            String permission = config.getString("joinMessages." + key + ".permission");
            String message = config.getString("joinMessages." + key + ".message");

            if (event.getPlayer().hasPermission(permission)) {
                event.setJoinMessage(null);
                if (message != null) {
                    message = ChatColor.translateAlternateColorCodes('&', message.replace("{player}", playerName));
                    Bukkit.broadcastMessage(message);
                }
            }
        });
    }
}
