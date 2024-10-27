package Commands;

import java.util.List;

public class ListCommand implements CommandInterface{
    public ListCommand() {

    }
    public ListCommand(List args) {
        System.out.println("List command created with args: " + args);
    }
    public void execute() {
        System.out.println("List executed");
    }
}
