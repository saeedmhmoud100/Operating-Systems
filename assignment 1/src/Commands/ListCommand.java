package Commands;

import java.io.File;
import java.util.List;

public class ListCommand extends BaseCommand {
    public ListCommand() {
        super("ls", List.of("-A", "-a", "--all", "-l", "-r", "--reverse", "-t", "-S", "-R", "--recursive", "-h", "--human-readable"));
        this.minArgs = 0;
        this.maxArgs = 1;
    }

    @Override
    protected void help() {
        System.out.println("Usage: ls [OPTION]... [FILE]...");
        System.out.println("List information about the FILEs (the current directory by default).");
//        System.out.println("Sort entries alphabetically if none of -cftuvSUX nor --sort is specified.");
        System.out.println();
        System.out.println("Mandatory arguments to long options are mandatory for short options too.");
        System.out.println("  -A, --almost-all           do not list implied . and ..");
        System.out.println("  -a, --all                  do not ignore entries starting with .");
        System.out.println("  -l                         use a long listing format");
        System.out.println("  -r, --reverse              reverse order while sorting");
        System.out.println("  -t                         sort by modification time, newest first");
        System.out.println("  -S                         sort by file size");
        System.out.println("  -R, --recursive            list subdirectories recursively");
        System.out.println("  -h, --human-readable       with -l and -s, print sizes like 1K 234M 2G etc.");
    }

    @Override
    protected String executeCommandForWindows(String command) {

        File dir = BaseCommand.currentPath.toFile();
        File[] files = dir.listFiles();
        StringBuilder res = new StringBuilder();
        if (files != null) {
            for (File file : files) {
                res.append(file.getName() + "\n");
            }
        }
        return res.toString();
    }

}
