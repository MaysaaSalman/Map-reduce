package main;

import connection.SocketWrapper;
import dfs.DFSOps;
import dfs.NameNode;
import shared.DFSCommand;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Commander {

    private static long commandId = 0;
    private static boolean finished = false;
    private static ConcurrentLinkedQueue<DFSCommand> commandsRecived = new ConcurrentLinkedQueue<>();
    private static ConcurrentHashMap<Long, DFSCommand> inExecution = new ConcurrentHashMap<>();
    private static ConcurrentLinkedQueue<DFSCommand> commandsToSend = new ConcurrentLinkedQueue<>();

    public static long getIncCommandId() {
        synchronized ((Long) commandId) {
            return ++commandId;
        }
    }

    public static void exec() {
        while (!finished || !commandsToSend.isEmpty()) {
            //    System.out.println("NumOfConnections"+NameNode.Connections.size());
            for (SocketWrapper socket : NameNode.getConnections().values()) {
                //     System.out.println("NN");
                ThreadManager.getService().submit(new Listener(socket));
            }
            while (!commandsToSend.isEmpty()) {
                //   ThreadManager.service.submit(new Sender(commandsToSend.remove()));
                new Sender(commandsToSend.remove());
            }
        }

    }

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
        return commandsToSend;
    }

    public static void setCommandsToSend(ConcurrentLinkedQueue<DFSCommand> commandsToSend) {
        Commander.commandsToSend = commandsToSend;
    }



    public static void sendFinish() {

        for (int i = 1; i <= NameNode.ConnectionsNum(); i++) {
            System.out.println("NN");
            new Sender(new DFSCommand(getIncCommandId(), DFSOps.Finish, NameNode.getDataNodeList().get(i)));
        }

    }
}





