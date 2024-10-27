package Commands;

import java.util.List;
import java.util.regex.Pattern;

public class CdCommand extends BaseCommand{
    private static final Pattern DIR_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_.-]+$");

    public CdCommand() {
        super("cd", List.of("../"));
    }

    @Override
    protected void help() {
        System.out.println("Usage: cd [directory]");
        System.out.println("Change the current directory to the specified directory.");
    }


    @Override
    protected boolean ValidateArgs(List<String> args) {
        boolean valid = false;
        try {
            valid = true;
            if (!args.isEmpty()) {
                if (this.allArgsAvailable.contains(args.get(0))) {
                    this.args.add(args.get(0));
                } else if (DIR_NAME_PATTERN.matcher(args.get(0)).matches()) {
                    this.args.add(args.get(0));
                } else {
                    throw new IllegalArgumentException("Invalid argument: " + args.get(0));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            valid = false;
        }
        return valid;
    }

    @Override
    protected void executeCommandForWindows(String Command) {
        String newDir = args.get(0);
        if (newDir.equals("../")) {
            String currentPath = System.getProperty("user.dir");
            String[] pathParts = currentPath.split("/");
            StringBuilder newPath = new StringBuilder();

            for (int i = 0; i < pathParts.length - 1; i++) {
                newPath.append(pathParts[i]);
                newPath.append("/");
            }
            System.out.println(newPath);
            System.setProperty("user.dir", newPath.toString());
        } else {
            System.setProperty("user.dir", newDir);
        }
    }
}
