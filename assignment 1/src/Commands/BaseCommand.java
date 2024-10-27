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
    }

    private boolean ValidateArgs(List<String> args) {
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

    public void execute(List<String> args) {

        if (!this.ValidateArgs(args)) {
            return;
        }

        StringBuilder command;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            command = new StringBuilder("cmd /c dir");
            for (String arg : this.args) {
                command.append(" ").append(arg);
            }
        }else{
            command = new StringBuilder("ls");
            for (String arg : this.args) {
                command.append(" ").append(arg);
            }
        }

        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command.toString());
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


}
