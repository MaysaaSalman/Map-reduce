package mapreduce;

import java.io.Serializable;

public enum JobType implements Serializable {
    Map, Reduce, BlockMap, BlockReduce, endMap, MapDone, MapFailed, ReducedDone, ReducedFailed;
    private static final long serialVersionUID = 3890009866237570L;

}
