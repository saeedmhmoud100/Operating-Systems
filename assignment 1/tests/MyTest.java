import Commands.BaseCommand;
import Commands.DateCommand;
import Commands.PwdCommand;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.util.List;

public class MyTest {
    @Test
    public void pwdTest() {
        BaseCommand command = new PwdCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute().trim(), BaseCommand.currentPath.toString().trim());
    }

    @Test
    public void dateTest() {
        BaseCommand command = new DateCommand();
        Assert.assertEquals(command.execute().trim(), java.time.LocalDate.now().toString().trim());
        Assert.assertEquals(command.execute(List.of("-l")).trim(), "Invalid argument: -l");
    }
}