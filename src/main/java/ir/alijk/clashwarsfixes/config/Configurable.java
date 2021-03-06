/*
 * - CREDIT -
 * Taken from MegaCore (https://github.com/Alijkaz/MegaCore)
 */
package ir.alijk.clashwarsfixes.config;

import ir.alijk.clashwarsfixes.ClashWarsFixes;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Main abstract class for configuration files
 *
 */
public abstract  class Configurable {
        private static File file;
        private final String configName;
        private final ClashWarsFixes instance;
        private static FileConfiguration config;

        public Configurable(ClashWarsFixes instance, String configName) {
                this.configName = configName;
                this.instance = instance;
        }

        /**
         * Set-up the whole configuration file ( 1- Create config and write defaults if needed | 2- Load configurations )
         */
        public void setup() {
                try {
                        create();
                        load();
                } catch (IOException|InvalidConfigurationException exception) {
                        exception.printStackTrace();
                }

                init();
        }

        /**
         * You should put your constants here
         */
        public abstract void init();

        /**
         * Load config file as a valid Bukkit Configuration YML file
         *
         * @throws IOException Will be thrown if config file is not readable
         * @throws InvalidConfigurationException Will be thrown if config file is not valid
         */
        public void load() throws IOException, InvalidConfigurationException {
                config = new YamlConfiguration();
                config.load(file);
        }

        /**
         * This method will attempt to create config.yml/messages.yml file with default values
         */
        public void create()
        {
                // Get config file in plugins data folder
                file = new File(instance.getDataFolder(), this.configName);

                // Checking if config exists, if not creating one with default values
                if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        instance.saveResource(this.configName, false);
                }
        }

        public static FileConfiguration getConfig() {
                return config;
        }
}
