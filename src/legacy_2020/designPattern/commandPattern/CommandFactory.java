package designPattern.commandPattern;

import designPattern.commandPattern.command.AbstractCommand;
import designPattern.commandPattern.command.PasteCommand;

public class CommandFactory {
    public AbstractCommand getCommand(String key) {
        AbstractCommand command = null;
        if (key.equals("Paste")) {
            command = new PasteCommand(new Document(""), 0);
        }
        return command;
    }
}
