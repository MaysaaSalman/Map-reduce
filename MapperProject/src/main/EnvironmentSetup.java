package main;

import java.io.File;

public class EnvironmentSetup {
    private static String NAME_NODE_IP;
    private static String NAMENODE_SERVICENAME;
    private static int NAME_NODE_REGISTRY_PORT;
    private static int DATA_NODE_REGISTRY_PORT;
    private static String DIRECTORY;
    private static int NAME_NODE_CHECK_PERIOD;
    private static int REPLICA_NUMS;
    private static int BUF_SIZE;

    static {
        NAMENODE_SERVICENAME = "namenode";
        NAME_NODE_REGISTRY_PORT = 11111;
        DIRECTORY = "HDFS";
        DATA_NODE_REGISTRY_PORT = 22222;
        NAME_NODE_CHECK_PERIOD = 5000;
        REPLICA_NUMS = 2;
        BUF_SIZE = 2048;
    }

    public static String getNameNodeIp() {
        return NAME_NODE_IP;
    }

    public static void setNameNodeIp(String nameNodeIp) {
        NAME_NODE_IP = nameNodeIp;
    }

    public static String getNamenodeServicename() {
        return NAMENODE_SERVICENAME;
    }

    public static void setNamenodeServicename(String namenodeServicename) {
        NAMENODE_SERVICENAME = namenodeServicename;
    }

    public static int getNameNodeRegistryPort() {
        return NAME_NODE_REGISTRY_PORT;
    }

    public static void setNameNodeRegistryPort(int nameNodeRegistryPort) {
        NAME_NODE_REGISTRY_PORT = nameNodeRegistryPort;
    }

    public static int getDataNodeRegistryPort() {
        return DATA_NODE_REGISTRY_PORT;
    }

    public static void setDataNodeRegistryPort(int dataNodeRegistryPort) {
        DATA_NODE_REGISTRY_PORT = dataNodeRegistryPort;
    }

    public static String getDIRECTORY() {
        return DIRECTORY;
    }

    public static void setDIRECTORY(String DIRECTORY) {
        EnvironmentSetup.DIRECTORY = DIRECTORY;
    }

    public static int getNameNodeCheckPeriod() {
        return NAME_NODE_CHECK_PERIOD;
    }

    public static void setNameNodeCheckPeriod(int nameNodeCheckPeriod) {
        NAME_NODE_CHECK_PERIOD = nameNodeCheckPeriod;
    }

    public static int getReplicaNums() {
        return REPLICA_NUMS;
    }

    public static void setReplicaNums(int replicaNums) {
        REPLICA_NUMS = replicaNums;
    }

    public static int getBufSize() {
        return BUF_SIZE;
    }

    public static void setBufSize(int bufSize) {
        BUF_SIZE = bufSize;
    }

    public static boolean createDirectory(String name) {

        File folder = new File(EnvironmentSetup.DIRECTORY + "/" + name);
        System.out.println("trying to create: " + folder.getAbsolutePath());
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Directory created");
            } else {
                System.out.println("Warning Directory already used please change directory name or delete the directory first");
                return false;
            }
        } else
            System.out.println("Warning Directory already used please change directory name or delete the directory first");
        return true;
    }

}
