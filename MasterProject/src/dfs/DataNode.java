package dfs;

import main.EnvironmentSetup;

import java.util.concurrent.ConcurrentHashMap;

public class DataNode {
    private int dataNodePort;
    private ConcurrentHashMap<String, ConcurrentHashMap<Integer, HDFSBlock>> fileToBlock;


    public DataNode(int dataNodeRegistryPort) {
        EnvironmentSetup.createDirectory("");
        this.dataNodePort = dataNodeRegistryPort;
        this.fileToBlock = new ConcurrentHashMap<String, ConcurrentHashMap<Integer, HDFSBlock>>();
    }

}
