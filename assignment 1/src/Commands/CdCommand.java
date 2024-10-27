package Commands;

import java.io.File;
import java.util.List;

public class CdCommand extends BaseCommand {

    public CdCommand() {
        super("cd", List.of("../", ".."));
        this.useRegex = true;
    }

    @Override
    protected void help() {
        System.out.println("Usage: cd [directory]");
        System.out.println("Change the current directory to the specified directory.");
    }

    @Override
    protected void executeCommandForLinux(String Command){
        String newDir = System.getProperty("user.dir");
        if (args.get(0).equals("../") || args.get(0).equals("..")) {
            newDir = newDir.substring(0, newDir.lastIndexOf("/"));
        } else {
            newDir = newDir + "/" + args.get(0);
        }
        File dir = new File(newDir);
        if (dir.exists() && dir.isDirectory()) {
            System.setProperty("user.dir", newDir);
        } else {
            System.out.println("Directory does not exist");
        }
    }
    @Override
    protected void executeCommandForWindows(String Command) {
        String newDir = System.getProperty("user.dir");
        if (args.get(0).equals("../") || args.get(0).equals("..")) {
            newDir = newDir.substring(0, newDir.lastIndexOf("\\"));
        } else {
            newDir = newDir + "\\" + args.get(0);
        }
        File dir = new File(newDir);
        if (dir.exists() && dir.isDirectory()) {
            System.setProperty("user.dir", newDir);
        } else {
            System.out.println("Directory does not exist");
        }
    }

}
