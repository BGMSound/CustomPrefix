package org.bgmsound.customprefix;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class PrefixGUI implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Prefix pPrefix;
        if(CustomPrefix.titlemap.get(player) == null) {
            pPrefix = new Prefix();
            CustomPrefix.titlemap.put(player, pPrefix);
        }
        else {
            pPrefix = CustomPrefix.titlemap.get(player);
        }
        pPrefix.openPrefixGUI((Player) sender, 1);

        return false;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Inventory inv = e.getClickedInventory();
        if(inv == null) return;
        if(e.getCurrentItem() == null) return;
        if (inv.getName().contains("칭호")) {
            e.setCancelled(true);

        }
    }
}
