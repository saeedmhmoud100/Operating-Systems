package Commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class catCommand extends BaseCommand{
    public catCommand() {
        super("cat", List.of());
        this.useRegex = true;
        this.minArgs = 1;
        this.maxArgs = 1;
    }

    @Override
    protected void help() {
        System.out.println("cat: cat [FILE]\n" +
                "    Display the contents of FILE\n" +
                "    If FILE is a directory, display the contents of all files in the directory\n" +
                "    If FILE is not found, display an error message\n" +
                "    If FILE is not a file, display an error message\n" +
                "    If FILE is a directory, display an error message\n");
    }

    @Override
    protected String executeCommandForWindows(String command){
        StringBuilder result = new StringBuilder();
        try {
            Path path = currentPath.resolve(args.get(0));
            if (Files.isDirectory(path)) {
                result.append("cat: " + args.get(0) + ": Is a directory").append("\n");
            } else if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    result.append(line).append("\n");
                }
            } else {
                result.append("cat: " + args.get(0) + ": No such file or directory").append("\n");
            }
        } catch (Exception e) {
            result.append("cat: " + args.get(0) + ": No such file or directory").append("\n");
        }
        return result.toString();
    }

}
