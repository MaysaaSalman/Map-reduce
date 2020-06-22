package main;

import java.io.File;

public class EnvironmentSetup {
    private static String NAMENODE_SERVICENAME;
    private static String DIRECTORY;
    private static int REPLICA_NUMS;
    private static int BUF_SIZE;
    private static int NUMBER_OF_MAPPERS;

    static {
        NUMBER_OF_MAPPERS = 3;
        NAMENODE_SERVICENAME = "namenode";
        DIRECTORY = "HDFS";
        REPLICA_NUMS = 2;
        BUF_SIZE = 2048;
    }

    public static String getNamenodeServicename() {
        return NAMENODE_SERVICENAME;
    }

    public static void setNamenodeServicename(String namenodeServicename) {
        NAMENODE_SERVICENAME = namenodeServicename;
    }

    public static String getDIRECTORY() {
        return DIRECTORY;
    }

    public static void setDIRECTORY(String DIRECTORY) {
        EnvironmentSetup.DIRECTORY = DIRECTORY;
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

    public static int getNumberOfMappers() {
        return NUMBER_OF_MAPPERS;
    }

    public static void setNumberOfMappers(int numberOfMappers) {
        NUMBER_OF_MAPPERS = numberOfMappers;
    }

    public static boolean createDirectory(String name) {

        File folder = new File(EnvironmentSetup.DIRECTORY + "/" + name);
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
