package Commands;

import java.util.List;

public class HelpCommand extends BaseCommand{
    public HelpCommand() {
        super("help", List.of());
        this.maxArgs = 1;
    }

    @Override
    public String executeCommand(String command) {
        StringBuilder result = new StringBuilder();
        result.append("Commands:"+"\n");
        result.append("ls - List files in the current directory"+"\n");
        result.append("cd - Change directory"+"\n");
        result.append("mkdir - Create a directory"+"\n");
        result.append("rmdir - Remove a directory"+"\n");
        result.append("touch - Create a file"+"\n");
        result.append("rm - Remove a file"+"\n");
        result.append("mv - Move a file"+"\n");
        result.append("cp - Copy a file"+"\n");
        result.append("cat - Display the contents of a file"+"\n");
        result.append("pwd - Show the current directory"+"\n");
        result.append("clear - Clear the screen"+"\n");
        result.append("date - Show the current date and time"+"\n");
        result.append(">> - Append output to a file"+"\n");
        result.append("> - Overwrite output to a file"+"\n");
        result.append("help - Show this message"+"\n");
        result.append("exit - Exit the program"+"\n");

        return result.toString();
    }

    @Override
    protected String help() {
        return ("help: help\n" +
                "    Display information about the available commands\n");
    }
}
