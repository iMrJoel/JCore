package me.mrjoel.jcore;

import me.mrjoel.jcore.commands.*;
import me.mrjoel.jcore.listener.*;
import me.mrjoel.jcore.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register commands

        getCommand("cleardrops").setExecutor(new ClearDropsCommand(this));
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("acc").setExecutor(new AccountCommand(this));
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("chat").setExecutor(new ChatCommand(this));
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("invsee").setExecutor(new InvseeCommand(this));
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "§aDependência principal JCore está habilitado. Todas as classes foram carregadas com sucesso!");


        // Register events
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);


        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"§cDependência principal JCore está desativada. Todas as classes foram desativadas com êxito!");
    }
}
