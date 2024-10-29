package Commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class mvCommand extends BaseCommand{
    public mvCommand() {
        super("mv", List.of());
        this.useRegex = true;
        this.minArgs = 1;
        this.maxArgs = 2;
    }

    @Override
    protected String help() {
        return ("mv: mv [source] [destination]\n" +
                "    Move a file or directory to another location.\n")
                + "    [source]: The source file or directory to move.\n"
                + "    [destination]: The destination file or directory to move to.\n";
    }
    @Override
    protected String executeCommand(String Command) {
        StringBuilder result = new StringBuilder();
        try {
                String fileName = args.get(0);
                Path source = currentPath.resolve(fileName);
                if(!Files.exists(source)){
                    throw new IllegalArgumentException("mv: cannot move '" + fileName + "': No such file or directory");
                }
                Path destination = Paths.get(args.get(1));
                if(!Files.exists(destination) || !Files.isDirectory(destination)){
                    throw new IllegalArgumentException("mv: cannot move '" + destination + "': No such file or directory");
                }
                Files.move(source, destination.resolve(source.getFileName()));
        } catch (Exception e) {
            result.append(e.getMessage()).append("\n");
        }
        return result.toString();
    }
}
