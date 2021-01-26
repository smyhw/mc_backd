package smyhw;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;



import java.util.HashMap;
import java.util.Map;
import org.bukkit.*;

public class backd extends JavaPlugin implements Listener{
	static Map<String,Location> locMap = new HashMap<String,Location>();
	static FileConfiguration configer;
	@Override
    public void onEnable() 
	{
		Bukkit.getPluginManager().registerEvents(this,this);
		saveDefaultConfig();
		configer = getConfig();
		getLogger().info("backd已经加载");
    }

	@Override
    public void onDisable() 
	{
		getLogger().info("backd已经卸载");
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
    	if (cmd.getName().equalsIgnoreCase("backd"))
    	{
    		Location loc = locMap.get(sender.getName());
    		if(loc == null)
    		{sender.sendMessage("没有可以返回的死亡地点");return true;}
    		if(configer.getStringList("disable_worlds").contains(loc.getWorld().getName()))
    		{sender.sendMessage("你的上一个死亡地点在禁止返回的世界中");return true;}
        	((Player) sender).	teleport(loc);
        	sender.sendMessage("成功返回死亡地点");
        	locMap.remove(sender.getName());
        	return true;
    	}
    	return false;
    }
    @EventHandler
    public void dd(PlayerDeathEvent e)
    {
    	locMap.put(e.getEntity().getName(), e.getEntity().getLocation());
    }
}