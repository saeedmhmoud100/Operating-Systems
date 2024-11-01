package Commands;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class BaseCommand {
    public static Path currentPath;
    boolean useRegex = false;
    int minArgs = 0;
    int maxArgs = Integer.MAX_VALUE;
    int outputToFileIndex = -1;
    protected static final Pattern RegexPattern = Pattern.compile("^[a-zA-Z0-9_.\\-/:\\\\ ]+$");
    List<String> allArgsAvailable;
    String main_command;
    List<String> args = new ArrayList<String>();

    protected BaseCommand(String main_command, List<String> allArgsAvailable) {
        if(currentPath == null){
            currentPath = Path.of(System.getProperty("user.dir"));
        }
        this.main_command = main_command;
        this.allArgsAvailable = new ArrayList<>(allArgsAvailable);
        this.allArgsAvailable.add("-h");
        this.allArgsAvailable.add("--help");
        this.allArgsAvailable.add("help");
        this.allArgsAvailable.add("-help");
        this.allArgsAvailable.add(">");
        this.allArgsAvailable.add(">>");
    }

    abstract protected String help();


    protected Pattern getRegexPattern() {
        return RegexPattern;
    }
    protected String ValidateArgs(List<String> args) {
        int additionalArgs = 0;

        if (this.allArgsAvailable.contains(">") || this.allArgsAvailable.contains(">>")) {
            additionalArgs = 2;
            int index = args.indexOf(">");
            if(index == -1){
                index = args.indexOf(">>");
            }

            if(index != -1){
                if(args.size() > index + 1){
                    this.outputToFileIndex = index +1;
                }
            }
        }

        try {

            if (args.size() < this.minArgs || args.size() > this.maxArgs + additionalArgs) {
                throw new IllegalArgumentException("Invalid number of arguments"
                        + " Expected: " + this.minArgs + " to " + (this.maxArgs + additionalArgs)
                        + "\nGot: " + args.size()
                        + "\nUse " + this.main_command + " -h or --help for help");
            }

            if (!args.isEmpty()) {
                for (String arg : args) {
                    if (this.allArgsAvailable.contains(arg)) {
                        this.args.add(arg);
                    } else {
                        if(useRegex){
                            if (getRegexPattern().matcher(arg).matches()) {
                                this.args.add(arg);
                            }
                        }else if(this.outputToFileIndex!=-1 && (this.outputToFileIndex == args.indexOf(arg) || this.outputToFileIndex+1 == args.indexOf(arg))) {
                            this.args.add(arg);
                        }else{
                            throw new IllegalArgumentException("Invalid argument: " + arg);
                        }
                    }
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return "";
    }


    protected String getFullCommand() {
        StringBuilder command;

        command = new StringBuilder(main_command);
        for (String arg : this.args) {
            command.append(" ").append(arg);
        }
        return command.toString();
    }


    protected String executeCommandForLinux(String command) {
        Runtime runtime = Runtime.getRuntime();
        String result = "";
        try {
            String currentPath = BaseCommand.currentPath.toString();
            Process process = runtime.exec(new String[]{"/bin/sh", "-c","cd '" + currentPath + "' && "+command});
            result = ProcessOutput(process);
        } catch (IOException e) {
            result = (e.getMessage());
        }

        return result;
    }

    protected String executeCommandForWindows(String command) {
        Runtime runtime = Runtime.getRuntime();
        String result = "";
        try {
            String fullCommand = "cd /d \"" + BaseCommand.currentPath.toString() + "\" && " + command;
            Process process = runtime.exec(new String[]{"cmd", "/c", fullCommand});
            result = ProcessOutput(process);
        } catch (IOException e) {
            result =(e.getMessage());
        }

        return result;
    }

    private String ProcessOutput(Process process) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            int exitCode = process.waitFor();
        } catch (InterruptedException e) {
            result.append("Process was interrupted: " + e.getMessage());
        }
        return result.toString();
    }

    protected String print(String data){
        data = data.trim();
        if(this.outputToFileIndex != -1) {
            String fileName = this.args.get(this.outputToFileIndex);
            try {
                FileWriter fileWriter = new FileWriter(fileName, this.outputToFileIndex-1 == this.args.indexOf(">>"));
                fileWriter.write(data);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }else{
            System.out.println(data);
        }
        return data;
    }
    protected String executeCommand(String command) {
        String os = System.getProperty("os.name").toLowerCase();
        String result = "";
        if (os.contains("win")) {
            result = executeCommandForWindows(command);
        } else {
            result = executeCommandForLinux(command);
        }
        return result;
    }


    static public List<String> ParseInputStringToList(String input) {
        List<String> args = new ArrayList<>();
        boolean inQuotes = false;

        StringBuilder currentArg = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == '\"') {
                inQuotes = !inQuotes;
            } else if (c == ' ' && !inQuotes) {
                if (!currentArg.isEmpty()) {
                    args.add(currentArg.toString());
                    currentArg.setLength(0);
                }
            } else {
                currentArg.append(c);
            }
        }
        if (!currentArg.isEmpty()) {
            args.add(currentArg.toString());
        }

        return args;
    }

    public final String execute(String input) {
        return execute(ParseInputStringToList(input));
    }
    public final String execute() {
        return execute(List.of());
    }
    public final String execute(List<String> args) {
        if(!args.isEmpty() && args.get(0).equals(main_command)){
            args = args.subList(1, args.size());
        }
        String valid = ValidateArgs(args);
        if (!valid.isEmpty()) {
            return valid;
        }
        if (this.args.contains("-h") || this.args.contains("--help") || this.args.contains("-help") || this.args.contains("help")) {
            return print(help());
        }
        String command = this.getFullCommand();
        String result = executeCommand(command);
        if(!result.isEmpty()){
            return print(result);
        }
        return "";
    }


}
