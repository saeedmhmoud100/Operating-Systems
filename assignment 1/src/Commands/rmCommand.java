package Commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class rmCommand extends BaseCommand{
    public rmCommand() {
        super("rm", List.of());
        this.useRegex = true;
        this.minArgs = 1;
        this.maxArgs = 1;
    }

    @Override
    protected String help() {
        return ("rm: rm [file]\n" +
            "    Remove a file\n");
    }


    @Override
    protected String executeCommand(String command) {
        StringBuilder result = new StringBuilder();
        try {
            Path path = BaseCommand.currentPath.resolve(args.get(0));
            if (Files.exists(path) && !Files.isDirectory(path)){
                Files.delete(path);
            } else {
                result.append("File does not exist").append("\n");
            }
        } catch (Exception e) {
            result.append("Invalid File Name").append("\n");
        }

        return result.toString();
    }
}
