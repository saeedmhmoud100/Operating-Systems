import Commands.BaseCommand;
import Commands.rmCommand;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RmCommandTest {

    @Test
    public void removeFileTest() throws IOException {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("Hello, World!");
        }

        BaseCommand command = new rmCommand();
        BaseCommand.currentPath = tempFile.getParentFile().toPath();
        Assert.assertEquals(command.execute(tempFile.getName()).trim(), "");

        Assert.assertFalse(Files.exists(tempFile.toPath()));
    }

    @Test
    public void removeNonexistentFileTest() {
        BaseCommand command = new rmCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute("nonexistent.txt").trim(), "File does not exist");
    }

    @Test
    public void removeInvalidArgumentTest() {
        BaseCommand command = new rmCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute(List.of("-l")).trim(), "File does not exist");
    }
}