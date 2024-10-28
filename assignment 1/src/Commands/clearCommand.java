package Commands;

import java.util.List;

public class clearCommand extends BaseCommand{
    public clearCommand() {
        super("clear", List.of());
        this.maxArgs = 1;

    }

    @Override
    protected void help() {
        System.out.println("clear: clear");
        System.out.println("\tClear the terminal screen.");
    }

    @Override
    protected void executeCommandForWindows(String command) {
        try {
            if(System.console() != null){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else{
                    System.out.println("\n".repeat(50));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
