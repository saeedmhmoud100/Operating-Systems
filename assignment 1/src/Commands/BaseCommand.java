package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCommand {

    List<String> allArgsAvailable;
    String main_command;
    List<String> args = new ArrayList<String>();

    protected BaseCommand(String main_command, List<String> allArgsAvailable) {
        this.main_command = main_command;
        this.allArgsAvailable =new ArrayList<>(allArgsAvailable);;
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
                for (int i = 0; i < args.size(); i++) {
                    if (this.allArgsAvailable.contains(args.get(i))) {
                        this.args.add(args.get(i));
                    } else {
                        throw new IllegalArgumentException("Invalid argument: " + args.get(i));
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
        command = new StringBuilder(main_command);
        for (String arg : this.args) {
            command.append(" ").append(arg);
        }
        return command.toString();
    }


    protected void executeCommandForLinux(String command) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            System.out.println("Executing command: " + process.toString());

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

    protected void executeCommandForWindows(String command){
        this.executeCommandForLinux(command);
    };
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
        if(this.args.contains("-h") || this.args.contains("--help") || this.args.contains("-help") || this.args.contains("help")){
            this.help();
            return;
        }
        String command = this.getFullCommand();
        executeCommand(command);
    }


}
