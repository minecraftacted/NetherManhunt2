package si.f5.actedsauce.nethermanhunt2.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import si.f5.actedsauce.nethermanhunt2.GameSequence;
import si.f5.actedsauce.nethermanhunt2.Main;

public class Suspension implements CommandExecutor {
    public static final String COMMAND_NAME = "suspension";
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase(COMMAND_NAME)) {
            Main.getGameSequence().Suspension();
            return true;
        }
        return false;
    }
}
