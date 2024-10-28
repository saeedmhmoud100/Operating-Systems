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
    protected void help() {
        System.out.println("Usage: rm <file>");
        System.out.println("Remove a file");
    }


    @Override
    protected void executeCommand(String command) {
        try {
            Path path = BaseCommand.currentPath.resolve(args.get(0));
            if (Files.exists(path) && !Files.isDirectory(path)){
                Files.delete(path);
            } else {
                System.out.println("File does not exist");
            }
        } catch (Exception e) {
            System.out.println("Invalid File Name");
        }
    }
}
