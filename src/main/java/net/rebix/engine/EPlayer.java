package net.rebix.engine;

import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetSocketAddress;
import java.util.*;


public class EPlayer implements Player {

    public static HashMap<String, EPlayer> players = new HashMap<>();
    Player player;
    Integer maxHealth = 500;
    Integer health = maxHealth;

    public EPlayer(Player player) {
        this.player = player;
        players.put(player.getUniqueId().toString(), this);
        player.setAllowFlight(true);
        player.setMaximumNoDamageTicks(0);
        convertHealth();
    }

    public static EPlayer get(Player player) {
        if (players.containsKey(player.getUniqueId().toString())) {
            return players.get(player.getUniqueId().toString());
        } else {
            return new EPlayer(player);
        }
    }


    public double getCHealth() {
        return health;
    }

    public void setCHealth(Integer health) {
        this.health = health;
        convertHealth();
    }

    public void addHealth(Integer health) {
        this.health += health;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
        convertHealth();
    }

    public void convertHealth() {
        char[] chars = health.toString().toCharArray();
        if (health < 1) {
            respawn();
            return;
        }
        Integer redHealth = 20;
        Integer maxRedHealth = 20;

        if (health < 500)
            redHealth = health / 25;
        if (maxHealth < 500)
            maxRedHealth = maxHealth / 25;
        Integer hp = health - 500;
        Integer goldHealth = 0;
        //calculatingGold
        if (hp > 0) goldHealth = hp / 100;
        player.setHealthScale(maxRedHealth);
        player.setHealth(redHealth);
        player.setAbsorptionAmount(goldHealth);

    }

    public void resetMaxHealth() {

    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
        convertHealth();
    }

    public void setMaxHealth(double v) {

    }

    public void damage(double v) {

    }

    public void damage(double v, @Nullable Entity entity) {

    }

    public void respawn() {
        setHealth(maxHealth);
    }


    //region vanilla methods
    public boolean isOnline() {
        return player.isOnline();
    }

    @NotNull
    public PlayerProfile getPlayerProfile() {
        return player.getPlayerProfile();
    }

    public boolean isBanned() {
        return player.isBanned();
    }

    public boolean isWhitelisted() {
        return player.isWhitelisted();
    }

    public void setWhitelisted(boolean b) {
        player.setWhitelisted(b);
    }

    public Player getPlayer() {
        return player;
    }

    public long getFirstPlayed() {
        return player.getFirstPlayed();
    }

    public long getLastPlayed() {
        return player.getLastPlayed();
    }

    public boolean hasPlayedBefore() {
        return player.hasPlayedBefore();
    }


    public double getHealth() {
        return player.getHealth();
    }

    public void setHealth(double v) {
        player.setHealth(v);
    }

    public double getAbsorptionAmount() {
        return player.getAbsorptionAmount();
    }

    public void setAbsorptionAmount(double v) {
        player.setAbsorptionAmount(v);
    }

    @NotNull
    public String getDisplayName() {
        return player.getDisplayName();
    }

    public void setDisplayName(@Nullable String s) {
        player.setDisplayName(s);
    }

    @NotNull
    public String getPlayerListName() {
        return player.getPlayerListName();
    }

    public void setPlayerListName(@Nullable String s) {
        player.setPlayerListName(s);
    }

    @Nullable
    public String getPlayerListHeader() {
        return player.getPlayerListHeader();
    }

    public void setPlayerListHeader(@Nullable String s) {
        player.setPlayerListHeader(s);
    }

    @Nullable
    public String getPlayerListFooter() {
        return player.getPlayerListFooter();
    }

    public void setPlayerListFooter(@Nullable String s) {
        player.setPlayerListFooter(s);
    }

    public void setPlayerListHeaderFooter(@Nullable String s, @Nullable String s1) {
        player.setPlayerListHeaderFooter(s, s1);
    }

    @NotNull
    public Location getCompassTarget() {
        return player.getCompassTarget();
    }

    public void setCompassTarget(@NotNull Location location) {
        player.setCompassTarget(location);
    }

    @Nullable
    public InetSocketAddress getAddress() {
        return player.getAddress();
    }

    public boolean isConversing() {
        return player.isConversing();
    }

    public void acceptConversationInput(@NotNull String s) {
        player.acceptConversationInput(s);
    }

    public boolean beginConversation(@NotNull Conversation conversation) {
        return player.beginConversation(conversation);
    }

    public void abandonConversation(@NotNull Conversation conversation) {
        player.abandonConversation(conversation);
    }

    public void abandonConversation(@NotNull Conversation conversation, @NotNull ConversationAbandonedEvent conversationAbandonedEvent) {
        player.abandonConversation(conversation, conversationAbandonedEvent);
    }

    public void sendRawMessage(@NotNull String s) {
        player.sendRawMessage(s);
    }

    public void sendRawMessage(@Nullable UUID uuid, @NotNull String s) {
        player.sendRawMessage(uuid, s);
    }

    public void kickPlayer(@Nullable String s) {
        player.kickPlayer(s);
    }

    public void chat(@NotNull String s) {
        player.chat(s);
    }

    public boolean performCommand(@NotNull String s) {
        return player.performCommand(s);
    }

    @NotNull
    public Location getLocation() {
        return player.getLocation();
    }

    @Nullable
    public Location getLocation(@Nullable Location location) {
        return player.getLocation(location);
    }

    @NotNull
    public Vector getVelocity() {
        return player.getVelocity();
    }

    public void setVelocity(@NotNull Vector vector) {
        player.setVelocity(vector);
    }

    public double getHeight() {
        return player.getHeight();
    }

    public double getWidth() {
        return player.getWidth();
    }

    @NotNull
    public BoundingBox getBoundingBox() {
        return player.getBoundingBox();
    }

    public boolean isOnGround() {
        return player.isOnGround();
    }

    public boolean isInWater() {
        return player.isInWater();
    }

    @NotNull
    public World getWorld() {
        return player.getWorld();
    }

    public void setRotation(float v, float v1) {
        player.setRotation(v, v1);
    }

    public boolean teleport(@NotNull Location location) {
        return player.teleport(location);
    }

    public boolean teleport(@NotNull Location location, @NotNull PlayerTeleportEvent.TeleportCause teleportCause) {
        return player.teleport(location, teleportCause);
    }

    public boolean teleport(@NotNull Entity entity) {
        return player.teleport(entity);
    }

    public boolean teleport(@NotNull Entity entity, @NotNull PlayerTeleportEvent.TeleportCause teleportCause) {
        return player.teleport(entity, teleportCause);
    }

    @NotNull
    public List<Entity> getNearbyEntities(double v, double v1, double v2) {
        return player.getNearbyEntities(v, v1, v2);
    }

    public int getEntityId() {
        return player.getEntityId();
    }

    public int getFireTicks() {
        return player.getFireTicks();
    }

    public void setFireTicks(int i) {
        player.setFireTicks(i);
    }

    public int getMaxFireTicks() {
        return player.getMaxFireTicks();
    }

    public boolean isVisualFire() {
        return player.isVisualFire();
    }

    public void setVisualFire(boolean b) {
        player.setVisualFire(b);
    }

    public int getFreezeTicks() {
        return player.getFreezeTicks();
    }

    public void setFreezeTicks(int i) {
        player.setFreezeTicks(i);
    }

    public int getMaxFreezeTicks() {
        return player.getMaxFreezeTicks();
    }

    public boolean isFrozen() {
        return player.isFrozen();
    }

    public void remove() {
        player.remove();
    }

    public boolean isDead() {
        return player.isDead();
    }

    public boolean isValid() {
        return player.isValid();
    }

    public void sendMessage(@NotNull String s) {
        player.sendMessage(s);
    }

    public void sendMessage(@NotNull String... strings) {
        player.sendMessage(strings);
    }

    public void sendMessage(@Nullable UUID uuid, @NotNull String s) {
        player.sendMessage(uuid, s);
    }

    public void sendMessage(@Nullable UUID uuid, @NotNull String... strings) {
        player.sendMessage(uuid, strings);
    }

    @NotNull
    public Server getServer() {
        return player.getServer();
    }

    public boolean isPersistent() {
        return player.isPersistent();
    }

    public void setPersistent(boolean b) {
        player.setPersistent(b);
    }

    @Nullable
    public Entity getPassenger() {
        return player.getPassengers().isEmpty() ? null : player.getPassengers().get(0);
    }

    public boolean setPassenger(@NotNull Entity entity) {
        return player.addPassenger(entity);
    }

    @NotNull
    public List<Entity> getPassengers() {
        return player.getPassengers();
    }

    public boolean addPassenger(@NotNull Entity entity) {
        return player.addPassenger(entity);
    }

    public boolean removePassenger(@NotNull Entity entity) {
        return player.removePassenger(entity);
    }

    public boolean isEmpty() {
        return player.isEmpty();
    }

    public boolean eject() {
        return player.eject();
    }

    public float getFallDistance() {
        return player.getFallDistance();
    }

    public void setFallDistance(float v) {
        player.setFallDistance(v);
    }

    @Nullable
    public EntityDamageEvent getLastDamageCause() {
        return player.getLastDamageCause();
    }

    public void setLastDamageCause(@Nullable EntityDamageEvent entityDamageEvent) {
        player.setLastDamageCause(entityDamageEvent);
    }

    @NotNull
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    public int getTicksLived() {
        return player.getTicksLived();
    }

    public void setTicksLived(int i) {
        player.setTicksLived(i);
    }

    public void playEffect(@NotNull EntityEffect entityEffect) {
        player.playEffect(entityEffect);
    }

    @NotNull
    public EntityType getType() {
        return player.getType();
    }

    public boolean isInsideVehicle() {
        return player.isInsideVehicle();
    }

    public boolean leaveVehicle() {
        return player.leaveVehicle();
    }

    @Nullable
    public Entity getVehicle() {
        return player.getVehicle();
    }

    public boolean isCustomNameVisible() {
        return player.isCustomNameVisible();
    }

    public void setCustomNameVisible(boolean b) {
        player.setCustomNameVisible(b);
    }

    public boolean isGlowing() {
        return player.isGlowing();
    }

    public void setGlowing(boolean b) {
        player.setGlowing(b);
    }

    public boolean isInvulnerable() {
        return player.isInvulnerable();
    }

    public void setInvulnerable(boolean b) {
        player.setInvulnerable(b);
    }

    public boolean isSilent() {
        return player.isSilent();
    }

    public void setSilent(boolean b) {
        player.setSilent(b);
    }

    public boolean hasGravity() {
        return player.hasGravity();
    }

    public void setGravity(boolean b) {
        player.setGravity(b);
    }

    public int getPortalCooldown() {
        return player.getPortalCooldown();
    }

    public void setPortalCooldown(int i) {
        player.setPortalCooldown(i);
    }

    @NotNull
    public Set<String> getScoreboardTags() {
        return player.getScoreboardTags();
    }

    public boolean addScoreboardTag(@NotNull String s) {
        return player.addScoreboardTag(s);
    }

    public boolean removeScoreboardTag(@NotNull String s) {
        return player.removeScoreboardTag(s);
    }

    @NotNull
    public PistonMoveReaction getPistonMoveReaction() {
        return player.getPistonMoveReaction();
    }

    @NotNull
    public BlockFace getFacing() {
        return player.getFacing();
    }

    @NotNull
    public Pose getPose() {
        return player.getPose();
    }

    @NotNull
    public SpawnCategory getSpawnCategory() {
        return player.getSpawnCategory();
    }

    public boolean isSneaking() {
        return player.isSneaking();
    }

    public void setSneaking(boolean b) {
        player.setSneaking(b);
    }

    public boolean isSprinting() {
        return player.isSprinting();
    }

    public void setSprinting(boolean b) {
        player.setSprinting(b);
    }

    public void saveData() {
        player.saveData();
    }

    public void loadData() {
        player.loadData();
    }

    public boolean isSleepingIgnored() {
        return player.isSleepingIgnored();
    }

    public void setSleepingIgnored(boolean b) {
        player.setSleepingIgnored(b);
    }

    @Nullable
    public Location getBedSpawnLocation() {
        return player.getBedSpawnLocation();
    }

    public void setBedSpawnLocation(@Nullable Location location) {
        player.setBedSpawnLocation(location);
    }

    public void incrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        player.incrementStatistic(statistic);
    }

    public void decrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        player.decrementStatistic(statistic);
    }

    public void incrementStatistic(@NotNull Statistic statistic, int i) throws IllegalArgumentException {
        player.incrementStatistic(statistic, i);
    }

    public void decrementStatistic(@NotNull Statistic statistic, int i) throws IllegalArgumentException {
        player.decrementStatistic(statistic, i);
    }

    public void setStatistic(@NotNull Statistic statistic, int i) throws IllegalArgumentException {
        player.setStatistic(statistic, i);
    }

    public int getStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        return player.getStatistic(statistic);
    }

    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        player.incrementStatistic(statistic, material);
    }

    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        player.decrementStatistic(statistic, material);
    }

    public int getStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        return player.getStatistic(statistic, material);
    }

    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int i) throws IllegalArgumentException {
        player.incrementStatistic(statistic, material, i);
    }

    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int i) throws IllegalArgumentException {
        player.decrementStatistic(statistic, material, i);
    }

    public void setStatistic(@NotNull Statistic statistic, @NotNull Material material, int i) throws IllegalArgumentException {
        player.setStatistic(statistic, material, i);
    }

    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        player.incrementStatistic(statistic, entityType);
    }

    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        player.decrementStatistic(statistic, entityType);
    }

    public int getStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        return player.getStatistic(statistic, entityType);
    }

    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int i) throws IllegalArgumentException {
        player.incrementStatistic(statistic, entityType, i);
    }

    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int i) {
        player.decrementStatistic(statistic, entityType, i);
    }

    public void setStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int i) {
        player.setStatistic(statistic, entityType, i);
    }

    public void setBedSpawnLocation(@Nullable Location location, boolean b) {
        player.setBedSpawnLocation(location, b);
    }

    public void playNote(@NotNull Location location, byte b, byte b1) {
        player.playNote(location, b, b1);
    }

    public void playNote(@NotNull Location location, @NotNull Instrument instrument, @NotNull Note note) {
        player.playNote(location, instrument, note);
    }

    public void playSound(@NotNull Location location, @NotNull Sound sound, float v, float v1) {
        player.playSound(location, sound, v, v1);
    }

    public void playSound(@NotNull Location location, @NotNull String s, float v, float v1) {
        player.playSound(location, s, v, v1);
    }

    public void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory soundCategory, float v, float v1) {
        player.playSound(location, sound, soundCategory, v, v1);
    }

    public void playSound(@NotNull Location location, @NotNull String s, @NotNull SoundCategory soundCategory, float v, float v1) {
        player.playSound(location, s, soundCategory, v, v1);
    }

    public void playSound(@NotNull Entity entity, @NotNull Sound sound, float v, float v1) {
        player.playSound(entity, sound, v, v1);
    }

    public void playSound(@NotNull Entity entity, @NotNull Sound sound, @NotNull SoundCategory soundCategory, float v, float v1) {
        player.playSound(entity, sound, soundCategory, v, v1);
    }

    public void stopSound(@NotNull Sound sound) {
        player.stopSound(sound);
    }

    public void stopSound(@NotNull String s) {
        player.stopSound(s);
    }

    public void stopSound(@NotNull Sound sound, @Nullable SoundCategory soundCategory) {
        player.stopSound(sound, soundCategory);
    }

    public void stopSound(@NotNull String s, @Nullable SoundCategory soundCategory) {
        player.stopSound(s, soundCategory);
    }

    public void stopAllSounds() {
        player.stopAllSounds();
    }

    public void playEffect(@NotNull Location location, @NotNull Effect effect, int i) {
        player.playEffect(location, effect, i);
    }

    public <T> void playEffect(@NotNull Location location, @NotNull Effect effect, @Nullable T t) {
        player.playEffect(location, effect, t);
    }

    public boolean breakBlock(@NotNull Block block) {
        return player.breakBlock(block);
    }

    public void sendBlockChange(@NotNull Location location, @NotNull Material material, byte b) {
        player.sendBlockChange(location, material, b);
    }

    public void sendBlockChange(@NotNull Location location, @NotNull BlockData blockData) {
        player.sendBlockChange(location, blockData);
    }

    public void sendBlockDamage(@NotNull Location location, float v) {
        player.sendBlockDamage(location, v);
    }

    public void sendEquipmentChange(@NotNull LivingEntity livingEntity, @NotNull EquipmentSlot equipmentSlot, @NotNull ItemStack itemStack) {
        player.sendEquipmentChange(livingEntity, equipmentSlot, itemStack);
    }

    public void sendSignChange(@NotNull Location location, @Nullable String[] strings) throws IllegalArgumentException {
        player.sendSignChange(location, strings);
    }

    public void sendSignChange(@NotNull Location location, @Nullable String[] strings, @NotNull DyeColor dyeColor) throws IllegalArgumentException {
        player.sendSignChange(location, strings, dyeColor);
    }

    public void sendSignChange(@NotNull Location location, @Nullable String[] strings, @NotNull DyeColor dyeColor, boolean b) throws IllegalArgumentException {
        player.sendSignChange(location, strings, dyeColor, b);
    }

    public void sendMap(@NotNull MapView mapView) {
        player.sendMap(mapView);
    }

    public void updateInventory() {
        player.updateInventory();
    }

    @Nullable
    public GameMode getPreviousGameMode() {
        return player.getPreviousGameMode();
    }

    public void setPlayerTime(long l, boolean b) {
        player.setPlayerTime(l, b);
    }

    public long getPlayerTime() {
        return player.getPlayerTime();
    }

    public long getPlayerTimeOffset() {
        return player.getPlayerTimeOffset();
    }

    public boolean isPlayerTimeRelative() {
        return player.isPlayerTimeRelative();
    }

    public void resetPlayerTime() {
        player.resetPlayerTime();
    }

    @Nullable
    public WeatherType getPlayerWeather() {
        return player.getPlayerWeather();
    }

    public void setPlayerWeather(@NotNull WeatherType weatherType) {
        player.setPlayerWeather(weatherType);
    }

    public void resetPlayerWeather() {
        player.resetPlayerWeather();
    }

    public void giveExp(int i) {
        player.giveExp(i);
    }

    public void giveExpLevels(int i) {
        player.giveExpLevels(i);
    }

    public float getExp() {
        return player.getExp();
    }

    public void setExp(float v) {
        player.setExp(v);
    }

    public int getLevel() {
        return player.getLevel();
    }

    public void setLevel(int i) {
        player.setLevel(i);
    }

    public int getTotalExperience() {
        return player.getTotalExperience();
    }

    public void setTotalExperience(int i) {
        player.setTotalExperience(i);
    }

    public void sendExperienceChange(float v) {
        player.sendExperienceChange(v);
    }

    public void sendExperienceChange(float v, int i) {
        player.sendExperienceChange(v, i);
    }

    public boolean getAllowFlight() {
        return player.getAllowFlight();
    }

    public void setAllowFlight(boolean b) {
        player.setAllowFlight(b);
    }

    public void hidePlayer(@NotNull Player player) {
        this.player.hidePlayer(player);
    }

    public void hidePlayer(@NotNull Plugin plugin, @NotNull Player player) {
        this.player.hidePlayer(plugin, player);
    }

    public void showPlayer(@NotNull Player player) {
        this.player.showPlayer(player);
    }

    public void showPlayer(@NotNull Plugin plugin, @NotNull Player player) {
        this.player.showPlayer(plugin, player);
    }

    public boolean canSee(@NotNull Player player) {
        return this.player.canSee(player);
    }

    public void hideEntity(@NotNull Plugin plugin, @NotNull Entity entity) {
        this.player.hideEntity(plugin, entity);
    }

    public void showEntity(@NotNull Plugin plugin, @NotNull Entity entity) {
        this.player.showEntity(plugin, entity);
    }

    public boolean canSee(@NotNull Entity entity) {
        return this.player.canSee(entity);
    }

    public boolean isFlying() {
        return player.isFlying();
    }

    public void setFlying(boolean b) {
        player.setFlying(b);
    }

    public float getFlySpeed() {
        return player.getFlySpeed();
    }

    public void setFlySpeed(float v) throws IllegalArgumentException {
        player.setFlySpeed(v);
    }

    public float getWalkSpeed() {
        return player.getWalkSpeed();
    }

    public void setWalkSpeed(float v) throws IllegalArgumentException {
        player.setWalkSpeed(v);
    }

    public void setTexturePack(@NotNull String s) {
        player.setTexturePack(s);
    }

    public void setResourcePack(@NotNull String s) {
        player.setResourcePack(s);
    }

    public void setResourcePack(@NotNull String s, @Nullable byte[] bytes) {
        player.setResourcePack(s, bytes);
    }

    public void setResourcePack(@NotNull String s, @Nullable byte[] bytes, @Nullable String s1) {
        player.setResourcePack(s, bytes, s1);
    }

    public void setResourcePack(@NotNull String s, @Nullable byte[] bytes, boolean b) {
        player.setResourcePack(s, bytes, b);
    }

    public void setResourcePack(@NotNull String s, @Nullable byte[] bytes, @Nullable String s1, boolean b) {
        player.setResourcePack(s, bytes, s1, b);
    }

    @NotNull
    public Scoreboard getScoreboard() {
        return player.getScoreboard();
    }


    public void setScoreboard(@NotNull Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        player.setScoreboard(scoreboard);
    }

    @Nullable
    public WorldBorder getWorldBorder() {
        return player.getWorldBorder();
    }

    public void setWorldBorder(@Nullable WorldBorder worldBorder) {
        player.setWorldBorder(worldBorder);
    }

    public boolean isHealthScaled() {
        return player.isHealthScaled();
    }

    public void setHealthScaled(boolean b) {
        player.setHealthScaled(b);
    }

    public double getHealthScale() {
        return player.getHealthScale();
    }

    public void setHealthScale(double v) throws IllegalArgumentException {
        player.setHealthScale(v);
    }

    @Nullable
    public Entity getSpectatorTarget() {
        return player.getSpectatorTarget();
    }

    public void setSpectatorTarget(@Nullable Entity entity) {
        player.setSpectatorTarget(entity);
    }

    public void sendTitle(@Nullable String s, @Nullable String s1) {
        player.sendTitle(s, s1);
    }

    public void sendTitle(@Nullable String s, @Nullable String s1, int i, int i1, int i2) {
        player.sendTitle(s, s1, i, i1, i2);
    }

    public void resetTitle() {
        player.resetTitle();
    }

    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i) {
        player.spawnParticle(particle, location, i);
    }

    public void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i) {
        player.spawnParticle(particle, v, v1, v2, i);
    }

    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, @Nullable T t) {
        player.spawnParticle(particle, location, i, t);
    }

    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, @Nullable T t) {
        player.spawnParticle(particle, v, v1, v2, i, t);
    }

    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2) {
        player.spawnParticle(particle, location, i, v, v1, v2);
    }

    public void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5) {
        player.spawnParticle(particle, v, v1, v2, i, v3, v4, v5);
    }

    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, @Nullable T t) {
        player.spawnParticle(particle, location, i, v, v1, v2, t);
    }

    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, @Nullable T t) {
        player.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, t);
    }

    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, double v3) {
        player.spawnParticle(particle, location, i, v, v1, v2, v3);
    }

    public void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6) {
        player.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6);
    }

    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, double v3, @Nullable T t) {
        player.spawnParticle(particle, location, i, v, v1, v2, v3, t);
    }

    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, @Nullable T t) {
        player.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6, t);
    }

    @NotNull
    public AdvancementProgress getAdvancementProgress(@NotNull Advancement advancement) {
        return player.getAdvancementProgress(advancement);
    }

    public int getClientViewDistance() {
        return player.getClientViewDistance();
    }

    public int getPing() {
        return player.getPing();
    }

    @NotNull
    public String getLocale() {
        return player.getLocale();
    }

    public void updateCommands() {
        player.updateCommands();
    }

    public void openBook(@NotNull ItemStack itemStack) {
        player.openBook(itemStack);
    }

    public void openSign(@NotNull Sign sign) {
        player.openSign(sign);
    }

    public void showDemoScreen() {
        player.showDemoScreen();
    }

    public boolean isAllowingServerListings() {
        return player.isAllowingServerListings();
    }

    @NotNull
    public Spigot spigot() {
        return player.spigot();
    }

    @NotNull
    public Map<String, Object> serialize() {
        return player.serialize();
    }

    @NotNull
    public String getName() {
        return player.getName();
    }

    @NotNull
    public PlayerInventory getInventory() {
        return player.getInventory();
    }

    @NotNull
    public Inventory getEnderChest() {
        return player.getEnderChest();
    }

    @NotNull
    public MainHand getMainHand() {
        return player.getMainHand();
    }

    public boolean setWindowProperty(@NotNull InventoryView.Property property, int i) {
        return player.setWindowProperty(property, i);
    }

    @NotNull
    public InventoryView getOpenInventory() {
        return player.getOpenInventory();
    }

    @Nullable
    public InventoryView openInventory(@NotNull Inventory inventory) {
        return player.openInventory(inventory);
    }

    @Nullable
    public InventoryView openWorkbench(@Nullable Location location, boolean b) {
        return player.openWorkbench(location, b);
    }

    @Nullable
    public InventoryView openEnchanting(@Nullable Location location, boolean b) {
        return player.openEnchanting(location, b);
    }

    public void openInventory(@NotNull InventoryView inventoryView) {
        player.openInventory(inventoryView);
    }

    @Nullable
    public InventoryView openMerchant(@NotNull Villager villager, boolean b) {
        return player.openMerchant(villager, b);
    }

    @Nullable
    public InventoryView openMerchant(@NotNull Merchant merchant, boolean b) {
        return player.openMerchant(merchant, b);
    }

    public void closeInventory() {
        player.closeInventory();
    }

    @NotNull
    public ItemStack getItemInHand() {
        return Objects.requireNonNull(player.getItemInUse());
    }

    public void setItemInHand(@Nullable ItemStack itemStack) {
        player.setItemInHand(itemStack);
    }

    @NotNull
    public ItemStack getItemOnCursor() {
        return player.getItemOnCursor();
    }

    public void setItemOnCursor(@Nullable ItemStack itemStack) {
        player.setItemOnCursor(itemStack);
    }

    public boolean hasCooldown(@NotNull Material material) {
        return player.hasCooldown(material);
    }

    public int getCooldown(@NotNull Material material) {
        return player.getCooldown(material);
    }

    public void setCooldown(@NotNull Material material, int i) {
        player.setCooldown(material, i);
    }

    public int getSleepTicks() {
        return player.getSleepTicks();
    }

    public boolean sleep(@NotNull Location location, boolean b) {
        return player.sleep(location, b);
    }

    public void wakeup(boolean b) {
        player.wakeup(b);
    }

    @NotNull
    public Location getBedLocation() {
        return player.getBedLocation();
    }

    @NotNull
    public GameMode getGameMode() {
        return player.getGameMode();
    }

    public void setGameMode(@NotNull GameMode gameMode) {
        player.setGameMode(gameMode);
    }

    public boolean isBlocking() {
        return player.isBlocking();
    }

    public boolean isHandRaised() {
        return player.isHandRaised();
    }

    @Nullable
    public ItemStack getItemInUse() {
        return player.getItemInUse();
    }

    public int getExpToLevel() {
        return player.getExpToLevel();
    }

    public float getAttackCooldown() {
        return player.getAttackCooldown();
    }

    public boolean discoverRecipe(@NotNull NamespacedKey namespacedKey) {
        return player.discoverRecipe(namespacedKey);
    }

    public int discoverRecipes(@NotNull Collection<NamespacedKey> collection) {
        return player.discoverRecipes(collection);
    }

    public boolean undiscoverRecipe(@NotNull NamespacedKey namespacedKey) {
        return player.undiscoverRecipe(namespacedKey);
    }

    public int undiscoverRecipes(@NotNull Collection<NamespacedKey> collection) {
        return player.undiscoverRecipes(collection);
    }

    public boolean hasDiscoveredRecipe(@NotNull NamespacedKey namespacedKey) {
        return player.hasDiscoveredRecipe(namespacedKey);
    }

    @NotNull
    public Set<NamespacedKey> getDiscoveredRecipes() {
        return player.getDiscoveredRecipes();
    }

    @Nullable
    public Entity getShoulderEntityLeft() {
        return player.getShoulderEntityLeft();
    }

    public void setShoulderEntityLeft(@Nullable Entity entity) {
        player.setShoulderEntityLeft(entity);
    }

    @Nullable
    public Entity getShoulderEntityRight() {
        return player.getShoulderEntityRight();
    }

    public void setShoulderEntityRight(@Nullable Entity entity) {
        player.setShoulderEntityRight(entity);
    }

    public boolean dropItem(boolean b) {
        return player.dropItem(b);
    }

    public float getExhaustion() {
        return player.getExhaustion();
    }

    public void setExhaustion(float v) {
        player.setExhaustion(v);
    }

    public float getSaturation() {
        return player.getSaturation();
    }

    public void setSaturation(float v) {
        player.setSaturation(v);
    }

    public int getFoodLevel() {
        return player.getFoodLevel();
    }

    public void setFoodLevel(int i) {
        player.setFoodLevel(i);
    }

    public int getSaturatedRegenRate() {
        return player.getSaturatedRegenRate();
    }

    public void setSaturatedRegenRate(int i) {
        player.setSaturatedRegenRate(i);
    }

    public int getUnsaturatedRegenRate() {
        return player.getUnsaturatedRegenRate();
    }

    public void setUnsaturatedRegenRate(int i) {
        player.setUnsaturatedRegenRate(i);
    }

    public int getStarvationRate() {
        return player.getStarvationRate();
    }

    public void setStarvationRate(int i) {
        player.setStarvationRate(i);
    }

    public double getEyeHeight() {
        return player.getEyeHeight();
    }

    public double getEyeHeight(boolean b) {
        return player.getEyeHeight(b);
    }

    @NotNull
    public Location getEyeLocation() {
        return player.getEyeLocation();
    }

    @NotNull
    public List<Block> getLineOfSight(@Nullable Set<Material> set, int i) {
        return player.getLineOfSight(set, i);
    }

    @NotNull
    public Block getTargetBlock(@Nullable Set<Material> set, int i) {
        return player.getTargetBlock(set, i);
    }

    @NotNull
    public List<Block> getLastTwoTargetBlocks(@Nullable Set<Material> set, int i) {
        return player.getLastTwoTargetBlocks(set, i);
    }

    @Nullable
    public Block getTargetBlockExact(int i) {
        return player.getTargetBlockExact(i);
    }

    @Nullable
    public Block getTargetBlockExact(int i, @NotNull FluidCollisionMode fluidCollisionMode) {
        return player.getTargetBlockExact(i, fluidCollisionMode);
    }

    @Nullable
    public RayTraceResult rayTraceBlocks(double v) {
        return player.rayTraceBlocks(v);
    }

    @Nullable
    public RayTraceResult rayTraceBlocks(double v, @NotNull FluidCollisionMode fluidCollisionMode) {
        return player.rayTraceBlocks(v, fluidCollisionMode);
    }

    public int getRemainingAir() {
        return player.getRemainingAir();
    }

    public void setRemainingAir(int i) {
        player.setRemainingAir(i);
    }

    public int getMaximumAir() {
        return player.getMaximumAir();
    }

    public void setMaximumAir(int i) {
        player.setMaximumAir(i);
    }

    public int getArrowCooldown() {
        return player.getArrowCooldown();
    }

    public void setArrowCooldown(int i) {
        player.setArrowCooldown(i);
    }

    public int getArrowsInBody() {
        return player.getArrowsInBody();
    }

    public void setArrowsInBody(int i) {
        player.setArrowsInBody(i);
    }

    public int getMaximumNoDamageTicks() {
        return player.getMaximumNoDamageTicks();
    }

    public void setMaximumNoDamageTicks(int i) {
        player.setMaximumNoDamageTicks(i);
    }

    public double getLastDamage() {
        return player.getLastDamage();
    }

    public void setLastDamage(double v) {
        player.setLastDamage(v);
    }

    public int getNoDamageTicks() {
        return player.getNoDamageTicks();
    }

    public void setNoDamageTicks(int i) {
        player.setNoDamageTicks(i);
    }

    @Nullable
    public Player getKiller() {
        return player.getKiller();
    }

    public boolean addPotionEffect(@NotNull PotionEffect potionEffect) {
        return player.addPotionEffect(potionEffect);
    }

    public boolean addPotionEffect(@NotNull PotionEffect potionEffect, boolean b) {
        return player.addPotionEffect(potionEffect, b);
    }

    public boolean addPotionEffects(@NotNull Collection<PotionEffect> collection) {
        return player.addPotionEffects(collection);
    }

    public boolean hasPotionEffect(@NotNull PotionEffectType potionEffectType) {
        return player.hasPotionEffect(potionEffectType);
    }

    @Nullable
    public PotionEffect getPotionEffect(@NotNull PotionEffectType potionEffectType) {
        return player.getPotionEffect(potionEffectType);
    }

    public void removePotionEffect(@NotNull PotionEffectType potionEffectType) {
        player.removePotionEffect(potionEffectType);
    }

    @NotNull
    public Collection<PotionEffect> getActivePotionEffects() {
        return player.getActivePotionEffects();
    }

    public boolean hasLineOfSight(@NotNull Entity entity) {
        return player.hasLineOfSight(entity);
    }

    public boolean getRemoveWhenFarAway() {
        return player.getRemoveWhenFarAway();
    }

    public void setRemoveWhenFarAway(boolean b) {
        player.setRemoveWhenFarAway(b);
    }

    @Nullable
    public EntityEquipment getEquipment() {
        return player.getEquipment();
    }

    public boolean getCanPickupItems() {
        return player.getCanPickupItems();
    }

    public void setCanPickupItems(boolean b) {
        player.setCanPickupItems(b);
    }

    public boolean isLeashed() {
        return player.isLeashed();
    }

    @NotNull
    public Entity getLeashHolder() throws IllegalStateException {
        return player.getLeashHolder();
    }

    public boolean setLeashHolder(@Nullable Entity entity) {
        return player.setLeashHolder(entity);
    }

    public boolean isGliding() {
        return player.isGliding();
    }

    public void setGliding(boolean b) {
        player.setGliding(b);
    }

    public boolean isSwimming() {
        return player.isSwimming();
    }

    public void setSwimming(boolean b) {
        player.setSwimming(b);
    }

    public boolean isRiptiding() {
        return player.isRiptiding();
    }

    public boolean isSleeping() {
        return player.isSleeping();
    }

    public boolean isClimbing() {
        return player.isClimbing();
    }

    public void setAI(boolean b) {
        player.setAI(b);
    }

    public boolean hasAI() {
        return player.hasAI();
    }

    public void attack(@NotNull Entity entity) {
        player.attack(entity);
    }

    public void swingMainHand() {
        player.swingMainHand();
    }

    public void swingOffHand() {
        player.swingOffHand();
    }

    public boolean isCollidable() {
        return player.isCollidable();
    }

    public void setCollidable(boolean b) {
        player.setCollidable(b);
    }

    @NotNull
    public Set<UUID> getCollidableExemptions() {
        return player.getCollidableExemptions();
    }

    @Nullable
    public <T> T getMemory(@NotNull MemoryKey<T> memoryKey) {
        return player.getMemory(memoryKey);
    }

    public <T> void setMemory(@NotNull MemoryKey<T> memoryKey, @Nullable T t) {
        player.setMemory(memoryKey, t);
    }

    @NotNull
    public EntityCategory getCategory() {
        return player.getCategory();
    }

    public boolean isInvisible() {
        return player.isInvisible();
    }

    public void setInvisible(boolean b) {
        player.setInvisible(b);
    }

    @Nullable
    public AttributeInstance getAttribute(@NotNull Attribute attribute) {
        return player.getAttribute(attribute);
    }

    @Nullable
    public String getCustomName() {
        return player.getCustomName();
    }

    public void setCustomName(@Nullable String s) {
        player.setCustomName(s);
    }

    public void setMetadata(@NotNull String s, @NotNull MetadataValue metadataValue) {
        player.setMetadata(s, metadataValue);
    }

    @NotNull
    public List<MetadataValue> getMetadata(@NotNull String s) {
        return player.getMetadata(s);
    }

    public boolean hasMetadata(@NotNull String s) {
        return player.hasMetadata(s);
    }

    public void removeMetadata(@NotNull String s, @NotNull Plugin plugin) {
        player.removeMetadata(s, plugin);
    }

    public boolean isPermissionSet(@NotNull String s) {
        return player.isPermissionSet(s);
    }

    public boolean isPermissionSet(@NotNull Permission permission) {
        return player.isPermissionSet(permission);
    }

    public boolean hasPermission(@NotNull String s) {
        return player.hasPermission(s);
    }

    public boolean hasPermission(@NotNull Permission permission) {
        return player.hasPermission(permission);
    }

    @NotNull
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String s, boolean b) {
        return player.addAttachment(plugin, s, b);
    }

    @NotNull
    public PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        return player.addAttachment(plugin);
    }

    @Nullable
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String s, boolean b, int i) {
        return player.addAttachment(plugin, s, b, i);
    }

    @Nullable
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, int i) {
        return player.addAttachment(plugin, i);
    }

    public void removeAttachment(@NotNull PermissionAttachment permissionAttachment) {
        player.removeAttachment(permissionAttachment);
    }

    public void recalculatePermissions() {
        player.recalculatePermissions();
    }

    @NotNull
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return player.getEffectivePermissions();
    }

    public boolean isOp() {
        return player.isOp();
    }

    public void setOp(boolean b) {
        player.setOp(b);
    }

    @NotNull
    public PersistentDataContainer getPersistentDataContainer() {
        return player.getPersistentDataContainer();
    }

    public void sendPluginMessage(@NotNull Plugin plugin, @NotNull String s, @NotNull byte[] bytes) {
        player.sendPluginMessage(plugin, s, bytes);
    }

    @NotNull
    public Set<String> getListeningPluginChannels() {
        return player.getListeningPluginChannels();
    }

    @NotNull
    public <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> aClass) {
        return player.launchProjectile(aClass);
    }

    @NotNull
    public <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> aClass, @Nullable Vector vector) {
        return player.launchProjectile(aClass, vector);
    }
    //endregion


}
