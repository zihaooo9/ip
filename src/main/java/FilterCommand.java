import java.time.LocalDate;

public class FilterCommand extends Command {
    private LocalDate filterDate;

    public FilterCommand(LocalDate filterDate) {
        this.filterDate = filterDate;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.formatPrint(taskList.filterByDate(this.filterDate));
    }
}
