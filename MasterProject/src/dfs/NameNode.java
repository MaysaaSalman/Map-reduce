package dfs;

import connection.SocketWrapper;
import main.EnvironmentSetup;
import shared.DataNodeInfo;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class NameNode implements Serializable {
    private static  int blockSize = 1000;
    private static int dataNodeNum = EnvironmentSetup.getNumberOfMappers();
    private static int port = 58030;
    private static ConcurrentHashMap<Integer, DataNodeInfo> dataNodeList = new ConcurrentHashMap<>();//<clusterID,ClusterInfo>
    private static ConcurrentHashMap<Integer, SocketWrapper> Connections = new ConcurrentHashMap<>();
    private static PriorityBlockingQueue<DataNodeInfo> dataNodes = new PriorityBlockingQueue<>();
    private   static NameNode nameNode = null;
    private static HDFSFileSystem hdfsFileSystem = new HDFSFileSystem();
    private static long totalBlocksNum = 0;

    public static int getBlockSize() {
        return blockSize;
    }

    public static void setBlockSize(int blockSize) {
        NameNode.blockSize = blockSize;
    }

    public static int getDataNodeNum() {
        return dataNodeNum;
    }

    public static void setDataNodeNum(int dataNodeNum) {
        NameNode.dataNodeNum = dataNodeNum;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        NameNode.port = port;
    }

    public static ConcurrentHashMap<Integer, DataNodeInfo> getDataNodeList() {
        return dataNodeList;
    }

    public static void setDataNodeList(ConcurrentHashMap<Integer, DataNodeInfo> dataNodeList) {
        NameNode.dataNodeList = dataNodeList;
    }

    public static ConcurrentHashMap<Integer, SocketWrapper> getConnections() {
        return Connections;
    }

    public static void setConnections(ConcurrentHashMap<Integer, SocketWrapper> connections) {
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
        dataNodes.add(dataNodeInfo);
        System.out.println(dataNodeInfo.getId());
        synchronized (hdfsFileSystem) {
            hdfsFileSystem.putFile("DataNode" + dataNodeInfo.getId());
        }
    }

    public static void sendData(String data) {
        totalBlocksNum++;
        List<DataNodeInfo> replicateDN = new ArrayList<>();
        for (int i = 0; i < EnvironmentSetup.getReplicaNums(); i++) {
            DataNodeInfo temp = dataNodes.remove();
            temp.setLoad(temp.getLoad() + 1);
            replicateDN.add(temp);
            dataNodes.add(temp);
        }
        synchronized ((Long) totalBlocksNum) {
            hdfsFileSystem.addBlock(totalBlocksNum, data, replicateDN);
        }


    }

    public static void addConnection(int connectionsNum, Socket dataNodeSocket) {
        synchronized (Connections) {
            Socket socket = dataNodeSocket;
            Connections.put(connectionsNum, new SocketWrapper(socket));

        }
    }


}
