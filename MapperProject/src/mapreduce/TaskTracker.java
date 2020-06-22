package mapreduce;

import connection.SocketWrapper;
import main.Connecter;
import main.EnvironmentSetup;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskTracker <K extends Comparable, V>{
    private SocketWrapper socket;
    private static JobType jobType;
    private static boolean finish = false;
    private static long COMMAND_COUNTER = 0;


    private static ConcurrentLinkedQueue<JTCommand> commandsRecived = new ConcurrentLinkedQueue<>();
    private static ConcurrentHashMap<Long, JTCommand> inExecution = new ConcurrentHashMap<>();
    private static ConcurrentLinkedQueue<JTCommand> CommandsToSend = new ConcurrentLinkedQueue<>();
    private static ArrayList<TaskInfo> tasks = new ArrayList<>();




    public static long getIncCounter() {
        synchronized ((Long) COMMAND_COUNTER) {
            return ++COMMAND_COUNTER;
        }
    }


    public SocketWrapper getSocket() {
        return socket;
    }

    public void setSocket(SocketWrapper socket) {
        this.socket = socket;
    }

    public static void setJobType(JobType jobType) {
        TaskTracker.jobType = jobType;
    }

    public static boolean isFinish() {
        return finish;
    }

    public static void setFinish(boolean finish) {
        TaskTracker.finish = finish;
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
        TaskTracker.commandsRecived = commandsRecived;
    }

    public static ConcurrentHashMap<Long, JTCommand> getInExecution() {
        return inExecution;
    }

    public static void setInExecution(ConcurrentHashMap<Long, JTCommand> inExecution) {
        TaskTracker.inExecution = inExecution;
    }

    public static ConcurrentLinkedQueue<JTCommand> getCommandsToSend() {
        return CommandsToSend;
    }

    public static void setCommandsToSend(ConcurrentLinkedQueue<JTCommand> commandsToSend) {
        CommandsToSend = commandsToSend;
    }

    public static ArrayList<TaskInfo> getTasks() {
        return tasks;
    }

    public static void setTasks(ArrayList<TaskInfo> tasks) {
        TaskTracker.tasks = tasks;
    }

    public TaskTracker() {
        this.socket = Connecter.getSocket();
        JTCommand jtCommand;
        while (true) {
            try {
                ObjectInputStream objectInputStream = socket.getOis();
                jtCommand = (JTCommand) objectInputStream.readObject();
                jobType = jtCommand.getType();
                EnvironmentSetup.setDIRECTORY("mapreduce/TaskTracker" + jtCommand.getDataNodeInfo().getId());

                break;
            } catch (Exception e) {
              //  e.printStackTrace();
            }
        }

        if (jobType == JobType.Map) {
            Mapper.setCode(jtCommand.getCode());
            startMapperExec();

        } else {
            Reducer.setCode(jtCommand.getCode());
            startReducerExec();
        }

    }

    public static void finished() {
        finish = true;
    }

    private void startReducerExec() {
        while (!finish) {

        }
        finish = false;
    }

    private void startMapperExec() {
        while (!finish) {
            new Listener();
            while (!CommandsToSend.isEmpty()) {
                //ThreadManager.service.submit(new Sender(CommandsToSend.remove()));
                new Sender(CommandsToSend.remove());
            }
            while (!commandsRecived.isEmpty()) {
                // ThreadManager.service.submit(new JTHandler(commandsRecived.remove()));
                new JTHandler(commandsRecived.remove());
            }


        }

        System.out.println("Mapping end");
        finish = false;
    }

    public static JobType getJobType() {
        return jobType;
    }
}
