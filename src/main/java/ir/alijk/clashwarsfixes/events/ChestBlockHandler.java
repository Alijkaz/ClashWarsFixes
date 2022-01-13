package ir.alijk.clashwarsfixes.events;

import ir.alijk.clashwarsfixes.config.Config;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class ChestBlockHandler implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockingChest(BlockPlaceEvent e) {
        Block bottomBlock = e.getBlock().getRelative(BlockFace.DOWN);

        if (bottomBlock == null) return;

        if ((bottomBlock.getType().equals(Material.ENDER_CHEST) && Config.PREVENT_BLOCK_ON_ENDERCHEST)
                || (bottomBlock.getType().equals(Material.CHEST) && Config.PREVENT_BLOCK_ON_CHEST)) {
            e.setCancelled(true);
        }
    }
}
