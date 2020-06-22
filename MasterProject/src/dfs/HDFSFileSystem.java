package dfs;

import shared.DataNodeInfo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public class HDFSFileSystem {
    public ConcurrentHashMap<String, HDFSFile> fileList;

    public ConcurrentHashMap<HDFSFile, ConcurrentHashMap<String, Long>> HDFSBlockList;
    public ConcurrentHashMap<Long, HDFSBlock> Blocks;

    public HDFSFileSystem() {
        Blocks = new ConcurrentHashMap<>();
        fileList = new ConcurrentHashMap<>();
        HDFSBlockList = new ConcurrentHashMap<>();
    }

    /**
     * public String deleteFile(String filename)
     * {
     * if(fileList.containsKey(filename)==false)
     * return "file does not exist";
     * HDFSFile file = this.fileList.get(filename);
     * String ans = file.delete();
     * fileList.remove(filename);
     * return ans;
     * }
     **/


    public void putFile(String HDFSFileName) {
        HDFSFile file = new HDFSFile(HDFSFileName);
        fileList.put(HDFSFileName, file);
        HDFSBlockList.put(file, new ConcurrentHashMap<>());
    }

    public void addBlock(long totalBlocksNum, String data, List<DataNodeInfo> replicateDN) {
        HDFSBlock hdfsBlock = new HDFSBlock("Block" + totalBlocksNum, totalBlocksNum, data, replicateDN);

        for (DataNodeInfo dataNodeInfo : replicateDN) {
            String fileName = "DataNode" + dataNodeInfo.getId();
            HDFSBlockList.get(fileList.get("DataNode" + dataNodeInfo.getId())).put(fileName, hdfsBlock.getID());
        }
        Blocks.put(hdfsBlock.getID(), hdfsBlock);

        // JobTracker.addToExc(hdfsBlock);
    }

    public HDFSBlock getBlock(long id) {
        if (Blocks.containsKey(id))
            return Blocks.get(id);
        throw new NoSuchElementException();
    }
}
