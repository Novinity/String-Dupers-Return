package com.novinitymc.stringDupersReturn.listener;

import com.novinitymc.stringDupersReturn.StringDupersReturn;
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
    @EventHandler
    public void onBlockBreak(BlockFromToEvent event) {
        if (event.getToBlock().getType() == Material.TRIPWIRE) {
            if (event.getToBlock().getBlockData() instanceof Tripwire tripwire) {
                if (tripwire.isAttached()) {
                    Block trapdoor = event.getBlock();
                    if (trapdoor.getType().toString().toUpperCase().contains("TRAPDOOR") && trapdoor.getBlockData() instanceof Waterlogged wl) {
                        if (wl.isWaterlogged()) {
                            event.setCancelled(true);
                            int min = StringDupersReturn.getInstance().getConfig().getInt("minStringDrop");
                            int max = StringDupersReturn.getInstance().getConfig().getInt("maxStringDrop");
                            if (min == 0 && max == 0) return;
                            try {
                                event.getToBlock().getWorld().dropItem(event.getToBlock().getLocation(), new ItemStack(Material.STRING, new Random().nextInt(min, max + 1)));
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
