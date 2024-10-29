package Commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class mkdirCommand extends BaseCommand{
    public mkdirCommand() {
        super("mkdir", List.of());
        this.useRegex = true;
        this.minArgs = 1;
        this.maxArgs = 1;
    }

    @Override
    protected String help() {
        return ("mkdir: mkdir [directory]\n" +
                "    Create a directory\n");
    }


    @Override
    protected String executeCommand(String command) {
        StringBuilder result = new StringBuilder();
        try {
            Path fullPath = BaseCommand.currentPath.resolve(this.args.get(0));
            Files.createDirectories(fullPath);
        } catch (Exception e) {
            result.append(e.getMessage()).append("\n");
        }
        return result.toString();
    }
}
