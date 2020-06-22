package mapreduce;

import dfs.HDFSBlock;
import dfs.NameNode;
import shared.DataNodeInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class JobTracker {

    private static Code code;
    private static Collection<HDFSBlock> Blocks = NameNode.getHdfsFileSystem().Blocks.values();
    private static ConcurrentHashMap<DataNodeInfo, Long> TaskLoad = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<DataNodeInfo, ArrayList<TaskInfo>> mapResult = new ConcurrentHashMap<>();

    public static void setTaskLoad(Collection<DataNodeInfo> dataNodeInfos) {
        for (DataNodeInfo dataNodeInfo :
                dataNodeInfos) {
            TaskLoad.put(dataNodeInfo, 0L);
        }
    }

    public static boolean startMapping() {
        for (DataNodeInfo dataNodeInfo : NameNode.getDataNodes()) {
            Commander.getCommandsToSend().add(new JTCommand(Commander.getIncCounter(), JobType.Map, code, dataNodeInfo));
        }
        Commander.sendJobs();
        int i = 0;
        setTaskLoad(NameNode.getDataNodeList().values());
        for (HDFSBlock hdfsBlock : Blocks) {
            DataNodeInfo temp = minLoad(hdfsBlock.getRepIDtoLoc().values());
            Commander.getCommandsToSend().add(new JTCommand(Commander.getIncCounter(), JobType.BlockMap, new TaskInfo(hdfsBlock.getID(), temp), temp));
            System.out.println(i++);
        }
        Commander.setFinished(true);
        Commander.exec();


        for (DataNodeInfo dataNodeInfo : NameNode.getDataNodes()) {

            Commander.getCommandsToSend().add(new JTCommand(Commander.getIncCounter(), JobType.endMap, dataNodeInfo));

        }
        Commander.sendJobs();


        return true;
    }

    private static DataNodeInfo minLoad(Collection<DataNodeInfo> values) {
        long min = NameNode.getTotalBlocksNum();
        DataNodeInfo maxDataNode = null;
        for (DataNodeInfo dataNodeInfo : values
        ) {
            if (TaskLoad.get(dataNodeInfo) < min) {
                maxDataNode = dataNodeInfo;
                min = TaskLoad.get(maxDataNode);
                TaskLoad.put(dataNodeInfo, min++);
            }

        }
        System.out.println(maxDataNode);
        return maxDataNode;
    }


    public static void startSort() {
        /**
         *
         *sort algorithm is mixed between Merge(locally) and quiq sort(among the clusters)
         */


    }

    public static Code getCode() {
        return code;
    }

    public static void setCode(Code code) {
        JobTracker.code = code;
    }

    public static void addToExc(HDFSBlock hdfsBlock) {
        Blocks.add(hdfsBlock);
    }
}
