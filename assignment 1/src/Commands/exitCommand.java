package Commands;

import java.util.List;

public class exitCommand extends BaseCommand{
    public exitCommand() {
        super("exit", List.of());
        this.maxArgs = 1;

    }

    @Override
    protected String help() {
        return ("exit: exit\n" +
                "    Exit the terminal\n");
    }
}
