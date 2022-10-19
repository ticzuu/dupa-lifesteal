package pl.ticzuu.lifesteal;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class LifeStealPlugin extends JavaPlugin {

    public static final ItemStack HEALTH_ITEM = new ItemStack(Material.RED_DYE);

    static {
        ItemMeta itemMeta = HEALTH_ITEM.getItemMeta();
        itemMeta.setDisplayName(ChatHelper.colored("&cSerce"));
        HEALTH_ITEM.setItemMeta(itemMeta);
    }

    @Override
    public void onEnable() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "health"), HEALTH_ITEM);

        recipe.shape(
                "$@$",
                "@#@",
                "$@$"
        );

        recipe.setIngredient('@', Material.IRON_BLOCK);
        recipe.setIngredient('#', Material.DIAMOND_BLOCK);
        recipe.setIngredient('$', Material.BLAZE_ROD);

        Bukkit.getServer().addRecipe(recipe);

        this.getServer().getPluginManager().registerEvents(new LifeStealHandler(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().removeRecipe(new NamespacedKey(this, "health"));
    }
}
