package ir.alijk.clashwarsfixes;

import ir.alijk.clashwarsfixes.config.Config;
import ir.alijk.clashwarsfixes.events.ChestBlockHandler;
import ir.alijk.clashwarsfixes.events.GeneratorHandler;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public final class ClashWarsFixes extends JavaPlugin {
    private static Optional<ClashWarsFixes> instance;

    @Override
    public void onEnable() {
        instance = Optional.of(this);

        new Config(getInstance()).setup();

        getServer().getPluginManager().registerEvents(new GeneratorHandler(), this);
        getServer().getPluginManager().registerEvents(new ChestBlockHandler(), this);

        getServer().getConsoleSender().sendMessage(colorize("&a&lClashWarsFixes &ahas been enabled (Made by Alijk#2910)"));
    }

    @Override
    public void onDisable() {
    }

    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static ClashWarsFixes getInstance() {
        return instance.orElseThrow(() -> new IllegalStateException("ClashWarsFixes plugin instance is not valid"));
    }
}
