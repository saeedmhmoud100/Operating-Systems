package Commands;

import java.util.List;

public class PwdCommand extends BaseCommand{
    public PwdCommand() {
        super("pwd", List.of());
        this.maxArgs = 1;

    }

    @Override
    protected void help() {
        System.out.println("Usage: pwd");
        System.out.println("Show the current directory");
    }

    @Override
    protected String executeCommandForWindows(String command) {
        StringBuilder result = new StringBuilder();
        result.append(BaseCommand.currentPath.toString()).append("\n");
        return result.toString();
    }
}
