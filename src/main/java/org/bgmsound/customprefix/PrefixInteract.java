package org.bgmsound.customprefix;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import org.bgmsound.bgmlib.ItemBuild;
import org.bgmsound.bgmlib.WarningMessage;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrefixInteract implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(!player.isOp()) {
            WarningMessage.sendWarningMsg(player, "관리자만 사용할 수 있습니다.");
            return false;
        }
        if (args.length == 0) {
            player.sendMessage("§e/커스텀칭호 생성 [이름] : §f칭호 북을 생성합니다§7.");
            player.sendMessage("§e/커스텀칭호 초기화 [플레이어] : §f플레이어의 칭호를 초기화합니다§7.");
            player.sendMessage("§e/커스텀칭호 보기 [플레이어] : §f플레이어의 칭호를 확인합니다§7.");
            return false;
        }
        else if(args[0].equals("생성")) {
            int m = args.length;
            String s = args[1];
            if(args.length>2) {
                for (int i = 2; i < m; i++) {
                    s = s + " " + args[i];
                }
            }
            ItemStack prefixbook = new ItemBuild(Material.BOOK).displayname("&7[ &f" + s + " &7] &f칭호 &7| &e[우클릭]").addLore("&e ◆ &f우클릭하여 칭호를 &7등록&f하세요&7. &7| &e[/칭호] &f &f ").build();
            player.getInventory().addItem(prefixbook);
        }
        return false;
    }
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!(e.getHand() == EquipmentSlot.HAND)) return;
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(item == null || item.getType() != Material.BOOK || item.getItemMeta() == null) return;
        String s = item.getItemMeta().getDisplayName();
        List<String> namesplit = new ArrayList<>();
        namesplit = Arrays.asList(s.split(" §7] §f칭호 "));
        String prefix = namesplit.get(0);
        prefix = prefix.replace("§7[ §f", "");
        prefix = prefix.replace("§7[ ", "");
        Prefix pPrefix;
        if(CustomPrefix.titlemap.get(player) == null) {
            pPrefix = new Prefix();
            CustomPrefix.titlemap.put(player, pPrefix);
        }
        else {
            pPrefix = CustomPrefix.titlemap.get(player);
        }
        if(pPrefix.titlelist.contains(prefix)) {
            WarningMessage.sendWarningMsg(player, "이미 등록한 칭호입니다!");
            return;
        }
        pPrefix.addPrefix(prefix);
        item.setAmount(item.getAmount() - 1);
        player.getInventory().setItemInHand(item);
    }
}
