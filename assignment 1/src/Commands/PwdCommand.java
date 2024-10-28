package Commands;

import java.util.List;

public class PwdCommand extends BaseCommand{
    public PwdCommand() {
        super("pwd", List.of());
        this.maxArgs = 0;

    }

    @Override
    protected void help() {
        System.out.println("Usage: pwd");
        System.out.println("Show the current directory");
    }

    @Override
    protected void executeCommandForWindows(String command) {
        System.out.println(BaseCommand.currentPath.toString());
    }
}
