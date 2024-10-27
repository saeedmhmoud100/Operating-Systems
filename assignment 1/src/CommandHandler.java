import Commands.BaseCommand;
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
        BaseCommand commandObj = null;


        if(command.equals("ls")) {
            commandObj = new ListCommand();
        }else if (command.equals("help")) {
            commandObj = new HelpCommand();
        }else if(command.equals("exit")){
            return false;
        }else{
            System.out.println("Command not found");
            return true;
        }
        commandObj.execute(arguments);
        return true;
    }
}
