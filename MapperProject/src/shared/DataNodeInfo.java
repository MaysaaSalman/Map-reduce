package shared;

import java.io.Serializable;
import java.util.HashSet;

public class DataNodeInfo implements Comparable<DataNodeInfo>, Serializable {
    private static final long serialVersionUID = 1970386480274659373L;

    private String id;
    private int load;
    private String dataNodeName;
    private String Directory;
    private HashSet<String> files;
    private int dataNodeNum;

    public DataNodeInfo(String id) {
        this.id = id;
        this.load = 1;
        this.dataNodeNum = dataNodeNum;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public String getDataNodeName() {
        return dataNodeName;
    }

    public void setDataNodeName(String dataNodeName) {
        this.dataNodeName = dataNodeName;
    }

    public HashSet<String> getFiles() {
        return files;
    }

    public void setFiles(HashSet<String> files) {
        this.files = files;
    }

    public int getDataNodeNum() {
        return dataNodeNum;
    }

    public void setDataNodeNum(int dataNodeNum) {
        this.dataNodeNum = dataNodeNum;
    }

    @Override
    public int compareTo(DataNodeInfo o) {
        if (o == null)
            throw new NullPointerException();
        return load - o.load;
    }

    public void setDirectory(String directory) {
        this.Directory = directory;
    }

    public String getDirectory() {
        return Directory;
    }

    @Override
    public String toString() {
        return "DataNodeInfo{" +
                "id='" + id + '\'' +
                ", load=" + load +
                ", dataNodeName='" + dataNodeName + '\'' +
                ", Directory='" + Directory + '\'' +
                ", files=" + files +
                ", dataNodeNum=" + dataNodeNum +
                '}';
    }
}