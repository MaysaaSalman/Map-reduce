package mapreduce;

import connection.SocketWrapper;

import java.io.ObjectInputStream;

public class Listener implements Runnable {
    private SocketWrapper socket;

    Listener(SocketWrapper socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = socket.getOis();
            Commander.getCommandsRecived().add((JTCommand) objectInputStream.readObject());
        } catch (Exception e) {
            System.out.println("No Connnection yet");
        }
    }
}
