package com.novinitygames.stringDupersReturn.listener;

import com.novinitygames.stringDupersReturn.StringDupersReturn;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Tripwire;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BlockListeners implements Listener {
    // Called when water flows
    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        // Make sure the block it's heading to that was broken is an attached tripwire
        if (event.getToBlock().getType() == Material.TRIPWIRE) {
            if (event.getToBlock().getBlockData() instanceof Tripwire tripwire) {
                if (tripwire.isAttached()) {
                    // Check if the block that it's coming from is a waterlogged trapdoor
                    Block trapdoor = event.getBlock();
                    if (trapdoor.getType().toString().toUpperCase().contains("TRAPDOOR") && trapdoor.getBlockData() instanceof Waterlogged wl) {
                        if (wl.isWaterlogged()) {
                            // Stop the tripwire from being broken
                            event.setCancelled(true);
                            // Get the minimum and maximum string drops
                            int min = StringDupersReturn.getInstance().getConfig().getInt("minStringDrop");
                            int max = StringDupersReturn.getInstance().getConfig().getInt("maxStringDrop");
                            if (min == 0 && max == 0) return;
                            try {
                                // Spawn a random amount of string within the bounds at the tripwire location
                                event.getToBlock().getWorld().dropItem(event.getToBlock().getLocation(), new ItemStack(Material.STRING, new Random().nextInt(min, max + 1)));
                                // Power it to emulate og string dupers a bit more
                                tripwire.setPowered(true);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        }
    }
}
