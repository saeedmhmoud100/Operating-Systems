import Commands.BaseCommand;
import Commands.ListCommand;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LsCommandTest {

    @Test
    public void listFilesTest() {
        BaseCommand command = new ListCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        File[] files = Paths.get(System.getProperty("user.dir")).toFile().listFiles();
        StringBuilder res = new StringBuilder();
        if (files != null) {
            for (File file : files) {
                res.append(file.getName()).append("\n");
            }
        }
        Assert.assertEquals(command.execute().trim(), res.toString().trim());
    }

    @Test
    public void listFilesInvalidArgumentTest() {
        BaseCommand command = new ListCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute(List.of("-invalid")).trim(), "Invalid argument: -invalid");
    }
}