package Commands;

import java.util.List;

public class HelpCommand extends BaseCommand{
    public HelpCommand() {
        super("help", List.of());
    }

    @Override
    public void execute(List<String> args) {
        System.out.println("Commands:");
        System.out.println("ls - List files in the current directory");
        System.out.println("cd - Change directory");
        System.out.println("mkdir - Create a directory");
        System.out.println("rmdir - Remove a directory");
        System.out.println("touch - Create a file");
        System.out.println("rm - Remove a file");
        System.out.println("mv - Move a file");
        System.out.println("cp - Copy a file");
        System.out.println("cat - Display the contents of a file");
        System.out.println("pwd - Show the current directory");
        System.out.println("clear - Clear the screen");
        System.out.println("date - Show the current date and time");
        System.out.println("help - Show this message");
        System.out.println("exit - Exit the program");
    }
}
