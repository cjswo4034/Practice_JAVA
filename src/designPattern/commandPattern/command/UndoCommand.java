package designPattern.commandPattern.command;

public class UndoCommand extends AbstractCommand implements Undo {
    public UndoCommand() {
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
