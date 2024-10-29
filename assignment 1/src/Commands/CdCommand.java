package Commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CdCommand extends BaseCommand {

    public CdCommand() {
        super("cd", List.of("../", ".."));
        this.useRegex = true;
        this.minArgs = 1;
        this.maxArgs = 1;
    }

    @Override
    protected String help() {
        return ("cd: cd [directory]\n" +
                "    Change the current directory to the specified directory.\n");
    }

    @Override
    protected String executeCommand(String Command){
        StringBuilder result = new StringBuilder();
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
            result.append("Directory does not exist");
        }

        return result.toString();
    }

}
