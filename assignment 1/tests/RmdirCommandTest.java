import Commands.BaseCommand;
import Commands.rmdirCommand;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RmdirCommandTest {

    @Test
    public void removeDirectoryTest() throws IOException {
        Path tempDir = Files.createTempDirectory("tempDir");
        tempDir.toFile().deleteOnExit();

        BaseCommand command = new rmdirCommand();
        BaseCommand.currentPath = tempDir.getParent().toAbsolutePath();
        Assert.assertEquals(command.execute(tempDir.getFileName().toString()).trim(), "");

        Assert.assertFalse(Files.exists(tempDir));
    }

    @Test
    public void removeNonexistentDirectoryTest() {
        BaseCommand command = new rmdirCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute("nonexistentDir").trim(), "Directory does not exist");
    }

    @Test
    public void removeInvalidArgumentTest() {
        BaseCommand command = new rmdirCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute(List.of("-l")).trim(), "Directory does not exist");
    }
}