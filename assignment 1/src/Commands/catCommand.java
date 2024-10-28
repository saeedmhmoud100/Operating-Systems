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
    protected void executeCommandForWindows(String command){
        try {
            Path path = currentPath.resolve(args.get(0));
            if (Files.isDirectory(path)) {
                System.out.println("cat: " + args.get(0) + ": Is a directory");
            } else if (Files.exists(path)) {
                Files.lines(path).forEach(System.out::println);
            } else {
                System.out.println("cat: " + args.get(0) + ": No such file or directory");
            }
        } catch (Exception e) {
            System.out.println("cat: " + args.get(0) + ": No such file or directory");
        }
    }

}
