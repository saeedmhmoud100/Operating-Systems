package Commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CdCommand extends BaseCommand {

    public CdCommand() {
        super("cd", List.of("../", ".."));
        this.useRegex = true;
    }

    @Override
    protected void help() {
        System.out.println("Usage: cd [directory]");
        System.out.println("Change the current directory to the specified directory.");
    }

    @Override
    protected void executeCommand(String Command){
        Path newDir = BaseCommand.currentPath;
        if (args.get(0).equals("../") || args.get(0).equals("..")) {
            newDir = newDir.resolve("../").normalize();
        } else {
            newDir = newDir.resolve(args.get(0));
        }
        if (Files.exists(newDir) && Files.isDirectory(newDir)) {
            BaseCommand.currentPath = newDir;
            System.setProperty("user.dir", newDir.toString());
        } else {
            System.out.println("Directory does not exist");
        }
    }

}
