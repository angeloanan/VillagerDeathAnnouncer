package xyz.angeloanan.villagerdeathsannouncer;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class VillagerDeathsAnnouncer extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("[VillagerDeathAnnouncer] Plugin is activated");
    }

    @Override
    public void onDisable() {
        System.out.println("[VillagerDeathAnnouncer] Plugin is disabled");
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntityType() == EntityType.VILLAGER) {
            Villager villager = (Villager) e.getEntity();
            Location deathLocation = villager.getLocation();
            String profession = StringUtils.capitalize(villager.getProfession().toString().toLowerCase());

            Integer x = deathLocation.getBlockX();
            Integer z = deathLocation.getBlockZ();

            // Message building
            StringBuilder message = new StringBuilder().append("A Villager");
            if (!profession.equals("None")) {
                message.append(" (").append(profession).append(")");
            }
            message.append(" has died on ").append(x).append(", ").append(z).append(".");
            // A Villager (Profession) has died on XX, ZZ.

            getServer().broadcastMessage(message.toString());
        }
    }
}
