package mapreduce;

import main.EnvironmentSetup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;


public class Context<K extends Comparable, V>{
    private String taskTrackerServiceName;
    private String taskid;
    private ArrayList<Record<K, V>> ans;
    private String outPath;
    private Record maxRex;
    private Record minRec;
    private TaskInfo taskInfo;
    private File f;
    private static int counter=0;


    public String getTaskTrackerServiceName() {
        return taskTrackerServiceName;
    }

    public void setTaskTrackerServiceName(String taskTrackerServiceName) {
        this.taskTrackerServiceName = taskTrackerServiceName;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }



    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public Record getMaxRex() {
        return maxRex;
    }

    public void setMaxRex(Record maxRex) {
        this.maxRex = maxRex;
    }

    public Record getMinRec() {
        return minRec;
    }

    public void setMinRec(Record minRec) {
        this.minRec = minRec;
    }

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    public Context(TaskInfo taskInfo) {
        try {


            taskTrackerServiceName = TaskTracker.getJobType().toString();
            this.taskInfo = taskInfo;
            taskid = taskInfo.getBlockId() + "";
            f = new File(EnvironmentSetup.getDIRECTORY());
            if (!f.exists()) {
                f.mkdir();
            }
            outPath = EnvironmentSetup.getDIRECTORY();
            ans = new ArrayList<>();
            if (TaskTracker.getJobType() == JobType.Map) {
                f = new File(outPath + "/mapper" + taskid);
                f.createNewFile();
            } else {
                f = new File(outPath + "/reducer" + taskid);
                f.createNewFile();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Record record) {
        ans.add(record);
    }

    public  ArrayList<Record<K,V>> writeToDisk() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));
            Collections.sort(ans);
          //  TaskTracker.getHeap() [counter]=ans;
            taskInfo.setMaxRecord(ans.get(ans.size() - 1));
            taskInfo.setMinRecord(ans.get(0));
            taskInfo.setContextPath(outPath);
            taskInfo.setRecordNum(ans.size());
            TaskTracker.getCommandsToSend().add(new JTCommand(TaskTracker.getIncCounter(), JobType.MapDone, this.taskInfo, this.taskInfo.getDataNodeInfo()));
            //TODO create taskInfo and send it to the master

            for (Record record : ans) {
                objectOutputStream.writeObject(ans);
           }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

}
