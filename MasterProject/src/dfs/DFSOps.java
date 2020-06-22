package dfs;

import java.io.Serializable;

public enum DFSOps implements Serializable {

    JOIN, STORE_BLOCK, SUCCESSFL_JOIN, SUCCESSFUL_STORED, FAIL_STORE, FAIL_JOIN, Finish;

    private static final long serialVersionUID = 6529203988267757690L;

}
