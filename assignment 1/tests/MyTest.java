import Commands.BaseCommand;
import Commands.PwdCommand;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;

public class MyTest {
    @Test
    public void pwdTest() {
        BaseCommand command = new PwdCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute().trim(), BaseCommand.currentPath.toString().trim());
    }
}