package tacticalaxis.tc;

import org.bukkit.event.block.*;
import org.bukkit.event.*;
import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

public class TreeHandler implements Listener {
    @EventHandler
    public void breakingBlock(final BlockBreakEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (e.getBlock().getType() != Material.LOG && e.getBlock().getType() != Material.LOG_2) {
            return;
        }
        if (!this.isAxe(e.getPlayer().getItemInHand())) {
            return;
        }
        if (!e.getPlayer().getGameMode().equals((Object) GameMode.SURVIVAL)) {
            return;
        }
        this.breakBlock(e.getBlock(), e.getPlayer());
    }

    public void breakBlock(final Block b, final Player p) {
        b.breakNaturally();
        final Location above = new Location(b.getWorld(), (double) b.getLocation().getBlockX(), (double) (b.getLocation().getBlockY() + 1), (double) b.getLocation().getBlockZ());
        final Block blockAbove = above.getBlock();
        if (blockAbove.getType() == Material.LOG || blockAbove.getType() == Material.LOG_2) {
            this.breakBlock(blockAbove, p);
            p.getItemInHand().setDurability((short) (p.getItemInHand().getDurability() + 1));
            if (p.getItemInHand().getDurability() > p.getItemInHand().getType().getMaxDurability()) {
                p.getInventory().remove(p.getItemInHand());
                p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
            }
        }
    }

    public boolean isAxe(final ItemStack item) {
        return item.getType().equals((Object) Material.WOOD_AXE) || item.getType().equals((Object) Material.STONE_AXE) || item.getType().equals((Object) Material.IRON_AXE) || item.getType().equals((Object) Material.GOLD_AXE) || item.getType().equals((Object) Material.DIAMOND_AXE);
    }
}
