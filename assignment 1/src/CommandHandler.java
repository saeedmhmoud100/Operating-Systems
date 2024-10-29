import Commands.*;

import java.nio.file.Path;
import java.util.*;

public class CommandHandler {
    private Scanner scanner;
    private Map<String, Class<? extends BaseCommand>> commandMap;

    public CommandHandler() {
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));

        commandMap = new HashMap<>();
        commandMap.put("ls", ListCommand.class);
        commandMap.put("cd", CdCommand.class);
        commandMap.put("mkdir", mkdirCommand.class);
        commandMap.put("rmdir", rmdirCommand.class);
        commandMap.put("rm", rmCommand.class);
        commandMap.put("mv", mvCommand.class);
        commandMap.put("cp", cpCommand.class);
        commandMap.put("pwd", PwdCommand.class);
        commandMap.put("date", DateCommand.class);
        commandMap.put("touch", TouchCommand.class);
        commandMap.put("cat", catCommand.class);
        commandMap.put("clear", clearCommand.class);
        commandMap.put("help", HelpCommand.class);
        commandMap.put("exit", exitCommand.class);
        scanner = new Scanner(System.in);
    }

    private List<String> getInput() {
        String input = scanner.nextLine();
        return BaseCommand.ParseInputStringToList(input);
    }

    public boolean executeCommand() {

        List input = this.getInput();
        if (input.size() == 0) {
            return true;
        }
        String command = (String) input.get(0);
        BaseCommand commandObj = null;


        Class<? extends BaseCommand> commandClass = commandMap.get(command);

        if (commandClass != null) {
            try {
                if(commandClass == exitCommand.class){
                    return false;
                }else{
                    commandObj = commandClass.getDeclaredConstructor().newInstance();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Command not found");
            return true;
        }
        commandObj.execute(input);
        return true;
    }


    public void run(){
        System.out.println("Welcome to the Command Line System! Type 'help' for commands.");
        while (true) {
            String currentPath = BaseCommand.currentPath.toString();
            System.out.print("(Command Line) "+currentPath +"> ");
            if (!this.executeCommand()) {
                break;
            }
            System.out.print('\n');
        }
    }
}
