import Commands.BaseCommand;
import Commands.CdCommand;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;

public class CdCommandTest {

    @Test
    public void changeToNonexistentDirectoryTest() {
        BaseCommand command = new CdCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));

        Path initialPath = BaseCommand.currentPath;

        Assert.assertEquals(command.execute("nonexistent").trim(), "Directory does not exist");
        Assert.assertEquals(BaseCommand.currentPath, initialPath);
    }

    @Test
    public void changeToValidDirectoryTest() {
        BaseCommand command = new CdCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));

        Path initialPath = BaseCommand.currentPath;
        Path parentPath = initialPath.getParent();

        Assert.assertEquals(command.execute("..").trim(), "");
        Assert.assertEquals(BaseCommand.currentPath, parentPath);
    }

    @Test
    public void invalidArgumentsTest() {
        BaseCommand command = new CdCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));

        Assert.assertEquals(command.execute(" ").trim(), "Invalid number of arguments Expected: 1 to 3\nGot: 0\nUse cd -h or --help for help");
    }
}