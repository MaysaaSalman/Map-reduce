package main;

import connection.SocketWrapper;
import shared.DFSCommand;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class Sender implements Runnable {
    private DFSCommand command;
    private SocketWrapper socket;

    public DFSCommand getCommand() {
        return command;
    }

    public void setCommand(DFSCommand command) {
        this.command = command;
    }

    public SocketWrapper getSocket() {
        return socket;
    }

    public void setSocket(SocketWrapper socket) {
        this.socket = socket;
    }

    public Sender(DFSCommand command) {
        socket = Connecter.getSocket();
        this.command = command;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream objectOutputStream = socket.getOos();
            objectOutputStream.writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
