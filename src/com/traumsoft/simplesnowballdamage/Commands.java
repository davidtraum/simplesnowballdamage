package com.traumsoft.simplesnowballdamage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {

    private Main main;

    public Commands(Main pMain) {
        this.main = pMain;
        this.main.getCommand("sbd").setExecutor(this);
        this.main.getCommand("sbd").setTabCompleter(this);
    }

    private void sendMessage(CommandSender to, String message) {
        to.sendMessage("§a[SnowballDamage] §r" + message);
    }

    private void sendHelp(CommandSender to) {
        this.sendMessage(to, "/sbd <enable / disable> §7Toggle snowball damage");
        this.sendMessage(to, "/sbd damage <value> §7Change the damage a snowball does (hearts per hit)");
        this.sendMessage(to, "/sbd status §7See if snowball damage is enabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        switch(s) {
            case "sbd":
                if(args.length > 0) {
                    switch(args[0]) {
                        case "enable":
                            this.main.setDamageEnabled(true);
                            this.sendMessage(sender, "Snowball damage enabled.");
                            break;
                        case "disable":
                            this.main.setDamageEnabled(false);
                            this.sendMessage(sender, "Snowball damage disabled.");
                            break;
                        case "status":
                            if(this.main.enabled) {
                                this.sendMessage(sender, "Snowball damage is enabled");
                            } else {
                                this.sendMessage(sender, "Snwoball damage is disabled");
                            }
                            break;
                        case "damage":
                            if(args.length > 1) {
                                try {
                                    double damage = Double.parseDouble(args[1]);
                                    main.setDamage(damage);
                                    this.sendMessage(sender, "Set snowball damage to " + damage + " hearts.");
                                }catch(Exception e) {
                                    this.sendMessage(sender, "§cPlease provide a valid damage value (number).");
                                }
                            } else {
                                this.sendMessage(sender, "§cPlease provide a damage value.");
                            }
                            break;
                        default:
                            this.sendHelp(sender);
                            break;
                    }
                } else {
                    this.sendHelp(sender);
                }
                return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if(args.length > 0) {
            return Arrays.asList(new String[]{});
        } else {
            return Arrays.asList(new String[]{"enable", "disable", "status", "damage"});
        }
    }
}
