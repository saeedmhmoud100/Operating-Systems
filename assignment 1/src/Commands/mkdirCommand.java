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
    protected void help() {
        System.out.println("Usage: mkdir <directory>");
        System.out.println("Create a directory");
    }


    @Override
    protected void executeCommand(String command) {
        try {
            Path fullPath = BaseCommand.currentPath.resolve(this.args.get(0));
            Files.createDirectories(fullPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
