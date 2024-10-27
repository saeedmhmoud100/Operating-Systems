package Commands;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends BaseCommand {
    List<String> allArgsAvailable = List.of("-a", "-l", "-h");
    String main_command = "ls";
    List<String> args = new ArrayList<String>();
    boolean valid = true;
    public ListCommand() {
        super("ls", List.of("-a", "-l", "-h"));
    }
}
