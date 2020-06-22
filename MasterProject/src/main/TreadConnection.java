package main;

import connection.SocketWrapper;
import dfs.NameNode;
import shared.DFSCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TreadConnection implements Runnable {
    private SocketWrapper dataNodeSocket;


    TreadConnection(SocketWrapper dataNodeSocket) {
        this.dataNodeSocket = dataNodeSocket;
    }

    @Override
    public void run() {
        try {
            System.out.println("New Connection" + NameNode.ConnectionsNum());
            //    NameNode.addConnection(NameNode.ConnectionsNum()+1,dataNodeSocket);
            System.out.println(NameNode.getConnections().get(NameNode.ConnectionsNum()));
            ObjectOutputStream objectOutputStream = dataNodeSocket.setOos();
            objectOutputStream.writeObject(String.valueOf(NameNode.ConnectionsNum()));
            ObjectInputStream objectInputStream = dataNodeSocket.setOis();
            ThreadManager.getService().submit(new DFSHandler((DFSCommand) objectInputStream.readObject()));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //TODO Serialize HDFS Block instade of String format!!


    }
}
