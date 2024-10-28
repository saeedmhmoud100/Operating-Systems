package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class BaseCommand {
    public static Path currentPath;
    boolean useRegex = false;
    protected static final Pattern DIR_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_.-]+$");
    List<String> allArgsAvailable;
    String main_command;
    List<String> args = new ArrayList<String>();

    protected BaseCommand(String main_command, List<String> allArgsAvailable) {
        this.main_command = main_command;
        this.allArgsAvailable = new ArrayList<>(allArgsAvailable);
        this.allArgsAvailable.add("-h");
        this.allArgsAvailable.add("--help");
        this.allArgsAvailable.add("help");
        this.allArgsAvailable.add("-help");
    }

    abstract protected void help();

    protected boolean ValidateArgs(List<String> args) {
        boolean valid = false;
        try {
            valid = true;
            if (!args.isEmpty()) {
                for (String arg : args) {
                    if (this.allArgsAvailable.contains(arg)) {
                        this.args.add(arg);
                    } else {
                        if(useRegex){
                            if (DIR_NAME_PATTERN.matcher(arg).matches()) {
                                this.args.add(arg);
                            }
                        }else{
                            throw new IllegalArgumentException("Invalid argument: " + arg);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            valid = false;
        }
        return valid;
    }


    protected String getFullCommand() {
        StringBuilder command;
        String currentPath = BaseCommand.currentPath.toString();
        command = new StringBuilder("cd '" + currentPath + "' && "+ main_command);
        for (String arg : this.args) {
            command.append(" ").append(arg);
        }
        return command.toString();
    }


    protected void executeCommandForLinux(String command) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(new String[]{"/bin/sh", "-c",command});

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                int exitCode = process.waitFor();
            } catch (InterruptedException e) {
                System.out.println("Process was interrupted: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void executeCommandForWindows(String command) {
        this.executeCommandForLinux(command);
    }

    protected void executeCommand(String command) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            executeCommandForWindows(command);
        } else {
            executeCommandForLinux(command);
        }
    }

    public void execute(List<String> args) {

        if (!this.ValidateArgs(args)) {
            return;
        }
        if (this.args.contains("-h") || this.args.contains("--help") || this.args.contains("-help") || this.args.contains("help")) {
            this.help();
            return;
        }
        String command = this.getFullCommand();
        executeCommand(command);
    }


}
