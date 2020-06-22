package main;

import connection.SocketWrapper;
import shared.DFSCommand;

public class Listener implements Runnable {
    private SocketWrapper socket;

    Listener() {
        this.socket = Connecter.getSocket();
        try {
            socket.getSocket().setSoTimeout(2000);
//            System.out.println(socket.getLocalPort());
           // System.out.println("MMMMMM");

            DFSCommand dfsCommand = (DFSCommand) socket.getOis().readObject();
            System.out.println(dfsCommand.getDfsOp());
         //   System.out.println("s2s2s2s2m n b n       s2s");
           // System.out.println(dfsCommand);
           // System.out.println("e2e2e2e2e2e2");
            Commander.getCommandsRecived().add(dfsCommand);
        } catch (Exception ignored) {
            //    ignored.printStackTrace();
        }

        //   try {
        //      socket.setSoTimeout(2000);
        //       //              NameNode.streams.defaultReadObject();
        //       ObjectInputStream objectInputStream = socket.getOis();
        //       DFSCommand dfsCommand = (DFSCommand) objectInputStream.readObject();
        //       System.out.println("ssssssssss");
        //       System.out.println(dfsCommand);
        //       System.out.println("eeeeeeeeee");
        //       // (DFSCommand) objectInputStream.readObject());
        //       Commander.commandsRecived.add(dfsCommand);
        //   } catch (IOException | ClassNotFoundException e) {
        //       if (e.getMessage() != null && !e.getMessage().contains("invalid type code: 00")) {
        //           System.out.println(e);
        //       }
        //    }
    }

    @Override
    public void run() {
        try {
            socket.getSocket().setSoTimeout(2000);
//            System.out.println(socket.getLocalPort());
            System.out.println("MMMMMM");
            DFSCommand dfsCommand = (DFSCommand) socket.getOis().readObject();
            System.out.println("s2s2s2s2m n b n       s2s");
            System.out.println(dfsCommand);
            System.out.println("e2e2e2e2e2e2");
            Commander.getCommandsRecived().add(dfsCommand);
        } catch (Exception ignored) {
        }
    }
}
