package de.g3ll3rt.minecraft.plugins;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class HelloPlugin extends JavaPlugin{
	final private String textEnabled = "plugin enabled";
	final private String textDisabled = "plugin disabled";
	
	public static void main(String [] args){
		System.out.println("place in plugin folder");
		
	}
	
    @Override
    public void onEnable(){
    	getLogger().info(textEnabled);
        System.out.println(textEnabled);
    }
 
    @Override
    public void onDisable() {
      System.out.println(textDisabled);
      System.out.println(textDisabled);
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(cmd.getName().equalsIgnoreCase("cgshell")){ 
    		System.out.println("cgshell gerufen");
    		return true;
    	}
    	return false; 
    }



}
