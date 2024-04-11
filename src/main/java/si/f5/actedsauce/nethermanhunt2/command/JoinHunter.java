package si.f5.actedsauce.nethermanhunt2.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import si.f5.actedsauce.nethermanhunt2.Main;
import si.f5.actedsauce.nethermanhunt2.team.TeamManager;
import si.f5.actedsauce.nethermanhunt2.team.Teams;

public class JoinHunter implements CommandExecutor {
    public static final String COMMAND_NAME = "joinhunter";
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("プレイヤーしか使用できません。");
            return false;
        }
        if(command.getName().equalsIgnoreCase(COMMAND_NAME)) {
            Main.getTeamManager().AddPlayer((Player) commandSender, Teams.HUNTER);
            return true;
        }
        return false;
    }
}
