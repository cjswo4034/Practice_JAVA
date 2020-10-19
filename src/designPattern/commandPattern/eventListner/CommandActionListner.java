package designPattern.commandPattern.eventListner;

import designPattern.commandPattern.CommandFactory;
import designPattern.commandPattern.command.AbstractCommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandActionListner implements ActionListener {
    private static final CommandFactory commandFactory = new CommandFactory();

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCmd = e.getActionCommand();
        AbstractCommand command = commandFactory.getCommand(actionCmd);
        command.execute();
    }
}
