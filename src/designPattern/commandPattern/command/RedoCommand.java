package designPattern.commandPattern.command;

public class RedoCommand extends AbstractCommand implements Redo {
    RedoCommand() {
        manager.executeCommand(this);
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public boolean undo() {
        return false;
    }
}
