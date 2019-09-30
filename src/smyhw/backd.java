package smyhw;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import org.bukkit.*;

public class backd extends JavaPlugin implements Listener{
	String name,temp,world;
	int x=0,y=0,z=0;
	@Override
    public void onEnable() 
	{      
		Bukkit.getPluginManager().registerEvents(this,this);
		getLogger().info("backd已经加载");
    }

	@Override
    public void onDisable() 
	{
		getLogger().info("backd已经卸载");
    }
    @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
    	//begin_of_yh
    	if (cmd.getName().equalsIgnoreCase("backd"))
    	{
        	name=sender.getName();
        	x=getConfig().getInt(name+".X");
        	y=getConfig().getInt(name+".Y");
        	z=getConfig().getInt(name+".Z");
        	if(x==0 && y==0 && z==0)
        	{sender.sendMessage("没有可以返回的死亡地点");return true;}
        	((Player) sender).	teleport(new Location(Bukkit.getWorld(world),x,y,z));
        	sender.sendMessage("成功返回死亡地点");
        	getConfig().set(name+".X",0);
        	getConfig().set(name+".Y",0);
        	getConfig().set(name+".Z",0);
        	saveConfig();
        	return true;
    	}
    	//end_of_yh
    	return false;
    }
//监视方块破坏
    @EventHandler
    public void dd(PlayerDeathEvent e)
    {
//    	e.getEntity().sendMessage("gg");
//    	System.out.println("aaa");
    	name=(e.getEntity().getName());
    	world=e.getEntity().getWorld().getName();
    	x=(int) e.getEntity().getLocation().getX();
    	y=(int) e.getEntity().getLocation().getY();
    	z=(int) e.getEntity().getLocation().getZ();
    	getConfig().set(name+".X",x);
    	getConfig().set(name+".Y",y);
    	getConfig().set(name+".Z",z);
    	getConfig().set(name+".world",world);
//    	e.getEntity().sendMessage(name+":"+x+";"+y+";"+z);
    	saveConfig();
    }
}