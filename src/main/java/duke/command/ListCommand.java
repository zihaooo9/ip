package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * ListCommand prints out current task list.
 *
 * @author Chng Zi Hao
 */
public class ListCommand extends Command {

    /**
     * Deletes task from the task list.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage Storage for Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printDivider();
        taskList.printTaskList();
        ui.printDivider();
    }
}
