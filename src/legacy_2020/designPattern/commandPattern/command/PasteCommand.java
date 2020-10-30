package designPattern.commandPattern.command;

import designPattern.commandPattern.Document;

public class PasteCommand extends AbstractCommand {
    private Document document;
    private int position;

    public PasteCommand(Document document, int position) {
        this.document = document;
        this.position = position;

        manager.executeCommand(this);
    }

    @Override
    public boolean execute() {
        // document에 붙여넣는 로직
        return false;
    }

    @Override
    public boolean undo() {
        return false;
    }
}
