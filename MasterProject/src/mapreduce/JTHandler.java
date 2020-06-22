package mapreduce;

public class JTHandler implements Runnable {
    /**
     * To handle incoming commands to manage Jobs and Tasks
     **/
    JTCommand command;

    public JTHandler(JTCommand command) {
        this.command = command;
    }


    @Override
    public void run() {
        switch (command.getType()) {
            /** Mapper **/
            case Map:
                break;

            case BlockMap:
                break;

            case Reduce:
                break;

            case BlockReduce:
                break;


            /**Master **/
            case MapDone:
                break;

            case ReducedDone:
                break;

            case ReducedFailed:
                break;

            case MapFailed:
                break;


            default:

        }
    }
}
