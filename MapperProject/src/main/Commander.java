package main;

import shared.DFSCommand;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Commander {
    private static long commandId = 0;
    private static boolean finished = false;
    private static ConcurrentLinkedQueue<DFSCommand> commandsRecived = new ConcurrentLinkedQueue<>();
    private static ConcurrentHashMap<Long, DFSCommand> inExecution = new ConcurrentHashMap<>();
    private static ConcurrentLinkedQueue<DFSCommand> CommandsToSend = new ConcurrentLinkedQueue<>();

    public static long getCommandId() {
        return commandId;
    }

    public static void setCommandId(long commandId) {
        Commander.commandId = commandId;
    }

    public static boolean isFinished() {
        return finished;
    }

    public static void setFinished(boolean finished) {
        Commander.finished = finished;
    }

    public static ConcurrentLinkedQueue<DFSCommand> getCommandsRecived() {
        return commandsRecived;
    }

    public static void setCommandsRecived(ConcurrentLinkedQueue<DFSCommand> commandsRecived) {
        Commander.commandsRecived = commandsRecived;
    }

    public static ConcurrentHashMap<Long, DFSCommand> getInExecution() {
        return inExecution;
    }

    public static void setInExecution(ConcurrentHashMap<Long, DFSCommand> inExecution) {
        Commander.inExecution = inExecution;
    }

    public static ConcurrentLinkedQueue<DFSCommand> getCommandsToSend() {
        return CommandsToSend;
    }

    public static void setCommandsToSend(ConcurrentLinkedQueue<DFSCommand> commandsToSend) {
        CommandsToSend = commandsToSend;
    }

    public static long getIncCommandId() {
        synchronized ((Long) commandId) {
            return ++commandId;
        }
    }

    public static void exec() {
        while (!finished) {

            //      ThreadManager.service.submit(new Listener());
            new Listener();
            System.out.println("AAAAA");
            while (!CommandsToSend.isEmpty()) {
                ThreadManager.getService().submit(new Sender(CommandsToSend.remove()));
            }
            while (!commandsRecived.isEmpty()) {
                ThreadManager.getService().submit(new DFSHandler(commandsRecived.remove()));

            }
        }
    }


}
