package pl.ticzuu.lifesteal;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatHelper {

    public static String colored(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static void title(Player player, String title, String subTitle) {
        player.sendTitle(colored(title), colored(subTitle), 20, 50, 25);
    }
}
