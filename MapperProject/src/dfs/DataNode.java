package dfs;

import main.Connecter;
import main.EnvironmentSetup;
import shared.DataNodeInfo;

import java.util.concurrent.ConcurrentHashMap;

public class DataNode {
    private int dataNodePort;
    private ConcurrentHashMap<String, ConcurrentHashMap<Integer, HDFSBlock>> fileToBlock;
    private static DataNodeInfo dataNodeInfo;
    private static HDFSFileSystem fileSystem = new HDFSFileSystem();

    public DataNode(int dataNodePort) {
        dataNodeInfo = Connecter.start();
        dataNodeInfo.setDirectory("DataNode" + dataNodeInfo.getId());
        EnvironmentSetup.createDirectory(dataNodeInfo.getDirectory());
        dataNodeInfo.setDirectory("HDFS/" + dataNodeInfo.getDirectory());

        this.dataNodePort = dataNodePort;
        this.fileToBlock = new ConcurrentHashMap<>();
    }

    public int getDataNodePort() {
        return dataNodePort;
    }

    public void setDataNodePort(int dataNodePort) {
        this.dataNodePort = dataNodePort;
    }

    public ConcurrentHashMap<String, ConcurrentHashMap<Integer, HDFSBlock>> getFileToBlock() {
        return fileToBlock;
    }

    public void setFileToBlock(ConcurrentHashMap<String, ConcurrentHashMap<Integer, HDFSBlock>> fileToBlock) {
        this.fileToBlock = fileToBlock;
    }

    public static DataNodeInfo getDataNodeInfo() {
        return dataNodeInfo;
    }

    public static void setDataNodeInfo(DataNodeInfo dataNodeInfo) {
        DataNode.dataNodeInfo = dataNodeInfo;
    }

    public static HDFSFileSystem getFileSystem() {
        return fileSystem;
    }

    public static void setFileSystem(HDFSFileSystem fileSystem) {
        DataNode.fileSystem = fileSystem;
    }
}
