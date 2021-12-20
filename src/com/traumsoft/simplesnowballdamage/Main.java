package com.traumsoft.simplesnowballdamage;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    private double damage = 0;
    public boolean enabled = false;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.damage = this.getConfig().getDouble("damage");
        this.enabled = this.getConfig().getBoolean("enabled");
        this.getServer().getPluginManager().registerEvents(this, this);
        new Commands(this);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(this.enabled && event.getDamager().getType() == EntityType.SNOWBALL && event.getEntity() instanceof LivingEntity) {
            ((LivingEntity) event.getEntity()).damage(this.damage);
        }
    }

    public void setDamage(double damage) {
        this.damage = damage;
        this.getConfig().set("damage", damage);
        this.saveConfig();
    }

    public void setDamageEnabled(boolean status) {
        this.enabled = status;
        this.getConfig().set("enabled", status);
        this.saveConfig();
    }
}
