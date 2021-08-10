package org.bgmsound.customprefix;

import org.bgmsound.bgmlib.ItemBuild;
import org.bgmsound.bgmlib.WarningMessage;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Prefix {
    public static List<String> titlelist = new ArrayList<>();
    public String currentPrefix = "";

    public void addPrefix(String title) {
        titlelist.add(title);
    }

    public static ItemStack glass = new ItemBuild(Material.STAINED_GLASS_PANE).durability((byte) DyeColor.GRAY.ordinal()).displayname("&f").build();
    public static ItemStack pre = new ItemBuild(Material.ARROW).displayname("&7[ &e◀ &7] &f이전 페이지").build();
    public static ItemStack nex = new ItemBuild(Material.ARROW).displayname("&7[ &e▶ &7] &f다음 페이지").build();

    public static void resetPrefix() {
        titlelist.clear();
    }

    public void openPrefixGUI(Player player, Integer page) {
        List<Integer> is = new ArrayList<>();
        int maxpage = ((titlelist.size()) / 14) + 1;
        Inventory inv = Bukkit.createInventory(null, 36, "칭호 (" + page + "/" + maxpage + ")");
        for (int i = 10; i < 17; i++) {
            inv.setItem(i, glass);
            is.add(i);
        }
        for (int i = 19; i < 26; i++) {
            inv.setItem(i, glass);
            is.add(i);
        }
        //%floor(size of {title.%uuid of player%::*}/14)+1%
        int i = titlelist.size();
        ItemStack barrier = new ItemBuild(Material.BARRIER).displayname("&7[ &e● &7] &f칭호 정보").addLore("&f").addLore("&e ◆ &f현재 적용된 칭호 &7: &cX").addLore("&7&o * 클릭하여 현재 적용된 칭호를 해제할 수 있습니다. &f &f ").addLore("&f").addLore(" &7[ &e* &7] &f현재 &6&l" + i + " &f개의 칭호 보유 중").build();
        for (i = 10; i < 17; i++) {
            inv.setItem(i, glass);
        }
        ItemStack prefix;
        String s;
        Material a;
        int i3 = (page - 1) * 14;
        int max = i3 + 13;

        if (titlelist.size() > i3) {
            for (int i2 : is) {
                //if (currentPrefix.equals(titlelist.get(i3))) {
                if(titlelist.get(i3).equals(currentPrefix)) {
                    s = "&e등록 ";
                    a = Material.BOOK;
                } else {
                    s = "&c미등록 ";
                    a = Material.ENCHANTED_BOOK;
                }
                prefix = new ItemBuild(a).displayname("&7[ &f" + titlelist.get(i3) + " &7] &f칭호 &7| " + s + "&f ").build();
                inv.setItem(i2, prefix);
                i3++;
                if (i3 == max) return;
                if (titlelist.size() > i3) return;
            }
        }
        inv.setItem(30, pre);
        inv.setItem(32, nex);
        inv.setItem(31, barrier);
        player.openInventory(inv);
        //
    }
}
