package designPattern.commandPattern.command;

public abstract class AbstractCommand {
    public final static CommandManager manager = new CommandManager();

    // 명령어 실행 (올바르게 처리되면 True, else False)
    public abstract boolean execute();

    // 취소 (올바르게 처리되면 True, else False)
    public abstract boolean undo();
}
