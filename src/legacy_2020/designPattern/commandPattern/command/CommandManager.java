package designPattern.commandPattern.command;

import java.util.Deque;
import java.util.LinkedList;

/* 1. 각각의 명령어 객체를 저장하고 관리해주는 관리자 클래스.
 * 2. 실행되는 명령어를 차례대로 저장한다.
 * 3. 사용자가 '명령취소'를 요청하면 최근에 실행된 명령어 객체의 undo() 메소드를 호출한다.
 * */
public class CommandManager {
    private static final int MAX_HISTORY_LENGTH = 50;

    private final Deque<AbstractCommand> history = new LinkedList<>();   // 지우는게 빈번하면 Linked가 나음
    private final Deque<AbstractCommand> redoList = new LinkedList<>();

    void executeCommand(AbstractCommand command) {
        if (command instanceof Redo) {
            redo();
            return;
        }

        if (command instanceof Undo) {
            undo();
            return;
        }

        if (command.execute()) {
            addToHistory(command);
        } else {
            System.err.println("Occured Error");
        }
    }

    void addToHistory(AbstractCommand command) {
        history.addLast(command);
        while (history.size() > MAX_HISTORY_LENGTH) history.pollFirst();
    }

    private void undo() {
        if (history.size() > 0) {
            AbstractCommand undoCommand = history.removeLast();
            undoCommand.undo();
            redoList.addLast(undoCommand);
        }
    }

    private void redo() {
        if (redoList.size() > 0) {
            AbstractCommand redoCommand = redoList.removeLast();
            redoCommand.execute();
            history.addLast(redoCommand);
        }
    }
}

interface Redo {

}

interface Undo {

}