package si.f5.actedsauce.nethermanhunt2;

import org.bukkit.plugin.java.JavaPlugin;
import si.f5.actedsauce.nethermanhunt2.command.*;
import si.f5.actedsauce.nethermanhunt2.team.TeamManager;

public class Main {
    private static JavaPlugin plugin;
    private static TeamManager teamManager;
    private static GameSequence gameSequence;
    private static GameWorld  gameWorld;
    public Main(JavaPlugin plugin) {
        Main.plugin = plugin;
        teamManager = new TeamManager();
        gameWorld = new GameWorld();
        gameSequence = new GameSequence();
        SetCommandExecutor();
    }
    public static JavaPlugin getPlugin() {
        return Main.plugin;
    }
    public static TeamManager getTeamManager() {
        return teamManager;
    }
    public static GameSequence getGameSequence() {
        return gameSequence;
    }
    public static GameWorld getGameWorld() {
        return gameWorld;
    }
    private void SetCommandExecutor() {
        plugin.getCommand(JoinHunter.COMMAND_NAME).setExecutor(new JoinHunter());
        plugin.getCommand(JoinRunner.COMMAND_NAME).setExecutor(new JoinRunner());
        plugin.getCommand(LeaveTeam.COMMAND_NAME).setExecutor(new LeaveTeam());
        plugin.getCommand(Start.COMMAND_NAME).setExecutor(new Start());
        plugin.getCommand(Suspension.COMMAND_NAME).setExecutor(new Suspension());

    }

}
