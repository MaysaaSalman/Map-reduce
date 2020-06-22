package main;

import connection.SocketWrapper;
import dfs.DFSOps;
import dfs.NameNode;
import shared.DFSCommand;
import shared.DataNodeInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connecter {
    private static SocketWrapper socket;

    public static SocketWrapper getSocket() {
        return socket;
    }

    public static void setSocket(SocketWrapper socket) {
        Connecter.socket = socket;
    }

    public static DataNodeInfo start() {
        try {
            socket = new SocketWrapper(new Socket("localhost", 58030));
            ObjectInputStream objectInputStream = socket.setOis();
            String recivedId = (String) objectInputStream.readObject();
            String id = recivedId + Commander.getIncCommandId();
            DataNodeInfo dataNodeInfo = new DataNodeInfo(recivedId);
            DFSCommand dfsCommand = new DFSCommand(Integer.parseInt(id), DFSOps.JOIN, dataNodeInfo);
            Commander.getInExecution().put(Long.parseLong(id), dfsCommand);
            ObjectOutputStream objectOutputStream = socket.setOos();
            objectOutputStream.writeObject(dfsCommand);
            NameNode.setStreams(objectInputStream);

            return dataNodeInfo;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
