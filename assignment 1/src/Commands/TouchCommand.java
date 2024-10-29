package Commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TouchCommand extends BaseCommand {

    public TouchCommand() {
        super("touch", List.of());
        this.useRegex = true;
        this.minArgs = 1;
        this.maxArgs = 1;
    }

    @Override
    protected void help() {
        System.out.println("Usage: touch <filename>");
    }

    @Override
    protected String executeCommandForWindows(String Command){
        StringBuilder result = new StringBuilder();
        Path newDir = BaseCommand.currentPath;
        try {
            Files.createFile(newDir.resolve(this.args.get(0)));
        } catch (Exception e) {
            result.append("Error creating file").append("\n");
        }
        return result.toString();
    }

}
