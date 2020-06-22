package main;


import dfs.DataNode;
import dfs.NameNode;
import shared.DFSCommand;

import static dfs.DFSOps.SUCCESSFL_JOIN;
import static dfs.DFSOps.SUCCESSFUL_STORED;

/**
 * To handle incoming commands
 **/
public class DFSHandler implements Runnable {
    private DFSCommand command;

    public DFSHandler(DFSCommand command) {
        this.command = command;
    }

    @Override
    public void run() {
        switch (command.getDfsOp()) {
            case STORE_BLOCK:
                DataNode.getFileSystem().writeBlock(command.getData(), "BLOCK" + command.getCommandID(), command.getHdfsBlock());
                Commander.getCommandsToSend().add(new DFSCommand(command.getCommandID(), SUCCESSFUL_STORED, command.getDataNodeInfo()));
                break;
            case SUCCESSFL_JOIN:
                Commander.getInExecution().remove(Math.abs(command.getCommandID()));
                break;
            case FAIL_JOIN:
                if (isFirstError()) {
                    command.setCommandID( command.getCommandID() * (-1));
                    Commander.getCommandsToSend().add(Commander.getInExecution().get(Math.abs(command.getCommandID())));
                } else {
                    throw new RuntimeException();
                }
                break;
            case JOIN:
                NameNode.addDataNode(command.getDataNodeInfo());
                Commander.getCommandsToSend().add(new DFSCommand(command.getCommandID(), SUCCESSFL_JOIN, command.getDataNodeInfo()));
                break;
            case SUCCESSFUL_STORED:
                Commander.getInExecution().remove(Math.abs(command.getCommandID()));
                break;
            case Finish:
                Commander.setFinished(true);
                break;
            case FAIL_STORE:
                if (isFirstError()) {
                    command.setCommandID(  command.getCommandID() * (-1));
                    Commander.getCommandsToSend().add(Commander.getInExecution().get(Math.abs(command.getCommandID())));
                } else {
                    throw new RuntimeException();
                }
                break;
            default:
                throw new IllegalArgumentException(command.getDfsOp() + ":Is not DataNode command");

        }
    }

    private boolean isFirstError() {
        if (command.getCommandID() > 0)
            return true;
        return false;
    }
}
