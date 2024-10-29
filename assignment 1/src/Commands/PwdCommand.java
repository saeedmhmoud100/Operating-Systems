package Commands;

import java.util.List;

public class PwdCommand extends BaseCommand{
    public PwdCommand() {
        super("pwd", List.of());
        this.maxArgs = 1;

    }

    @Override
    protected String help() {
        return ("pwd: pwd\n" +
                "    Show the current directory\n");
    }

    @Override
    protected String executeCommandForWindows(String command) {
        StringBuilder result = new StringBuilder();
        result.append(BaseCommand.currentPath.toString()).append("\n");
        return result.toString();
    }
}
