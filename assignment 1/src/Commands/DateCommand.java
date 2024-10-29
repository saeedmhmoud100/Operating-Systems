package Commands;

import java.util.List;

public class DateCommand extends BaseCommand{
    public DateCommand() {
        super("date", List.of());
        this.maxArgs = 1;
    }

    @Override
    public String executeCommand(String command) {
        StringBuilder result = new StringBuilder();
        result.append("Date: " + java.time.LocalDate.now()+"\n");
        return result.toString();
    }
    @Override
    protected String help() {
        return ("date: date\n" +
                "    Print the current date\n");
    }
}
