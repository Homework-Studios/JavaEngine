package net.rebix.engine.item;

import net.rebix.engine.JavaEngine;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import net.rebix.engine.item.enchant.Enchantment;
import net.rebix.engine.item.modifier.Modifier;
import net.rebix.engine.combat.stats.ItemStats;
import net.rebix.engine.utils.customevents.ItemUseAbilityEvent;
import net.rebix.engine.utils.jsonreader.ItemJsonReader;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.util.*;

public class EItem implements Listener {

    private String name;
    private Integer level;
    private List<String> upgradeItems = new ArrayList<>();
    private List<Material> lvlskins = new ArrayList<>();
    private String id;
    private List<ItemAbility> abilities = new ArrayList<>();
    private Rarity rarity;
    private ItemStack item;
    private ItemStats stats;
    private List<Enchantment> enchantments = new ArrayList<>();
    private List<Modifier> modifiers = new ArrayList<>();
    private String lore = "";

    Integer maxLevel = 0;
    String effectiveName;
    PersistentDataContainer container;
    ItemMeta meta;

    public static HashMap<String,EItem> registered = new HashMap<>();

    public EItem(String id) {
        copy(id);
        meta = item.getItemMeta();
        if(meta == null) {
            System.out.println("meta is null");
            item.setItemMeta(meta);
        }
            container = meta.getPersistentDataContainer();


    }

    public EItem(ItemStack item) {
        meta = item.getItemMeta();
        if(meta == null) {
            System.out.println("meta is null");
            item.setItemMeta(meta);
        }
        container = meta.getPersistentDataContainer();
            this.item = item;
            this.name = meta.getDisplayName();
            this.id = container.get(new NamespacedKey(JavaEngine.plugin, "id"), PersistentDataType.STRING);
            this.level = container.get(new NamespacedKey(JavaEngine.plugin, "level"), PersistentDataType.INTEGER);
            this.upgradeItems = Arrays.asList(container.get(new NamespacedKey(JavaEngine.plugin, "upgradeitems"), PersistentDataType.STRING).split(","));
            this.lore = container.get(new NamespacedKey(JavaEngine.plugin, "lore"), PersistentDataType.STRING);

            for(String skin : Objects.requireNonNull(container.get(new NamespacedKey(JavaEngine.plugin, "lvlskins"), PersistentDataType.STRING)).split(",")) {
                lvlskins.add(Material.valueOf(skin));
            }
            for(String ability : Objects.requireNonNull(container.get(new NamespacedKey(JavaEngine.plugin, "abilities"), PersistentDataType.STRING)).split(",")) {
                abilities.add(ItemAbility.abilities.get(ability));
            }
            this.rarity = Rarity.valueOf(Objects.requireNonNull(container.get(new NamespacedKey(JavaEngine.plugin, "rarity"), PersistentDataType.STRING)));
            for(String enchantment : Objects.requireNonNull(container.get(new NamespacedKey(JavaEngine.plugin, "enchantments"), PersistentDataType.STRING)).split(",")) {
                enchantments.add(Enchantment.getbyString(enchantment));
            }
            for(String modifier : Objects.requireNonNull(container.get(new NamespacedKey(JavaEngine.plugin, "modifiers"), PersistentDataType.STRING)).split(",")) {
                modifiers.add(Modifier.getbyString(modifier));
            }

    }

    public EItem() {
        item = new ItemStack(Material.STONE);
        meta = item.getItemMeta();
        if(meta == null) {
            System.out.println("meta is null");
            item.setItemMeta(meta);
        }
        container = meta.getPersistentDataContainer();
    }

    public EItem(File file) {
        setItem(new ItemStackBuilder(Material.STONE).setName("").build());
        meta = item.getItemMeta();
        if(meta == null) {
            System.out.println("meta is null");
            item.setItemMeta(meta);
        }
        container = meta.getPersistentDataContainer();
        ItemJsonReader reader = new ItemJsonReader(file);
        setId(reader.getId());
        setName(reader.getName());
        setLevel(reader.getLevel());
        setUpgradeItems(reader.upgradeitems());
        setStats(reader.getStats());
        setLvlskins(reader.getLvlSkins());
        setAbilities(reader.getAbilities());
        setRarity(reader.getRarity());
        setEnchantments(reader.getEnchantments());
        setModifiers(reader.getModifiers());
        setLore(reader.getLore());
        meta.setLocalizedName(reader.getLocal());

        this.item = new ItemStackBuilder(lvlskins.get(level), id).setName(name).setGlowing(true).setUnbreakable(true).build();
        item.setItemMeta(meta);
    }

    boolean copy(String id) {
        EItem item = registered.get(id);
        if(item == null) return false;
        copy(item);
        return true;
    }

    boolean copy(ItemStack item) {
        String id = new ItemStackBuilder(item).getID();
        if(id == null) return false;
        return copy(id);
    }

    void copy(EItem item) {
        this.item = item.item;
        this.meta = item.meta;
        this.container = meta.getPersistentDataContainer();
        setLore(item.lore);
        setId(item.getId());
        setName(item.getName());
        setLevel(item.getLevel());
        setUpgradeItems(item.getUpgradeItems());
        setLvlskins(item.getLvlskins());
        setAbilities(item.getAbilities());
        setRarity(item.getRarity());
        setEnchantments(item.getEnchantments());
        setModifiers(item.getModifiers());

    }

    public void updateItem(Inventory inv, Integer slot) {
        updateValues();
        inv.setItem(slot, item);
    }

    List<String> getlorestring() {
        List<String> l = new ArrayList<>();
        String mlore = "";
        for(int i = 0; i < level + 1; i++) {
            mlore += "§7[ ] ";
        }
        for(Modifier m : modifiers) {
            mlore = mlore.replaceFirst("\\[ ] ", m.getLore());
        }
        l.add(mlore);
        l.add("§fRarity: " + getRarity().getColor() + getRarity().name().replace("_", " "));
        l.add("§fLevel: " + (level + 1));
        l.add("");
        Collections.addAll(l, lore.split("\n"));
        l.add("");
        for(ItemAbility a : abilities) {
            if(a != null) {
                l.add("§6§l" + a.getTrigger() + " §fto use §5" + a.getName());
                l.addAll(a.getDescription());
            }
        }
        return l;
    }

    public void updateValues() {
        EItem update = registered.get(id);
        maxLevel = update.getLvlskins().size();
        boolean max = level >= maxLevel - 1;
        setId(update.getId());
        setUpgradeItems(update.getUpgradeItems());
        setLvlskins(update.getLvlskins());
        setAbilities(update.getAbilities());
        if(max) setRarity(Rarity.getNext(update.getRarity()));
        else setRarity(update.getRarity());
        setEnchantments(update.getEnchantments());
        setModifiers(update.getModifiers());
        setStats(update.getStats());
        String lvlsymbol = "♢"+getRarity().getColor();
        meta.removeEnchant(org.bukkit.enchantments.Enchantment.LUCK);
        if(level + 1 >= maxLevel) {
            lvlsymbol = "§6♢";
            meta.addEnchant(org.bukkit.enchantments.Enchantment.LUCK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.setLore(getlorestring());
        effectiveName = getRarity().getColor() + update.getName().replace("{level}", lvlsymbol+(level + 1) + lvlsymbol);
        meta.setDisplayName(effectiveName);
        if(level < lvlskins.size())
            item.setType(lvlskins.get(level));
        item.setItemMeta(meta);
    }

    public void upgrade(int levels) {
       setLevel(getLevel() + levels);
    }

    public void upgrade() {
        setLevel(getLevel() + 1);
    }

    public void max() {
        setLevel(lvlskins.size() - 1);
    }

    @EventHandler
    public void useAbility(ItemUseAbilityEvent event) {
        for(ItemAbility ability : event.getItem().getAbilities()) {
            if(ability != null)
                if (ability.getTrigger().equals(event.getTrigger())) {
                    ability.use(event.getPlayer(), event.getItem());
                return;
            }
        }
    }

    void register() {
        registered.put(id, this);
    }

    public static void registerAll() {
        String parent = JavaEngine.plugin.getDataFolder().getAbsolutePath() + "/ToRegister";
        List<File> files = new ArrayList<>();
        listf(parent, files);
        for(File file : files) {
            EItem item = new EItem(file);
            item.register();
        }
        //updateForPlayers();
    }

    public static void listf(String directoryName, List<File> files) {
        File directory = new File(directoryName);

        // Get all files from a directory.
        File[] fList = directory.listFiles();
        if(fList != null)
            for (File file : fList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    listf(file.getAbsolutePath(), files);
                }
            }
    }

    //Only Getter and setter part can be ignored
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        container.set(new NamespacedKey(JavaEngine.plugin, "id"), PersistentDataType.STRING, id);
        item.setItemMeta(meta);

    }

    public List<ItemAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<ItemAbility> abilities) {
        this.abilities = abilities;
        String out = "";
        for (ItemAbility ability : abilities) {
            if(ability != null)
                out += ability.getId() + ",";
        }
        container.set(new NamespacedKey(JavaEngine.plugin, "abilities"), PersistentDataType.STRING, out);
        item.setItemMeta(meta);
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
        container.set(new NamespacedKey(JavaEngine.plugin, "rarity"), PersistentDataType.STRING, rarity.name());
    }

    public ItemStats getStats() {
        return stats;
    }

    public void setStats(ItemStats stats) {
        container.set(new NamespacedKey(JavaEngine.plugin, "stats"), PersistentDataType.STRING, stats.toString());
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        container.set(new NamespacedKey(JavaEngine.plugin, "level"), PersistentDataType.INTEGER, level);
        this.level = level;
    }

    public List<String> getUpgradeItems() {
        return upgradeItems;
    }

    public void setUpgradeItems(List<String> upgradeItems) {
        container.set(new NamespacedKey(JavaEngine.plugin, "upgradeItems"), PersistentDataType.STRING, String.join(",", upgradeItems));
        this.upgradeItems = upgradeItems;
    }

    public ItemStack getItem() {
        updateValues();
        item.setItemMeta(meta);
        return item.clone();
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public List<Enchantment> getEnchantments() {
        return enchantments;
    }

    public void setEnchantments(List<Enchantment> enchantments) {
        String out = "";
        for (Enchantment enchantment : enchantments) {
            out += enchantment.getString() + ",";
        }
        container.set(new NamespacedKey(JavaEngine.plugin, "enchantments"), PersistentDataType.STRING, out);
        this.enchantments = enchantments;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<Modifier> modifiers) {
        String out = "";
        for (Modifier modifier : modifiers) {
            out += modifier.getString() + ",";
        }
    container.set(new NamespacedKey(JavaEngine.plugin, "modifiers"), PersistentDataType.STRING, out);
        this.modifiers = modifiers;
    }

    public List<Material> getLvlskins() {
        return lvlskins;
    }

    public void setLvlskins(List<Material> lvlskins) {
        String out = "";
        for (Material material : lvlskins) {
            out += material.name() + ",";
        }
        container.set(new NamespacedKey(JavaEngine.plugin, "lvlskins"), PersistentDataType.STRING, out);

        this.lvlskins = lvlskins;
    }


    public void setLore(String lore) {
        container.set(new NamespacedKey(JavaEngine.plugin, "lore"), PersistentDataType.STRING, lore);
        this.lore = lore;
    }

    public static void updateForPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Inventory inv = player.getInventory();
            for (int i = 0; i < inv.getSize(); i++) {
                ItemStack item = inv.getItem(i);
                if(item == null) continue;
                if(item.getType() == Material.AIR) continue;
                if(item.getItemMeta() == null) continue;
                if(item.getItemMeta().getLocalizedName().contains("eitem")) {
                    String id = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "id"), PersistentDataType.STRING);
                    EItem eitem = new EItem(Objects.requireNonNull(player.getInventory().getItem(i)));
                    eitem.updateItem(inv, i);
                }
            }
        }
    }

    public static Boolean isEItem(ItemStack item) {
        if(item == null) return false;
        if(item.getItemMeta() == null) return false;
        return item.getItemMeta().getLocalizedName().contains("eitem");
    }
}
