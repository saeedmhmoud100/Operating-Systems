package Commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class rmdirCommand extends BaseCommand{
    public rmdirCommand() {
        super("rmdir", List.of());
        this.useRegex = true;
        this.minArgs = 1;
        this.maxArgs = 1;
    }

    @Override
    protected String help() {
        return ("rmdir: rmdir [directory]\n" +
                "    Remove a directory\n");
    }


    @Override
    protected String executeCommand(String command) {
        StringBuilder result = new StringBuilder();
        try {
            Path path = BaseCommand.currentPath.resolve(args.get(0));
            if (Files.exists(path) && Files.isDirectory(path)){
                Files.delete(path);
            } else {
                result.append("Directory does not exist").append("\n");
            }
        } catch (Exception e) {
            result.append("Invalid directory").append("\n");
        }
        return result.toString();
    }
}
