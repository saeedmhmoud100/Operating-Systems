import Commands.BaseCommand;
import Commands.mkdirCommand;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MkdirCommandTest {

    @Test
    public void createDirectoryTest() throws IOException {
        Path tempDir = Files.createTempDirectory("tempDir");
        tempDir.toFile().deleteOnExit();

        BaseCommand command = new mkdirCommand();
        BaseCommand.currentPath = tempDir;
        String newDirName = "newDir";
        Assert.assertEquals(command.execute(newDirName).trim(), "");

        Path newDir = tempDir.resolve(newDirName);
        Assert.assertTrue(Files.exists(newDir));
        Assert.assertTrue(Files.isDirectory(newDir));
    }

    @Test
    public void createExistingDirectoryTest() throws IOException {
        Path tempDir = Files.createTempDirectory("tempDir");
        tempDir.toFile().deleteOnExit();

        Path newDir = tempDir.resolve("newDir");
        Files.createDirectories(newDir);

        BaseCommand command = new mkdirCommand();
        BaseCommand.currentPath = tempDir;
        Assert.assertEquals(command.execute("newDir").trim(), "");

        Assert.assertTrue(Files.exists(newDir));
        Assert.assertTrue(Files.isDirectory(newDir));
    }
}