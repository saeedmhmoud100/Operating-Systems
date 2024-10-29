import Commands.BaseCommand;
import Commands.DateCommand;
import Commands.ListCommand;
import Commands.PwdCommand;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MyTest {

    @Test
    public void lsTest() {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            BaseCommand command = new ListCommand();
            BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
            File[] files = Paths.get(System.getProperty("user.dir")).toFile().listFiles();
            StringBuilder res = new StringBuilder();
            if (files != null) {
                for (File file : files) {
                    res.append(file.getName() + "\n");
                }
            }
            Assert.assertEquals(command.execute().trim(), res.toString().trim());
            Assert.assertEquals(command.execute(List.of("-l")).trim(), res.toString().trim());
            Assert.assertEquals(command.execute(List.of("-a t l -h")).trim(), "Invalid argument: -a t l -h");
            Assert.assertEquals(command.execute(BaseCommand.ParseInputStringToList("-a -l -R -A -t")).trim(),
                    "Invalid number of arguments Expected: 0 to 3\n" +
                    "Got: 5\n" +
                    "Use ls -h or --help for help");
        }
    }

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