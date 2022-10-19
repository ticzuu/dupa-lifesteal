package pl.ticzuu.lifesteal;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class LifeStealHandler implements Listener {

    @EventHandler
    public void handleDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity(), killer = victim.getKiller();
        AttributeInstance victimHealth = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        boolean shouldLose = victimHealth.getBaseValue() > 3;
        if (shouldLose) {
            victimHealth.setBaseValue(victimHealth.getBaseValue() - 1);
        }
        ChatHelper.title(victim, "&cUmarłeś", shouldLose ? "&7Nie tracisz życ bo masz za mało" : "&7Tracisz &41 &7życie");
        if (killer == null) {
            return;
        }
        AttributeInstance killerHealth = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        boolean shouldAdd = killerHealth.getBaseValue() < 20;
        if (shouldAdd) {
            killerHealth.setBaseValue(killerHealth.getBaseValue() + 1);
        }
        ChatHelper.title(killer, "&aZabiłeś", shouldAdd ? "&7Nie dostajesz życ bo chłop miał za mało" : "&7Dostajesz &21 &7życie");
    }

    @EventHandler
    public void handleInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        ItemStack itemInHand = event.getItem();
        if (itemInHand == null) {
            return;
        }
        if (event.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }
        if (!itemInHand.isSimilar(LifeStealPlugin.HEALTH_ITEM)) {
            return;
        }
        Player player = event.getPlayer();
        AttributeInstance health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (health.getBaseValue() >= 20) {
            player.sendMessage(ChatHelper.colored("&cPosiadasz już 20 życ"));
            return;
        }
        health.setBaseValue(health.getBaseValue() + 1);
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            player.getInventory().setItemInMainHand(null);
        }
    }
}
