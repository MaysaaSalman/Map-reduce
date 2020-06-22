package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class SocketWrapper {
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public SocketWrapper(Socket socket) {
        try {
            socket.setSoTimeout(2000);
            this.socket = socket;
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream setOis() {
        try {
            this.ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.ois;
    }

    public ObjectOutputStream setOos() {
        try {
            this.oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.oos;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    @Override
    public String toString() {
        return "SocketWrapper{" +
                "socket=" + socket +
                ", ois=" + ois +
                ", oos=" + oos +
                '}';
    }
}
