package si.f5.actedsauce.nethermanhunt2.team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.HashSet;
import java.util.Set;


public class TeamManager {
    Hunter hunter;
    Runner runner;
    public TeamManager() {
        runner = new Runner();
        hunter = new Hunter();
    }
    public void AddPlayer(Player player,Teams team) {
        if(team == Teams.RUNNER){runner.AddPlayer(player);}
        else if (team == Teams.HUNTER){hunter.AddPlayer(player);}
    }
    public void RemovePlayer(Player player) throws PlayerIsNotInTeamException{
        if (runner.GetPlayers().contains(player)) {
            runner.RemovePlayer(player);
        }else if (hunter.GetPlayers().contains(player)) {
            hunter.RemovePlayer(player);
        } else {
            throw new PlayerIsNotInTeamException();
        }
    }
    public Set<OfflinePlayer> GetRunners() {
        return runner.GetPlayers();
    }
    public Set<OfflinePlayer> GetHunters() {
        return hunter.GetPlayers();
    }
    public Set<OfflinePlayer> GetPlayersOnATeam() {
        Set<OfflinePlayer> set = new HashSet<>();
        set.addAll(GetRunners());
        set.addAll(GetHunters());
        return set;
    }
    public static class PlayerIsNotInTeamException extends Exception{

    }
}