import Commands.BaseCommand;
import Commands.mvCommand;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MvCommandTest {

    @Test
    public void moveFileTest() throws IOException {
        File sourceFile = File.createTempFile("sourceFile", ".txt");
        sourceFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write("Hello, World!");
        }

        Path destinationDir = Files.createTempDirectory("destinationDir");
        destinationDir.toFile().deleteOnExit();

        BaseCommand command = new mvCommand();
        BaseCommand.currentPath = sourceFile.getParentFile().toPath();
        Assert.assertEquals(command.execute(List.of(sourceFile.getName(), destinationDir.toString())).trim(), "");

        Path movedFile = destinationDir.resolve(sourceFile.getName());
        Assert.assertTrue(Files.exists(movedFile));
        Assert.assertFalse(Files.exists(sourceFile.toPath()));
        Assert.assertEquals(Files.readString(movedFile).trim(), "Hello, World!");
    }

    @Test
    public void moveNonexistentFileTest() {
        BaseCommand command = new mvCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute(List.of("nonexistent.txt", "destinationDir")).trim(), "mv: cannot move 'nonexistent.txt': No such file or directory");
    }

    @Test
    public void moveToNonexistentDirectoryTest() throws IOException {
        // Setup: Create a temporary source file with some content
        File sourceFile = File.createTempFile("sourceFile", ".txt");
        sourceFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write("Hello, World!");
        }

        BaseCommand command = new mvCommand();
        BaseCommand.currentPath = sourceFile.getParentFile().toPath();
        Assert.assertEquals(command.execute(List.of(sourceFile.getName(), "nonexistentDir")).trim(), "mv: cannot move 'nonexistentDir': No such file or directory");
    }

    @Test
    public void moveInvalidArgumentTest() {
        BaseCommand command = new mvCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute(List.of("-l")).trim(), "mv: cannot move '-l': No such file or directory");
    }
}