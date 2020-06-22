package main;

import dfs.DataNode;
import mapreduce.TaskTracker;

public class Main {
    public static void main(String[] args) {

         DataNode dataNode = new DataNode(58030);
         Commander.exec();

        TaskTracker taskTracker = new TaskTracker();

    }
}
