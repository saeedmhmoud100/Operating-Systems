package Commands;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends BaseCommand {
    public ListCommand() {
        super("ls", List.of("-A", "-a", "-l", "-r", "-t", "-S", "-R", "-1", "-h"));
    }
}
