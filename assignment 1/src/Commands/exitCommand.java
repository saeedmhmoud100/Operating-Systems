package Commands;

import java.util.List;

public class exitCommand extends BaseCommand{
    public exitCommand() {
        super("exit", List.of());
        this.maxArgs = 0;

    }

    @Override
    protected void help() {
        System.out.println("Usage: exit");
        System.out.println("Exits the command line system.");
    }
}
