package si.f5.actedsauce.nethermanhunt2;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import java.util.Random;

public class GameWorld {
    private static final String WORLD_NAME = "si.f5.actedsauce.nethermanhunt2.world";
    private static final int BORDER_SIZE = 4000;
    private World world;
    public void CreateWorld() {
        GenerateWorld();
        ConfigWorld();
    }
    private void GenerateWorld() {
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
