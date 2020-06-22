package mapreduce;

import shared.DataNodeInfo;

import java.io.Serializable;

public class JTCommand implements Serializable {
    private static final long serialVersionUID = 38530276542677570L;

    private long id;
    private JobType type;
    private Code code;
    private DataNodeInfo dataNodeInfo;
    private TaskInfo taskInfo;

    public JTCommand(long id, JobType type, Code code, DataNodeInfo dataNodeInfo) {
        this.id = id;
        this.type = type;
        this.code = code;
        this.dataNodeInfo = dataNodeInfo;
    }


    public JTCommand(long id, JobType type, TaskInfo taskInfo, DataNodeInfo dataNodeInfo) {
        this.id = id;
        this.type = type;
        this.taskInfo = taskInfo;
        this.dataNodeInfo = dataNodeInfo;

    }

    public JTCommand(long id, JobType type, DataNodeInfo dataNodeInfo) {
        this.id = id;
        this.type = type;
        this.dataNodeInfo = dataNodeInfo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public DataNodeInfo getDataNodeInfo() {
        return dataNodeInfo;
    }

    public void setDataNodeInfo(DataNodeInfo dataNodeInfo) {
        this.dataNodeInfo = dataNodeInfo;
    }

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }
}
