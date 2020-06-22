package mapreduce;

public class JobInfo {
    private String ID;
    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public String getID() {
        return this.ID;
    }


}
