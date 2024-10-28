package Commands;

import java.util.List;

public class clearCommand extends BaseCommand{
    public clearCommand() {
        super("clear", List.of());
        this.maxArgs = 0;

    }

    @Override
    protected void help() {
        System.out.println("clear: clear");
        System.out.println("\tClear the terminal screen.");
    }

    @Override
    protected void executeCommandForWindows(String command) {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//            protected void clearScreen() {
//                System.out.println("\n".repeat(50)); // Prints 50 new lines to simulate clearing
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
