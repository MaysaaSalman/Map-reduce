package main;

import dfs.DFSOps;
import dfs.NameNode;
import injection.Import;
import injection.Library;
import io.Partitoner;
import mapreduce.Code;
import mapreduce.JobTracker;
import shared.DFSCommand;
import shared.DataNodeInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        /** Environment Setup **/


        System.out.println("Enter The Number Of Mappers");
        EnvironmentSetup.setNumberOfMappers( Integer.parseInt(reader.readLine().trim()));

        System.out.println("Enter The Number of Replica Blocks");
        EnvironmentSetup.setReplicaNums( Integer.parseInt(reader.readLine().trim()));

        System.out.println("Run Mapper Project for");


        if (!Connecter.start()) {
            System.out.println("Connection establish error");
            System.exit(0);
        }


//        while (NameNode.Connections.size() < EnvironmentSetup.NUMBER_OF_MAPPERS) ;


        System.out.println(NameNode.ConnectionsNum() + "Connected");


        try {
            Partitoner.SendPartitions(new File(args[0]));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Commander.exec();


        if (Commander.isFinished() && Commander.getCommandsToSend().size() == 0) {
            SendFinishStore();
        }

        Commander.sendFinish();


        System.out.println("enter the code" +
                "\n for example for the word count insert:" +
                "\n  Record<String,Integer> record=new Record<>(keyIn,1); " +
                "\n after the code insert \"done\"");

        String code = "";
        String line = reader.readLine();

        while (!line.toLowerCase().equals("done")) {
            code = code + line;
            line = reader.readLine();
        }

        System.out.println("Enter the Import Statements");
        ArrayList<Library> libraryArrayList = new ArrayList<>();
        String library = reader.readLine();
        while (!library.equals("")) {
            libraryArrayList.add(new Library(library));
            library = reader.readLine();
        }


        Code codeWithImports = new Code(new Import(libraryArrayList), code);
        JobTracker.setCode(codeWithImports);

        if (JobTracker.startMapping()) {
            System.out.println("Mapping Done Successfully ");
        } else {
            System.out.println("wrong mapping!!");
            System.exit(0);
        }
    }

    private static void SendFinishStore() {
        for (DataNodeInfo dataNodeInfo : NameNode.getDataNodeList().values()) {
            ThreadManager.getService().submit(new Sender(new DFSCommand(Commander.getIncCommandId(), DFSOps.Finish, dataNodeInfo)));
        }
    }


}
