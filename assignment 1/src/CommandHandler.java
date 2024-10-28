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
        commandMap.put("pwd", PwdCommand.class);
        commandMap.put("date", DateCommand.class);
        commandMap.put("touch", TouchCommand.class);
        commandMap.put("cat", catCommand.class);
        commandMap.put("clear", clearCommand.class);
        commandMap.put("help", HelpCommand.class);
        scanner = new Scanner(System.in);
    }

    private List<String> getInput() {
        String input = scanner.nextLine();
        List<String> args = new ArrayList<>();
        boolean inQuotes = false;

        StringBuilder currentArg = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == '\"') {
                inQuotes = !inQuotes;
            } else if (c == ' ' && !inQuotes) {
                if (currentArg.length() > 0) {
                    args.add(currentArg.toString());
                    currentArg.setLength(0);
                }
            } else {
                currentArg.append(c);
            }
        }
        if (currentArg.length() > 0) {
            args.add(currentArg.toString());
        }
        return args;
    }

    public boolean executeCommand() {

        List input = this.getInput();
        String command = (String) input.get(0);
        List arguments = input.subList(1, input.size());
        BaseCommand commandObj = null;


        Class<? extends BaseCommand> commandClass = commandMap.get(command);

        if (commandClass != null) {
            try {
                if (commandClass == ListCommand.class) {
                    commandObj = new ListCommand();
                } else {
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
        commandObj.execute(arguments);
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
