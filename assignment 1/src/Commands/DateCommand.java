package Commands;

import java.util.List;

public class DateCommand extends BaseCommand{
    public DateCommand() {
        super("date", List.of());
        this.maxArgs = 0;
    }

    @Override
    public void execute(List<String> args) {
        System.out.println("Date: " + java.time.LocalDate.now());
    }
    @Override
    protected void help() {
        System.out.println("Prints the current date");
    }
}
