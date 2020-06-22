package main;


import dfs.NameNode;
import shared.DFSCommand;

import static dfs.DFSOps.SUCCESSFL_JOIN;

/**
 * To handle incoming commands
 **/
public class DFSHandler implements Runnable {
    DFSCommand command;

    public DFSHandler(DFSCommand command) {
        this.command = command;
    }


    @Override
    public void run() {
        switch (command.getDfsOp()) {

            case JOIN:
                System.out.println(command.getDataNodeInfo().getId() + "joined");
                NameNode.addDataNode(command.getDataNodeInfo());
                Commander.getCommandsToSend().add(new DFSCommand(command.getCommandID(), SUCCESSFL_JOIN, command.getDataNodeInfo()));
                break;
            case SUCCESSFUL_STORED:
                Commander.getInExecution().remove(Math.abs(command.getCommandID()));
                break;
            case FAIL_STORE:
                if (isFirstError()) {
                    command.setCommandID( command.getCommandID() * (-1));

                    Commander.getCommandsToSend().add(Commander.getInExecution().get(Math.abs(command.getCommandID())));
                } else {
                    throw new RuntimeException();
                }
                break;
            default:
                throw new IllegalArgumentException(command.getDfsOp() + ":Is not Master command");

        }
    }

    private boolean isFirstError() {
        if (command.getCommandID() > 0)
            return true;
        return false;
    }
}
