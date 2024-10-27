package Commands;

import java.util.List;

public class mkdirCommand extends BaseCommand{
    public mkdirCommand() {
        super("mkdir", List.of());
        this.useRegex = true;
    }

    @Override
    protected void help() {
        System.out.println("Usage: mkdir <directory>");
        System.out.println("Create a directory");
    }


    @Override
    protected void executeCommandForLinux(String command) {
        try {
            Process process = Runtime.getRuntime().exec(new String[] {"/bin/bash", "-c", command});
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void executeCommandForWindows(String command) {
        try {
            Process process = Runtime.getRuntime().exec("cmd /c " + command);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
