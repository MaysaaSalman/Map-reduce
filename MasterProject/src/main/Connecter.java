package main;

import connection.SocketWrapper;
import dfs.NameNode;

import java.io.IOException;
import java.net.ServerSocket;


public class Connecter {
    public static boolean start() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(NameNode.getPort());
            for (int i = 0; i < NameNode.getDataNodeNum(); i++) {
                System.out.println(i + "Connections");
                SocketWrapper socketWrapper = new SocketWrapper(serverSocket.accept());
                NameNode.getConnections().put(i + 1, socketWrapper);
                System.out.println(socketWrapper);
                ThreadManager.getService().submit(new TreadConnection(socketWrapper));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
