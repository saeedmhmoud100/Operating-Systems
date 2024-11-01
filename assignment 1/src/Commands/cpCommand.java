package Commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class cpCommand extends BaseCommand{
    public cpCommand() {
        super("mv", List.of());
        this.useRegex = true;
        this.minArgs = 2;
        this.maxArgs = 2;
    }

    @Override
    protected String help() {
        return ("cp: cp [source] [destination]\n" +
                "    Copy the file from source to destination\n");
    }

    @Override
    protected String executeCommand(String Command) {
        StringBuilder result = new StringBuilder();
        Path source = BaseCommand.currentPath.resolve(this.args.get(0));
        Path destination = Paths.get(this.args.get(1));

        try {
            if(!Files.exists(source)){
                throw new Exception("Source does not exist");
            }
            if(!Files.exists(destination)){
                throw new Exception("Destination does not exist");
            }
            Files.copy(source, destination.resolve(source.getFileName()));
        } catch (Exception e) {
            result.append("Error: " + e.getMessage()+"\n");
        }
        return result.toString();
    }
}
