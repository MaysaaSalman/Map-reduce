package mapreduce;

import main.ThreadManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Commander {
    private static boolean finished = false;
    private static long COMMAND_COUNTER = 0;
    private static ConcurrentLinkedQueue<JTCommand> commandsRecived = new ConcurrentLinkedQueue<>();
    private static ConcurrentHashMap<Long, JTCommand> inExecution = new ConcurrentHashMap<>();
    private static ConcurrentLinkedQueue<JTCommand> CommandsToSend = new ConcurrentLinkedQueue<>();

    public static boolean isFinished() {
        return finished;
    }

    public static void setFinished(boolean finished) {
        Commander.finished = finished;
    }

    public static long getCommandCounter() {
        return COMMAND_COUNTER;
    }

    public static void setCommandCounter(long commandCounter) {
        COMMAND_COUNTER = commandCounter;
    }

    public static ConcurrentLinkedQueue<JTCommand> getCommandsRecived() {
        return commandsRecived;
    }

    public static void setCommandsRecived(ConcurrentLinkedQueue<JTCommand> commandsRecived) {
        Commander.commandsRecived = commandsRecived;
    }

    public static ConcurrentHashMap<Long, JTCommand> getInExecution() {
        return inExecution;
    }

    public static void setInExecution(ConcurrentHashMap<Long, JTCommand> inExecution) {
        Commander.inExecution = inExecution;
    }

    public static ConcurrentLinkedQueue<JTCommand> getCommandsToSend() {
        return CommandsToSend;
    }

    public static void setCommandsToSend(ConcurrentLinkedQueue<JTCommand> commandsToSend) {
        CommandsToSend = commandsToSend;
    }

    public static void sendJobs() {
        while (!CommandsToSend.isEmpty()) {
            System.out.println("MMMMM");
            new Sender(CommandsToSend.remove());
        }
    }


    public static long getIncCounter() {
        synchronized ((Long) COMMAND_COUNTER) {
            return ++COMMAND_COUNTER;
        }
    }

    public static void exec() {
        while (!finished || !CommandsToSend.isEmpty()) {
            //   for (SocketWrapper socket : NameNode.Connections.values()) {
            //       ThreadManager.service.submit(new Listener(socket));
            //   }
            System.out.println("LLLLLLLLLLLLL");
            while (!CommandsToSend.isEmpty()) {
                new Sender(CommandsToSend.remove());
            }
            System.out.println("TTTTTTTTT");
            while (!commandsRecived.isEmpty())
                ThreadManager.getService().submit(new JTHandler(commandsRecived.remove()));

        }
        //TODO Send the commands to send and handle the recived commamds

    }
}
