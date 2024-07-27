package me.mrjoel.jcore;

import me.mrjoel.jcore.listener.JoinListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class JoinMain extends JavaPlugin {
    private static JoinMain instance;
    private FileConfiguration messagesConfig;

    @Override
    public void onEnable() {
        instance = this;
        loadMessagesConfig();
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    public static JoinMain getInstance() {
        return instance;
    }

    public FileConfiguration getMessagesConfig() {
        return messagesConfig;
    }

    private void loadMessagesConfig() {
        File messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            messagesFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }

        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }
}
