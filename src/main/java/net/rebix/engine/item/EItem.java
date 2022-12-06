package net.rebix.engine.item;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.rebix.engine.EPlayer;
import net.rebix.engine.JavaEngine;
import net.rebix.engine.combat.stats.StatType;
import net.rebix.engine.combat.stats.Stats;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import net.rebix.engine.item.enchant.Enchantment;
import net.rebix.engine.item.modifier.Modifier;
import net.rebix.engine.item.modifier.ModifierAction;
import net.rebix.engine.utils.JsonReader;
import net.rebix.engine.utils.customevents.ItemUseAbilityEvent;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class EItem implements Listener {

    public static HashMap<String, EItem> registered = new HashMap<>();
    Integer maxLevel = 0;
    String effectiveName;
    PersistentDataContainer container;
    ItemMeta meta;
    private String name;
    private Integer level;
    private List<String> upgradeItems = new ArrayList<>();
    private List<Material> lvlskins = new ArrayList<>();
    private String id;
    private List<ItemAbility> abilities = new ArrayList<>();
    private Rarity rarity;
    private ItemStack item;
    private Stats stats;
    private Stats effectiveStats;
    private List<Enchantment> enchantments = new ArrayList<>();
    private List<Modifier> modifiers = new ArrayList<>();
    private String lore = "";
    private UUID owner = UUID.randomUUID();

    public EItem(String id) {
        copy(id);
        meta = item.getItemMeta();
        if (meta == null) {
            System.out.println("meta is null");
            item.setItemMeta(meta);
        }
        container = meta.getPersistentDataContainer();


    }

    public EItem(ItemStack item) {
        meta = item.getItemMeta();
        if (meta == null) {
            System.out.println("meta is null");
            item.setItemMeta(meta);
        }
        container = meta.getPersistentDataContainer();
        meta.setCustomModelData(container.get(new NamespacedKey(JavaEngine.plugin, "mcd"), PersistentDataType.INTEGER));
        this.item = item;
        this.name = meta.getDisplayName();
        this.id = container.get(new NamespacedKey(JavaEngine.plugin, "id"), PersistentDataType.STRING);
        this.level = container.get(new NamespacedKey(JavaEngine.plugin, "level"), PersistentDataType.INTEGER);
        this.upgradeItems = Arrays.asList(container.get(new NamespacedKey(JavaEngine.plugin, "upgradeitems"), PersistentDataType.STRING).split(","));
        this.lore = container.get(new NamespacedKey(JavaEngine.plugin, "lore"), PersistentDataType.STRING);
        String uuid = container.get(new NamespacedKey(JavaEngine.plugin, "owner"), PersistentDataType.STRING);
        this.stats = new Stats().getbyString(Objects.requireNonNull(container.get(new NamespacedKey(JavaEngine.plugin, "stats"), PersistentDataType.STRING)));
        if (uuid != null) this.owner = UUID.fromString(uuid);
        else this.owner = UUID.randomUUID();
        for (String skin : Objects.requireNonNull(container.get(new NamespacedKey(JavaEngine.plugin, "lvlskins"), PersistentDataType.STRING)).split(",")) {
            lvlskins.add(Material.valueOf(skin));
        }
        for (String ability : Objects.requireNonNull(container.get(new NamespacedKey(JavaEngine.plugin, "abilities"), PersistentDataType.STRING)).split(",")) {
            abilities.add(ItemAbility.abilities.get(ability));
        }
        this.rarity = Rarity.valueOf(Objects.requireNonNull(container.get(new NamespacedKey(JavaEngine.plugin, "rarity"), PersistentDataType.STRING)));
        for (String enchantment : Objects.requireNonNull(container.get(new NamespacedKey(JavaEngine.plugin, "enchantments"), PersistentDataType.STRING)).split(",")) {
            enchantments.add(Enchantment.getbyString(enchantment));
        }
        for (String modifier : Objects.requireNonNull(container.get(new NamespacedKey(JavaEngine.plugin, "modifiers"), PersistentDataType.STRING)).split(",")) {
            modifiers.add(Modifier.getbyString(modifier));
        }

    }

    public EItem() {
        item = new ItemStack(Material.STONE);
        meta = item.getItemMeta();
        if (meta == null) {
            System.out.println("meta is null");
            item.setItemMeta(meta);
        }
        container = meta.getPersistentDataContainer();
    }

    public EItem(File file) {
        setItem(new ItemStackBuilder(Material.STONE).setName("").build());
        meta = item.getItemMeta();
        if (meta == null) {
            System.out.println("meta is null");
            item.setItemMeta(meta);
        }
        container = meta.getPersistentDataContainer();
        try {
            JsonObject json = JsonParser.parseReader(new FileReader(file)).getAsJsonObject();

            JsonReader reader = new JsonReader(json);


            setId(file.getName().replace(".json", ""));
            setName(reader.getString("name"));
            setLevel(reader.getInteger("level"));
            setUpgradeItems(Arrays.asList(reader.getString("upgradeitems").split(",")));
            setStats(new Stats().getbyString(reader.getString("stats")));

            List<String> lvlskins1 = Arrays.asList(reader.getString("lvlskins").split(","));
            List<Material> materialList = new ArrayList<>();
            for (int i = 0; i < lvlskins1.size(); i++) {
                if (lvlskins1.get(i).equals("")) {
                    continue;
                }
                materialList.add(Material.valueOf(lvlskins1.get(i)));
            }
            if (materialList.size() == 0) {
                materialList.add(Material.RED_BANNER);
            }
            List<ItemAbility> abilities = new ArrayList<>();
            List<String> abilitiesString = Arrays.asList(reader.getString("abilities").split(","));
            for (int i = 0; i < abilitiesString.size(); i++) {
                abilities.add(ItemAbility.abilities.get(abilitiesString.get(i)));
            }

            setLvlskins(materialList);
            setAbilities(abilities);
            setRarity(Rarity.get(reader.getString("rarity")));

            List<Enchantment> enchantments = new ArrayList<>();
            List<String> enchantmentsString = Arrays.asList(reader.getString("enchantments").split(","));
            for (int i = 0; i < enchantmentsString.size(); i++) {
                enchantments.add(Enchantment.getbyString(enchantmentsString.get(i)));
            }
            setEnchantments(enchantments);

            List<Modifier> modifiers = new ArrayList<>();
            List<String> modifiersString = Arrays.asList(reader.getString("modifiers").split(","));
            for (int i = 0; i < modifiersString.size(); i++) {
                modifiers.add(Modifier.getbyString(modifiersString.get(i)));
            }
            setModifiers(modifiers);
            setLore(reader.getString("lore"));
            meta.setLocalizedName(reader.getString("itemtag"));
            setCustomModelData(reader.getInteger("cmd"));
            setOwner(UUID.randomUUID());

            this.item = new ItemStackBuilder(lvlskins.get(level), id).setName(name).build();
            item.setItemMeta(meta);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static EItem getByString(String s) {
        String[] split = s.split("\\{")[1].split(",");
        EItem out = new EItem(s.split("\\{")[0]);

        return out;
    }

    public static void registerAll() {
        String parent = JavaEngine.plugin.getDataFolder().getAbsolutePath() + "/ToRegister";
        List<File> files = new ArrayList<>();
        listf(parent, files);
        for (File file : files) {
            EItem item = new EItem(file);
            item.register();
        }
        //updateForPlayers();
    }

    public static void listf(String directoryName, List<File> files) {
        File directory = new File(directoryName);

        // Get all files from a directory.
        File[] fList = directory.listFiles();
        if (fList != null)
            for (File file : fList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    listf(file.getAbsolutePath(), files);
                }
            }
    }

    public static void updateForPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Inventory inv = player.getInventory();
            for (int i = 0; i < inv.getSize(); i++) {
                ItemStack item = inv.getItem(i);
                if (item == null) continue;
                if (item.getType() == Material.AIR) continue;
                if (item.getItemMeta() == null) continue;
                if (item.getItemMeta().getLocalizedName().contains("eitem")) {
                    String id = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "id"), PersistentDataType.STRING);
                    EItem eitem = new EItem(Objects.requireNonNull(player.getInventory().getItem(i)));
                    eitem.updateItem(inv, i);
                }
            }
        }
    }

    public static Boolean isEItem(ItemStack item) {
        if (item == null) return false;
        if (item.getItemMeta() == null) return false;
        return item.getItemMeta().getLocalizedName().contains("eitem");
    }

    boolean copy(String id) {
        EItem item = registered.get(id);
        if (item == null) return false;
        copy(item);
        return true;
    }

    boolean copy(ItemStack item) {
        String id = new ItemStackBuilder(item).getID();
        if (id == null) return false;
        return copy(id);
    }

    public JsonObject getJson() {
        JsonObject json = new JsonObject();
        json.addProperty("id", id);
        json.addProperty("level", level);
        return json;
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
        for (int i = 0; i < level; i++) {
            mlore += "§7[ ] ";
        }
        for (Modifier m : modifiers)
            if (m != null)
                mlore = mlore.replaceFirst("\\[ ] ", m.getLore());
        if (!mlore.equals("")) l.add(mlore);

        if (rarity != Rarity.NORMAL)
            l.add("§fRarity: " + getRarity().getColor() + getRarity().name().replace("_", " "));
        if (level != 0)
            l.add("§fLevel: " + level);
        String s = "";
        for (int i = 0; i < StatType.values().length; i++) {
            Double stat = effectiveStats.getStat(StatType.values()[i]);
            if (stat != 0) {
                s += StatType.values()[i].setValue(stat).toString() + ChatColor.WHITE + ", ";
            }
        }
        if (s != "") {
            l.add(ChatColor.WHITE + "Stats: " + s);
        }
        l.add("");
        if (!Objects.equals(lore, "")) {
            Collections.addAll(l, lore.split("\n"));
            l.add("");
        }
        for (ItemAbility a : abilities)
            if (a != null) {
                l.add("§6§l" + a.getTrigger() + " §fto use §5" + a.getName());
                l.addAll(a.getDescription());
            }
        return l;
    }

    public void updateValues() {
        EItem update = registered.get(id);
        maxLevel = update.getLvlskins().size();
        boolean max = level >= maxLevel;
        setUpgradeItems(update.getUpgradeItems());
        setLvlskins(update.getLvlskins());
        setAbilities(update.getAbilities());
        if (max && maxLevel != 0) setRarity(Rarity.getNext(update.getRarity()));
        else setRarity(update.getRarity());
        setEnchantments(update.getEnchantments());
        setModifiers(update.getModifiers());
        setStats(update.getStats());
        String lvlsymbol = "♢" + getRarity().getColor();
        meta.removeEnchant(org.bukkit.enchantments.Enchantment.LUCK);
        if (level + 1 >= maxLevel && maxLevel != 0) {
            lvlsymbol = "§6♢";
            meta.addEnchant(org.bukkit.enchantments.Enchantment.LUCK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.setCustomModelData(update.getCustomModelData());


        Stats add = new Stats();
        Stats generalMultiply = new Stats();
        Stats statMultiply = new Stats();
        effectiveStats = new Stats(stats);
        for (int i = 0; i < modifiers.size(); i++) {
            Modifier mod = modifiers.get(i);
            if (mod == null || i >= level) continue;
            ModifierAction action = mod.getAction();
            switch (action) {
                case Adder:
                    add.add(mod.getStat(), mod.getValue());
                    break;
                case Multiplier:
                    statMultiply.add(mod.getStat(), mod.getValue());
                    break;
                case GeneralMultiplier:
                    generalMultiply.add(mod.getStat(), mod.getValue());
                    break;
                case CustomEffect:
                    //TODO: Implement Custom Modifier Effects
                    throw new NotImplementedException();
            }
        }
        effectiveStats.multiply(statMultiply, true);
        effectiveStats.add(add);
        effectiveStats.multiply(generalMultiply, true);


        meta.setLore(getlorestring());
        effectiveName = getRarity().getColor() + update.getName();
        if (level > 0) effectiveName = effectiveName.replace("{level}", lvlsymbol + (level) + lvlsymbol);
        else effectiveName = effectiveName.replace("{level}", "");
        Player p = getOwner();
        if (p != null) {
            effectiveName.replace("{playername}", ((EPlayer) p).getNametag());
        }
        meta.setDisplayName(effectiveName);
        if (level <= lvlskins.size())
            item.setType(lvlskins.get(level));
        item.setItemMeta(meta);

        
    }

    public Stats getEffectiveStats() {
        return effectiveStats;
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
        for (ItemAbility ability : event.getItem().getAbilities()) {
            if (ability != null)
                if (ability.getTrigger().equals(event.getTrigger())) {
                    ability.use(event.getPlayer(), event.getItem());

                }
        }
    }

    void register() {
        registered.put(id, this);
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

    private Integer getCustomModelData() {
        return container.get(new NamespacedKey(JavaEngine.plugin, "cmd"), PersistentDataType.INTEGER);
    }

    private void setCustomModelData(Integer cmd) {
        container.set(new NamespacedKey(JavaEngine.plugin, "cmd"), PersistentDataType.INTEGER, cmd);
        meta.setCustomModelData(cmd);
        item.setItemMeta(meta);
    }

    public List<ItemAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<ItemAbility> abilities) {
        this.abilities = abilities;
        String out = "";
        for (ItemAbility ability : abilities) {
            if (ability != null)
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

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        container.set(new NamespacedKey(JavaEngine.plugin, "stats"), PersistentDataType.STRING, stats.getString());
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
            if (modifier != null) out += modifier.getString() + ",";
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

    public Player getOwner() {
        List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
        for (int i = 0; i > players.size(); i++) {
            if (players.get(i).getUniqueId() == this.owner) return players.get(i);
        }
        return null;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
        container.set(new NamespacedKey(JavaEngine.plugin, "owner"), PersistentDataType.STRING, owner.toString());
    }

    public void setOwner(Player player) {
        setOwner(player.getUniqueId());
    }


    public void onEntityDealDamage() {

    }

    public void onEntityTakeDamage() {

    }


}
