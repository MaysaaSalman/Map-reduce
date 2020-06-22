package dfs;

import shared.DataNodeInfo;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class NameNode implements Serializable {

    private static ConcurrentHashMap<Integer, DataNodeInfo> dataNodeList;//<clusterID,ClusterInfo>
    private static ConcurrentHashMap<Integer, Socket> Connections;
    private static PriorityBlockingQueue<DataNodeInfo> dataNodes;
    private static NameNode nameNode = null;
    private static HDFSFileSystem hdfsFileSystem = new HDFSFileSystem();
    private static ObjectInputStream streams;
    private static long totalBlocksNum = 0;

    private NameNode() {
        //  EnvironmentSetup.createDirectory("");
    }

    public static ConcurrentHashMap<Integer, DataNodeInfo> getDataNodeList() {
        return dataNodeList;
    }

    public static void setDataNodeList(ConcurrentHashMap<Integer, DataNodeInfo> dataNodeList) {
        NameNode.dataNodeList = dataNodeList;
    }

    public static ConcurrentHashMap<Integer, Socket> getConnections() {
        return Connections;
    }

    public static void setConnections(ConcurrentHashMap<Integer, Socket> connections) {
        Connections = connections;
    }

    public static PriorityBlockingQueue<DataNodeInfo> getDataNodes() {
        return dataNodes;
    }

    public static void setDataNodes(PriorityBlockingQueue<DataNodeInfo> dataNodes) {
        NameNode.dataNodes = dataNodes;
    }

    public static NameNode getNameNode() {
        return nameNode;
    }

    public static void setNameNode(NameNode nameNode) {
        NameNode.nameNode = nameNode;
    }

    public static HDFSFileSystem getHdfsFileSystem() {
        return hdfsFileSystem;
    }

    public static void setHdfsFileSystem(HDFSFileSystem hdfsFileSystem) {
        NameNode.hdfsFileSystem = hdfsFileSystem;
    }

    public static ObjectInputStream getStreams() {
        return streams;
    }

    public static void setStreams(ObjectInputStream streams) {
        NameNode.streams = streams;
    }

    public static long getTotalBlocksNum() {
        return totalBlocksNum;
    }

    public static void setTotalBlocksNum(long totalBlocksNum) {
        NameNode.totalBlocksNum = totalBlocksNum;
    }

    public static NameNode getInstance() {
        if (nameNode == null)
            return new NameNode();
        return nameNode;
    }


    public static int ConnectionsNum() {
        synchronized (Connections) {
            return Connections.size();
        }
    }

    public static void addDataNode(DataNodeInfo dataNodeInfo) {
        dataNodeList.put(Integer.parseInt(dataNodeInfo.getId()), dataNodeInfo);
        dataNodeList.put(ConnectionsNum(), dataNodeInfo);
        synchronized (hdfsFileSystem) {
            hdfsFileSystem.putFile("DataNode" + dataNodeInfo.getId());
        }
        dataNodes.add(dataNodeInfo);
    }

    public static void sendData(String data) {
        totalBlocksNum++;
        List<DataNodeInfo> replicateDN = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DataNodeInfo temp = dataNodes.remove();
            temp.setLoad( temp.getLoad() + 1);
            replicateDN.add(temp);
            dataNodes.add(temp);
        }
        for (int i = 0; i < 3; i++) {
            String fileName = "DataNode" + dataNodes.remove().getId();
            synchronized ((Long) totalBlocksNum) {
                hdfsFileSystem.addBlock(fileName, totalBlocksNum, data, replicateDN);

            }

        }
    }

    public static void addConnection(int connectionsNum, Socket dataNodeSocket) {
        synchronized (Connections) {
            Connections.put(connectionsNum, dataNodeSocket);

        }
    }


}
