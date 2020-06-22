package dfs;

import main.Commander;
import shared.DFSCommand;
import shared.DataNodeInfo;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class HDFSBlock implements Serializable {

    private String blockFileName;
    private long ID;
    private ConcurrentHashMap<Integer, DataNodeInfo> repIDtoLoc;
    private String blockFolderName;

    private static final long serialVersionUID = 6529685988267757690L;

    public HDFSBlock(String blockFileName, long ID, String data, List<DataNodeInfo> locations) {
        this.blockFileName = blockFileName;
        this.ID = ID;
        this.repIDtoLoc = new ConcurrentHashMap<>();
        for (int i = 0; i < locations.size(); i++) {
            this.repIDtoLoc.put(i, locations.get(i));
        }
        for (Integer repID : this.repIDtoLoc.keySet()) {
            Commander.getCommandsToSend().add(new DFSCommand(Commander.getIncCommandId(), DFSOps.STORE_BLOCK, repIDtoLoc.get(repID), this, data));
        }
    }


    public long getID() {
        return ID;
    }

    public int get(List<String> data) {
        //TODO Get the Data from the HDFS block
        return -1;
    }

    public String getBlockFileName() {
        return blockFileName;
    }

    public void setBlockFileName(String blockFileName) {
        this.blockFileName = blockFileName;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public ConcurrentHashMap<Integer, DataNodeInfo> getRepIDtoLoc() {
        return repIDtoLoc;
    }

    public void setRepIDtoLoc(ConcurrentHashMap<Integer, DataNodeInfo> repIDtoLoc) {
        this.repIDtoLoc = repIDtoLoc;
    }

    public String getBlockFolderName() {
        return blockFolderName;
    }

    public void setBlockFolderName(String blockFolderName) {
        this.blockFolderName = blockFolderName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}