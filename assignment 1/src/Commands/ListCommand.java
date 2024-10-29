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
    protected String help() {
        return "Usage: ls [OPTION]... [FILE]...\n"
                + "List information about the FILEs (the current directory by default).\n"
                + "Mandatory arguments to long options are mandatory for short options too.\n"
                + "  -A, --almost-all           do not list implied . and ..\n"
                + "  -a, --all                  do not ignore entries starting with .\n"
                + "  -l                         use a long listing format\n"
                + "  -r, --reverse              reverse order while sorting\n"
                + "  -t                         sort by modification time, newest first\n"
                + "  -S                         sort by file size\n"
                + "  -R, --recursive            list subdirectories recursively\n"
                + "  -h, --human-readable       with -l and -s, print sizes like 1K 234M 2G etc.\n";

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
