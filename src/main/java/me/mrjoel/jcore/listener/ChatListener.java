package me.mrjoel.jcore.listener;

import me.mrjoel.jcore.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatListener implements Listener {

    private final Main plugin;
    private final Map<UUID, Long> chatCooldowns;

    public ChatListener(Main plugin) {
        this.plugin = plugin;
        this.chatCooldowns = new HashMap<>();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!plugin.getConfig().getBoolean("chat.enabled") && !event.getPlayer().hasPermission("jcore.chatbypass")) {
            event.getPlayer().sendMessage(ChatColor.RED + "O bate-papo deste servidor está desabilitado.");
            event.setCancelled(true);
            return;
        }

        UUID playerUUID = event.getPlayer().getUniqueId();
        if (chatCooldowns.containsKey(playerUUID)) {
            long lastChatTime = chatCooldowns.get(playerUUID);
            if (System.currentTimeMillis() - lastChatTime < 2000) {
                event.getPlayer().sendMessage(ChatColor.RED + "Você deve aguardar 2 segundos entre mensagens.");
                event.setCancelled(true);
                return;
            }
        }

        chatCooldowns.put(playerUUID, System.currentTimeMillis());
    }
}
