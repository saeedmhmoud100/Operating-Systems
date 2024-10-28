package Commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class rmdirCommand extends BaseCommand{
    public rmdirCommand() {
        super("rmdir", List.of());
        this.useRegex = true;
    }

    @Override
    protected void help() {
        System.out.println("Usage: mkdir <directory>");
        System.out.println("Create a directory");
    }


    @Override
    protected void executeCommand(String command) {
        try {
            Path path = BaseCommand.currentPath.resolve(args.get(0));
            if (Files.exists(path) && Files.isDirectory(path)){
                Files.delete(path);
            } else {
                System.out.println("Directory does not exist");
            }
        } catch (Exception e) {
            System.out.println("Invalid directory");
        }
    }
}
