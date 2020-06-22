package mapreduce;


import connection.SocketWrapper;
import dfs.NameNode;

import java.io.ObjectOutputStream;

public class Sender implements Runnable {
    private JTCommand command;
    private SocketWrapper socket;

    Sender(JTCommand command) {
        socket = NameNode.getConnections().get(Integer.parseInt(command.getDataNodeInfo().getId()));
        this.command = command;

        try {
            System.out.println(command.getType());
            ObjectOutputStream objectOutputStream = socket.getOos();
            objectOutputStream.writeObject(command);
            System.out.println("JOB Sent");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }


    }

    @Override
    public void run() {

    }
}
