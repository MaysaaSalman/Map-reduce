package mapreduce;

import java.util.ArrayList;

public class JTHandler implements Runnable {
    /**
     * To handle incoming commands to manage Jobs and Tasks
     **/
    JTCommand command;

    public JTHandler(JTCommand command) {
        this.command = command;

        System.out.println("YYYYYYYYYYYY");
        System.out.println(command);
        System.out.println(command.getType());
        switch (command.getType()) {
            /** Mapper **/
            case Map:

                break;

            case BlockMap:
                System.out.println("PPPPPPPPPPPPPPPPPPPPPP");
                TaskTracker.getTasks().add(command.getTaskInfo());

                //     try {
                //       Mapper.map("","Maysaa at map",new Context("","","",true));
                //
                //     } catch (IOException e) {
                //         e.printStackTrace();
                //     } catch (InterruptedException e) {
                //         e.printStackTrace();
                //     }
                break;

            case Reduce:
                break;

            case BlockReduce:
                break;


            /**Master **/
            case endMap:
                System.out.println("UUUUUUUUUUUUUUUUUUUU");
                Mapper.map();
                System.out.println("Done");
                TaskTracker.finished();

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


    @Override
    public void run() {
        switch (command.getType()) {
            /** Mapper **/
            case Map:

                break;

            case BlockMap:
                System.out.println("PPPPPPPPPPPPPPPPPPPPPP");
                TaskTracker.getTasks().add(command.getTaskInfo());

                //     try {
                //       Mapper.map("","Maysaa at map",new Context("","","",true));
                //
                //     } catch (IOException e) {
                //         e.printStackTrace();
                //     } catch (InterruptedException e) {
                //         e.printStackTrace();
                //     }
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
