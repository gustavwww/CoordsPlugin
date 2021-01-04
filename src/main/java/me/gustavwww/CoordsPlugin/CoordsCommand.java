package me.gustavwww.CoordsPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class CoordsCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public CoordsCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("CoordsPlugin: Command only executable by players.");
            return true;
        }
        Player p = (Player) sender;
        p.openInventory(createInventory());

        return true;
    }

    private Inventory createInventory() {
        int online = Bukkit.getOnlinePlayers().size();
        int invSize = online + (9-(online % 9));

        Inventory inv = Bukkit.createInventory(null, invSize, "Buy Player's Coordinates");

        for (Player p : Bukkit.getOnlinePlayers()) {
            inv.addItem(getPlayerSkull(p));
        }

        return inv;
    }

    private ItemStack getPlayerSkull(Player player) {

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);

        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(player);
        meta.setDisplayName("&a" + player.getName());
        skull.setItemMeta(meta);
        return skull;
    }

}
