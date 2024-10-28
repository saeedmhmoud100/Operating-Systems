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
    protected void help() {
        System.out.println("mv: mv [source] [destination]");
        System.out.println("\tMove a file or directory to another location.");
        System.out.println("\t[source]: The source file or directory to move.");
        System.out.println("\t[destination]: The destination file or directory to move to.");
    }

    @Override
    protected Pattern getRegexPattern() {
        return Pattern.compile("^[a-zA-Z0-9_.\\-/:\\\\ ]+$");
    }

    @Override
    protected void executeCommand(String Command) {
        try {
                String fileName = args.get(0);
                Path source = currentPath.resolve(fileName);
                if(!Files.exists(source)){
                    throw new IllegalArgumentException("mv: cannot move '" + fileName + "': No such file or directory");
                }
                Path destination = Paths.get(args.get(1));
                if(!Files.exists(destination) || !Files.isDirectory(destination)){
                    System.out.println(source);
                    throw new IllegalArgumentException("mv: cannot move '" + destination + "': No such file or directory");
                }
                Files.move(source, destination.resolve(source.getFileName()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
