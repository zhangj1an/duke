package dukepkg.commands;
import dukepkg.*;
import dukepkg.exceptions.FormatException;

/**
 * The range of commands to interact with the bot.
 */
public abstract class Command {

    /**
     * Parses the input line as a command to modify existing tasks.
     *
     * @param arr the input line passed in by user.
     * @return a command used for modifying existing tasks. Either a done command or a delete command.
     */
    public static Command getModifyExistingTaskCommand(String[] arr) {
        int index = Integer.parseInt(arr[1]) - 1;
        if(arr[0].equals("done")) {
            return new DoneCommand(index);
        } else {
            return new DeleteCommand(index);
        }
    }

    /**
     * Parses the input line as a command to add new tasks.
     *
     * @param arr the input line passed in by user.
     * @return a command used for adding new tasks. Either a todo command, a deadline command or a event command.
     * @throws FormatException when the user input is incompatible with existing command formats.
     */
    public static Command getAddTaskCommand(String[] arr) throws FormatException {
        Task t = new Todo(arr[1]);
        if (!arr[0].equals("todo")) {
            if(arr[0].equals("deadline")) {
                Parser.validateDeadlineFormat(arr);
                t = Parser.standardizeDeadlineTime(arr);
                return new DeadlineCommand(t);
            } else {
                Parser.validateEventFormat(arr);
                t = Parser.standardizeEventTime(arr);
                return new EventCommand(t);
            }
        }
        return new TodoCommand(t);
    }

    /**
     * An abstract method to execute the user command.
     *
     * @param tasklist the TaskList object for adding and deleting tasks.
     * @param ui       the Ui object for raising prompts after executing tasks.
     * @param storage  the Storage object to save latest changes in the tasklist.
     * @throws FormatException when the user input is incompatible with existing command formats.
     */
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws FormatException;

    /**
     * Flag of whether the program should terminate.
     *
     * @return the boolean.
     */
    public boolean isExit() {
        return false;
    }
}
