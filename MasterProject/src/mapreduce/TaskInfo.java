package mapreduce;

import shared.DataNodeInfo;

import java.io.Serializable;

public class TaskInfo implements Serializable {
    private static final long serialVersionUID = 380079792677570L;


    private long BlockId;
    private long RecordNum;
    private Record maxRecord;
    private Record minRecord;
    private String ContextPath;
    private JobType jobType;
    private DataNodeInfo dataNodeInfo;


    public TaskInfo(long blockId, long recordNum, Record maxRecord, Record minRecord, String contextPath, JobType jobType, DataNodeInfo dataNodeInfo) {
        BlockId = blockId;
        RecordNum = recordNum;
        this.maxRecord = maxRecord;
        this.minRecord = minRecord;
        ContextPath = contextPath;
        this.jobType = jobType;
        this.dataNodeInfo = dataNodeInfo;
    }

    public TaskInfo(long blockId, DataNodeInfo dataNodeInfo) {
        BlockId = blockId;
        this.dataNodeInfo = dataNodeInfo;

    }

    public DataNodeInfo getDataNodeInfo() {
        return dataNodeInfo;
    }

    public long getBlockId() {
        return BlockId;
    }

    public void setBlockId(long blockId) {
        BlockId = blockId;
    }

    public long getRecordNum() {
        return RecordNum;
    }

    public void setRecordNum(long recordNum) {
        RecordNum = recordNum;
    }

    public Record getMaxRecord() {
        return maxRecord;
    }

    public void setMaxRecord(Record maxRecord) {
        this.maxRecord = maxRecord;
    }

    public Record getMinRecord() {
        return minRecord;
    }

    public void setMinRecord(Record minRecord) {
        this.minRecord = minRecord;
    }

    public String getContextPath() {
        return ContextPath;
    }

    public void setContextPath(String contextPath) {
        ContextPath = contextPath;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }
}
