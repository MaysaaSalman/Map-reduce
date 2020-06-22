package dfs;

import shared.DataNodeInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class HDFSFileSystem {
    private ConcurrentHashMap<String, HDFSFile> fileList;
    private static  ConcurrentHashMap<Long, String> blocksLocations = new ConcurrentHashMap<>();
    private ConcurrentHashMap<HDFSFile, ConcurrentHashMap<String, HDFSBlock>> BlockList;

    public HDFSFileSystem() {
        fileList = new ConcurrentHashMap<>();
        BlockList = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, HDFSFile> getFileList() {
        return fileList;
    }

    public void setFileList(ConcurrentHashMap<String, HDFSFile> fileList) {
        this.fileList = fileList;
    }

    public static ConcurrentHashMap<Long, String> getBlocksLocations() {
        return blocksLocations;
    }

    public static void setBlocksLocations(ConcurrentHashMap<Long, String> blocksLocations) {
        HDFSFileSystem.blocksLocations = blocksLocations;
    }

    public ConcurrentHashMap<HDFSFile, ConcurrentHashMap<String, HDFSBlock>> getBlockList() {
        return BlockList;
    }

    public void setBlockList(ConcurrentHashMap<HDFSFile, ConcurrentHashMap<String, HDFSBlock>> blockList) {
        BlockList = blockList;
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
        BlockList.put(file, new ConcurrentHashMap<>());

    }

    public void addBlock(String fileName, long totalBlocksNum, String data, List<DataNodeInfo> replicateDN) {
        HDFSBlock hdfsBlock = new HDFSBlock("Block" + totalBlocksNum, totalBlocksNum, data, replicateDN);
        for (DataNodeInfo dataNodeInfo : replicateDN) {
            BlockList.get(fileList.get("DataNode" + dataNodeInfo.getId())).put(fileName, hdfsBlock);
        }
    }

    public void writeBlock(String data, String fileName, HDFSBlock hdfsBlock) {
        try {


            File file = new File(DataNode.getDataNodeInfo().getDirectory() + "/" + fileName);
            blocksLocations.put(hdfsBlock.getID(), file.getAbsolutePath());
            System.out.println(file.getAbsolutePath());
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
