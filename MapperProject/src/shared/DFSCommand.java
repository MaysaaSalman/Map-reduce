package shared;

import dfs.DFSOps;
import dfs.HDFSBlock;

import java.io.Serializable;


public class DFSCommand implements Serializable {
    //   private static final long serialVersionUID = 1;
    //   private static final int CURRENT_SERIAL_VERSION = 1;

    private static final long serialVersionUID = 3458530288267757690L;

    private DFSOps dfsOp;
    private DataNodeInfo dataNodeInfo;
    private HDFSBlock hdfsBlock;
    private String data;
    private long commandID;

    public DFSCommand(long id, DFSOps dfsOps, DataNodeInfo dataNodeInfo, HDFSBlock hdfsBlock, String data) {/**STORE_BLOCK Constructor this command created by the NameNode**/
        this.dfsOp = dfsOps;
        this.dataNodeInfo = dataNodeInfo;
        this.hdfsBlock = hdfsBlock;
        //this.data=new ArrayList<>();
        //Collections.copy(this.data,data);
        this.data = data;
        commandID = id;
    }

    public DFSCommand(long id, DFSOps dfsOps, DataNodeInfo dataNodeInfo) {/**JOIN Constructor  this command created by the dataNodes**/
        this.dfsOp = dfsOps;
        this.dataNodeInfo = dataNodeInfo;
        commandID = id;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public DFSOps getDfsOp() {
        return dfsOp;
    }

    public void setDfsOp(DFSOps dfsOp) {
        this.dfsOp = dfsOp;
    }

    public DataNodeInfo getDataNodeInfo() {
        return dataNodeInfo;
    }

    public void setDataNodeInfo(DataNodeInfo dataNodeInfo) {
        this.dataNodeInfo = dataNodeInfo;
    }

    public HDFSBlock getHdfsBlock() {
        return hdfsBlock;
    }

    public void setHdfsBlock(HDFSBlock hdfsBlock) {
        this.hdfsBlock = hdfsBlock;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getCommandID() {
        return commandID;
    }

    public void setCommandID(long commandID) {
        this.commandID = commandID;
    }

    @Override
    public String toString() {
        return "DFSCommand{" +
                "dfsOp=" + dfsOp +
                ", dataNodeInfo=" + dataNodeInfo +
                ", hdfsBlock=" + hdfsBlock +
                ", data='" + data + '\'' +
                ", commandID=" + commandID +
                '}';
    }
}
