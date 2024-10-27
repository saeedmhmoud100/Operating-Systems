package Commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TouchCommand extends BaseCommand {

    public TouchCommand() {
        super("touch", List.of());
        this.useRegex = true;
    }

    @Override
    protected void help() {
        System.out.println("Usage: touch <filename>");
    }

    @Override
    protected void executeCommandForWindows(String Command){
        Path newDir = BaseCommand.currentPath;
        try {
            Files.createFile(newDir.resolve(this.args.get(0)));
        } catch (Exception e) {
            System.out.println("Error creating file");
        }
    }

}
