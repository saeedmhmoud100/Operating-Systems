import Commands.BaseCommand;
import Commands.PwdCommand;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.util.List;

public class PwdCommandTest {

    @Test
    public void showCurrentDirectoryTest() {
        BaseCommand command = new PwdCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute().trim(), BaseCommand.currentPath.toString().trim());
    }

    @Test
    public void invalidArgumentsTest() {
        BaseCommand command = new PwdCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute(List.of("-invalid")).trim(), "Invalid argument: -invalid");
    }
}