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
        this.minArgs = 1;
        this.maxArgs = 2;
    }

    @Override
    protected void help() {
        System.out.println("Usage: cp <source> <destination>");

    }

    @Override
    protected Pattern getRegexPattern() {
        return Pattern.compile("^[a-zA-Z0-9_.\\-/:\\\\ ]+$");
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
