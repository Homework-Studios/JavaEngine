package net.rebix.engine.api.bookmenu;


import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BookBuilder {

    ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
    BookMeta meta = (BookMeta) item.getItemMeta();

    public BookBuilder() {
        meta.setAuthor("");
        meta.setTitle("");

        TextComponent text = new TextComponent("§6§lRebix");
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/me hi"));
        TextComponent text2 = new TextComponent("Weiterer Test");
        text2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/me hi2"));
        meta.spigot().addPage(new BaseComponent[]{text, text2});
        item.setItemMeta(meta);
    }




    public void send(Player player) {
        player.openBook(item);
    }
}
