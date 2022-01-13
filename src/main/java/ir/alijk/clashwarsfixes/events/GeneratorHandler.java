package ir.alijk.clashwarsfixes.events;

import ir.alijk.clashwarsfixes.ClashWarsFixes;
import ir.alijk.clashwarsfixes.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GeneratorHandler implements Listener {
    private final List<Entity> ignoredItems = new ArrayList<>();

    /**
     * Prevent item duplication (Ain't having fun with players duplicating their resources)
     */
    @EventHandler
    public void playerItemDrop(PlayerDropItemEvent e) {
        if (isResource(e.getItemDrop().getItemStack().getType())) {
            ignoredItems.add(e.getItemDrop());
        }
    }

    /**
     * Handle resource drops from generators
     * This method will handle:
     *  - Increasing drop rate
     *  - Limiting drops to specific amount of resources in every forge
     */
    @EventHandler
    public void generatorHandler(ItemSpawnEvent e) {
        // Preventing potential errors on runtime
        if (e.getEntity().getItemStack() == null) return;

        Material itemType = e.getEntity().getItemStack().getType();
        if (!isResource(itemType)) return;

        if (Config.GENERATOR_LIMITER_ENABLED) {
            int amountOfItems = (int) e.getEntity().getWorld().getNearbyEntities(
                                        e.getEntity().getLocation(),
                                        Config.GENERATOR_LIMITER_RADIUS,
                                        Config.GENERATOR_LIMITER_RADIUS,
                                        Config.GENERATOR_LIMITER_RADIUS
                                ).stream().filter(entity -> entity instanceof Item).count();

            if (amountOfItems > Config.GENERATOR_LIMITER_LIMIT)
                e.setCancelled(true);
        }

        if (Config.GENERATOR_BOOSTER_ENABLED) {
            Bukkit.getScheduler().runTaskLater(ClashWarsFixes.getInstance(), () -> {
                if (ignoredItems.contains(e.getEntity())) {
                    ignoredItems.remove(e.getEntity());
                    return;
                }

                for (int i = 0; i < Config.GENERATOR_BOOSTER_DROP_AMOUNT; i++) {
                    ignoredItems.add(e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(itemType)));
                }
            }, Config.GENERATOR_BOOSTER_DELAY * 20L);
        }
    }

    /**
     * Simply checking if the dropped item is a resource (gold or iron)
     * @param item The item that we want to check
     * @return If the item is resource
     */
    private boolean isResource(Material item) {
        return item != Material.IRON_INGOT && item != Material.GOLD_INGOT;
    }
}
