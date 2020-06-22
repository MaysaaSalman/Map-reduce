package mapreduce;

import connection.SocketWrapper;
import main.Connecter;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class Sender implements Runnable {
    JTCommand command;
    SocketWrapper socket;

    public Sender(JTCommand command) {
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
