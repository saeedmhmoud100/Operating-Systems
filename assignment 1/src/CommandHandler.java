import Commands.CommandInterface;
import Commands.HelpCommand;
import Commands.ListCommand;

import java.util.List;
import java.util.Scanner;

public class CommandHandler {
    private Scanner scanner;

    public CommandHandler() {
        scanner = new Scanner(System.in);
    }

    private List getInput() {
        String input = scanner.nextLine();
        return List.of(input.split(" "));
    }

    public boolean executeCommand() {

        List input = this.getInput();
        String command = (String) input.get(0);
        List arguments = input.subList(1, input.size());
        CommandInterface commandObj = null;


        if(command.equals("ls")) {
            commandObj = new ListCommand(arguments);
        }else if (command.equals("help")) {
            commandObj = new HelpCommand();

        }else{
            System.out.println("Command not found");
            return false;



        }

        commandObj.execute();
        return true;
    }
}
