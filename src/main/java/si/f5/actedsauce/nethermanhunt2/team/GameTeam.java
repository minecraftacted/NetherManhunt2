package si.f5.actedsauce.nethermanhunt2.team;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Set;

abstract class GameTeam {
    protected String TEAM_NAME;
    protected ChatColor TEAM_COLOR;
    protected String TEAM_DISPLAY_NAME;
    protected String TEAM_PREFIX;
    Team bukkitTeam;
    public GameTeam() {
        if (this instanceof Runner) {
            TEAM_NAME = "runner";
            TEAM_COLOR = ChatColor.AQUA;
            TEAM_DISPLAY_NAME = "逃走者";
        } else if (this instanceof Hunter) {
            TEAM_NAME = "hunter";
            TEAM_COLOR = ChatColor.RED;
            TEAM_DISPLAY_NAME = "鬼";
        }
        TEAM_PREFIX = TEAM_COLOR + "["+TEAM_DISPLAY_NAME+"]";
        TEAM_DISPLAY_NAME = TEAM_COLOR + TEAM_DISPLAY_NAME;
        CreateTeam();
        ConfigTeam();
    }
    public void CreateTeam() {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        bukkitTeam = scoreboard.registerNewTeam(TEAM_NAME);
    }
    public void ConfigTeam() {
        bukkitTeam.setAllowFriendlyFire(true);
        bukkitTeam.setCanSeeFriendlyInvisibles(true);
        bukkitTeam.setColor(TEAM_COLOR);
        bukkitTeam.setDisplayName(TEAM_DISPLAY_NAME);
        bukkitTeam.setPrefix(TEAM_PREFIX);
        bukkitTeam.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.ALWAYS);
        bukkitTeam.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
        bukkitTeam.setOption(Team.Option.DEATH_MESSAGE_VISIBILITY, Team.OptionStatus.ALWAYS);
    }
    public void AddPlayer(Player player) {
        bukkitTeam.addEntry(player.getName());
        DirectionOnJoin(player);
    }
    public void RemovePlayer(Player player) {
        bukkitTeam.removeEntry(player.getName());
        DirectionOnLeave(player);
    }
    public Set<OfflinePlayer> GetPlayers() {
        return bukkitTeam.getPlayers();
    }
    protected void DirectionOnJoin(Player player) {
        player.sendMessage(TEAM_DISPLAY_NAME + "に参加しました。");
        player.playSound(player, Sound.UI_TOAST_OUT, SoundCategory.MASTER,1,1);
        player.playSound(player, Sound.UI_BUTTON_CLICK, SoundCategory.MASTER,1,1);
        player.playSound(player, Sound.UI_LOOM_SELECT_PATTERN, SoundCategory.MASTER,1,1);
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_COW_BELL, SoundCategory.MASTER,1,1);
    }
    protected void DirectionOnLeave(Player player) {
        player.sendMessage("チームから脱退しました。");
        player.playSound(player,Sound.ENTITY_ENDER_EYE_DEATH,SoundCategory.MASTER,1,1);
    }

}
class Runner extends GameTeam {

}
class Hunter extends GameTeam {

}
