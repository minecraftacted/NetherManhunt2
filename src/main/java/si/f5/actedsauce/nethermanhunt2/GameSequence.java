package si.f5.actedsauce.nethermanhunt2;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import si.f5.actedsauce.nethermanhunt2.team.TeamManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class GameSequence{
    GameWorld world;
    public static final int TIME_LIMIT = 60*1;
    JavaPlugin plugin;
    Timer timer;
    TeamManager teamManager;
    public GameSequence() {
        this.teamManager = Main.getTeamManager();
        this.plugin = Main.getPlugin();
        this.world = Main.getGameWorld();
    }
    public void Start() throws IllegalStateException{
        world.CreateWorld();
        timer = new Timer();//Startの度にインスタンス作り直さないと駄目(Bukkitの仕様的に)
        timer.Start();
    }
    public void Suspension() {
        timer.Suspension();
    }
}
class Timer extends BukkitRunnable{
    AtomicBoolean isRunning;
    AtomicBoolean stopFlag;
    AtomicInteger remainingTime;
    ArrayList<ProcessOnTime> processes;

    public Timer() {
        isRunning = new AtomicBoolean(false);
        stopFlag = new AtomicBoolean(false);
        remainingTime = new AtomicInteger(GameSequence.TIME_LIMIT);
        processes = new ArrayList<>();
        processes.add(new ProcessOnTime(GameSequence.TIME_LIMIT,ProcessType.TELEPORT_TO_THE_WORLD));
    }
    public void Start() throws IllegalStateException{
        if(isRunning.get()) {
            throw new IllegalStateException("すでに開始されている");
        }
        isRunning.set(true);
        runTaskTimer(Main.getPlugin(),0,20);
    }
    public void Suspension() throws IllegalStateException{
        if(!isRunning.get()) {
            throw new IllegalStateException("まだ開始されていない");
        }
        stopFlag.set(true);
    }
    @Override
    public void run() {
        if(stopFlag.get() || remainingTime.get()<=0) {
            Bukkit.broadcastMessage("Fuck you");
            stopFlag.set(false);
            isRunning.set(false);
            this.cancel();
            return;
        }
        Bukkit.broadcastMessage("Do you respect me?"+remainingTime.get());
        for(ProcessOnTime processOnTime:processes) {
            processOnTime.RunIfTimeIsCorrect(remainingTime.get());
        }
        remainingTime.decrementAndGet();
    }
}
class ProcessOnTime {
    private int time;
    private Consumer<Integer> process;
    private TeamManager teamManager;
    public ProcessOnTime(int time, ProcessType process)  {
        teamManager = Main.getTeamManager();
        this.time= time;
        switch (process){
            case TELEPORT_TO_THE_WORLD:
                this.process = this::TeleportToTheWorld;
                break;
        }
    }
    public void RunIfTimeIsCorrect(int time) {
        if(this.time == time) {
            process.accept(time);
        }
    }
    private void TeleportToTheWorld(Integer a) {
        Set<Player> players = new HashSet<>();
        for (OfflinePlayer offlinePlayer:teamManager.GetPlayersOnATeam()) {
            if(!(offlinePlayer instanceof Player)) {
                break;
            }
            players.add((Player) offlinePlayer);
        }
        for (Player player:players) {
            Main.getGameWorld().TeleportPlayerToRandomLocation(player);
        }
    }
}
enum ProcessType {
    TELEPORT_TO_THE_WORLD
}