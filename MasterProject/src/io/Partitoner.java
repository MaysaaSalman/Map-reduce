package io;

import dfs.NameNode;
import main.Commander;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Partitoner {


    public static void SendPartitions(File path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();

        String res = "";
        System.out.println("Start Partitioning");
        int count = 0;
        while (line != null) {
            res = res + line + "\n";
            if (!line.equals("")) {
                count++;

           if (count == NameNode.getBlockSize()) {
                     count = 0;
                    NameNode.sendData(res);
                    res = "";

                }
            }
            line = reader.readLine();

        }
        Commander.setFinished( true);


    }

}


