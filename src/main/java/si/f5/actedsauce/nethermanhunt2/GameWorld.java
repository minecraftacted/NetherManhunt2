package si.f5.actedsauce.nethermanhunt2;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;


public class GameWorld {
    private static final String WORLD_NAME = "si.f5.actedsauce.nethermanhunt2.world";
    private static final int BORDER_SIZE = 4000;
    private World world;
    public void CreateWorld() {
        DeleteOldWorld();
        GenerateWorld();
        ConfigWorld();
    }
    private void DeleteOldWorld() {
        //一旦全員オーバーワールドに追い出す
        if(Bukkit.getWorld(WORLD_NAME) != null) {
            List<Player> players = Bukkit.getWorld(WORLD_NAME).getPlayers();
            for (Player player:players) {
                player.teleport(new Location(Bukkit.getWorld("world"),0,1000000,0));
            }
        }
        Bukkit.unloadWorld(WORLD_NAME,false);
        for(File file:Bukkit.getWorldContainer().listFiles()){
            if(file.getName().equals(WORLD_NAME)) {
                try {
                    FileUtils.deleteDirectory(file);
                    break;
                }catch (java.io.IOException e) {
                    Bukkit.getLogger().warning("Can't delete "+WORLD_NAME+" !");
                    Bukkit.getLogger().warning("Continue Old world.");
                }
            }
        }
    }
    private void GenerateWorld(){
        world = new WorldCreator(WORLD_NAME)
                .environment(World.Environment.NETHER)
                .seed(new Random().nextLong(1000000000))
                .createWorld();
    }
    private void ConfigWorld() {
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setSize(BORDER_SIZE);
        worldBorder.setCenter(0,0);
    }
    public void TeleportPlayerToRandomLocation(Player player){
        int centerX = 0;
        int centerZ = 0;
        int spreadDistance = 100;
        int maxRange = BORDER_SIZE/2;
        int maxHeight = 127;
        String respectTeams = "false";
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "execute in "+WORLD_NAME+" run spreadplayers "+centerX+" "+centerZ+" "+spreadDistance+" "+maxRange+" under "+maxHeight+" "+respectTeams+" "+player.getName());
    }
}
