import Commands.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MyTest {
    @Test
    public void dateTest() {
        BaseCommand command = new DateCommand();
        Assert.assertEquals(command.execute().trim(), java.time.LocalDate.now().toString().trim());
        Assert.assertEquals(command.execute(List.of("-l")).trim(), "Invalid argument: -l");
    }
}