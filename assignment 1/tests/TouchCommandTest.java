import Commands.BaseCommand;
import Commands.TouchCommand;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;

public class TouchCommandTest {

    @Test
    public void createNewFileTest() {
        BaseCommand command = new TouchCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        String newFileName = "testFile.txt";
        File newFile = BaseCommand.currentPath.resolve(newFileName).toFile();

        if (newFile.exists()) {
            newFile.delete();
        }

        Assert.assertEquals(command.execute(newFileName).trim(), "");
        Assert.assertTrue(newFile.exists());

        newFile.delete();
    }

    @Test
    public void invalidArgumentsTest() {
        BaseCommand command = new TouchCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));

        Assert.assertEquals(command.execute().trim(), "Invalid number of arguments Expected: 1 to 3\nGot: 0\nUse touch -h or --help for help");
    }
}