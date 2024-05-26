package se.mspi.lab4;

import se.mspi.lab4.commands.*;
import se.mspi.lab4.mbeans.*;
import se.mspi.lab4.mbeans.Square;
import se.mspi.lab4.mbeans.SquareMBean;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShootingRangeSimulator {
    private final Map<String, Command> commandMap = new HashMap<>();
    private final ShotHistory shotHistory = new ShotHistory();
    private final ResultBean resultBean = new ResultBean();

    private Long lastShotTime = null;

    // MBeans
    private final ShotRegisterMBean shotRegisterMBean = new ShotRegister();
    private final SquareMBean squareMBean = new Square();

    public ShootingRangeSimulator() {
        addCommands(
                new ExitCommand(),
                new HelpCommand(),
                new HistoryCommand(),
                new ShotCommand()
        );
    }

    private void addCommands(Command... commands) {
        for (Command command : commands) {
            commandMap.put(command.getName(), command);
        }
    }

    private void initMBeans() {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            mbs.registerMBean(shotRegisterMBean, new ObjectName("se.mspi.lab4.mbeans:type=ShotRegister"));
            mbs.registerMBean(squareMBean,  new ObjectName("se.mspi.lab4.mbeans:type=Square"));
        } catch (JMException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        initMBeans();
        Scanner scanner = new Scanner(System.in);
        String input;
        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.print("> ");
            input = scanner.nextLine();
            if (input.trim().length() == 0) continue;
            parseAndExecute(input.trim());
        }
    }

    public void parseAndExecute(String input) {
        String[] s = input.split(" ");
        String key = s[0];
        String args = input.substring(s[0].length());
        Command command = commandMap.get(key);
        if (command != null) command.execute(args, this);
        else System.out.println("Такой команды нет, список команд: help");
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    public ShotHistory getShotHistory() {
        return shotHistory;
    }

    public ResultBean getResultBean() {
        return resultBean;
    }

    public Long getLastShotTime() {
        return lastShotTime;
    }

    public void setLastShotTime(Long lastShotTime) {
        this.lastShotTime = lastShotTime;
    }

    public ShotRegisterMBean getShotCounterMBean() {
        return shotRegisterMBean;
    }

    public SquareMBean getSquareMBean() {
        return squareMBean;
    }
}
