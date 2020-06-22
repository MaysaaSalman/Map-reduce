package main;

import connection.SocketWrapper;
import dfs.NameNode;
import shared.DFSCommand;

import java.io.ObjectOutputStream;

public class Sender implements Runnable {
    private DFSCommand command;
    private SocketWrapper socket;

    Sender(DFSCommand command) {
        socket = NameNode.getConnections().get(Integer.parseInt(command.getDataNodeInfo().getId()));
        this.command = command;

        try {
            ObjectOutputStream objectOutputStream = socket.getOos();
            objectOutputStream.writeObject(command);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }


    }

    @Override
    public void run() {
        try {
            ObjectOutputStream objectOutputStream = socket.getOos();
            objectOutputStream.writeObject(command);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }
}
