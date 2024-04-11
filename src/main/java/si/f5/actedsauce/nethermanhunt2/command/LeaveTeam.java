package si.f5.actedsauce.nethermanhunt2.command;

import org.bukkit.entity.Player;
import si.f5.actedsauce.nethermanhunt2.Main;
import si.f5.actedsauce.nethermanhunt2.team.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LeaveTeam implements CommandExecutor{
        public static final String COMMAND_NAME = "leaveteam";
        @Override
        public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage("プレイヤーしか使用できません。");
                return false;
            }
            if(command.getName().equalsIgnoreCase(COMMAND_NAME)) {
                try {
                    Main.getTeamManager().RemovePlayer((Player) commandSender);
                } catch (TeamManager.PlayerIsNotInTeamException e) {
                    commandSender.sendMessage("チームに入っていません。");
                }
                return true;
            }
            return false;
        }
}
