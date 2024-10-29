package Commands;

import java.util.List;

public class clearCommand extends BaseCommand{
    public clearCommand() {
        super("clear", List.of());
        this.maxArgs = 1;

    }

    @Override
    protected String help() {
        return ("clear: clear\n" +
                "    Clear the terminal screen.\n");
    }

    @Override
    protected String executeCommandForWindows(String command) {
        StringBuilder result = new StringBuilder();
        try {
            if(System.console() != null){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else{
                result.append("\n".repeat(50));
            }
        } catch (Exception e) {
            result.append(e.getMessage()+"\n");
        }
        return result.toString();
    }
}
