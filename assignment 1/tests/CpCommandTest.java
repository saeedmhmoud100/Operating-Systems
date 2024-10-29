import Commands.BaseCommand;
import Commands.cpCommand;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CpCommandTest {

    @Test
    public void copyFileTest() throws IOException {
        File sourceFile = File.createTempFile("sourceFile", ".txt");
        sourceFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write("Hello, World!");
        }

        Path destinationDir = Files.createTempDirectory("destinationDir");
        destinationDir.toFile().deleteOnExit();

        BaseCommand command = new cpCommand();
        BaseCommand.currentPath = sourceFile.getParentFile().toPath();
        Assert.assertEquals(command.execute(List.of(sourceFile.getName(), destinationDir.toString())).trim(), "");

        Path copiedFile = destinationDir.resolve(sourceFile.getName());
        Assert.assertTrue(Files.exists(copiedFile));
        Assert.assertEquals(Files.readString(copiedFile).trim(), "Hello, World!");
    }

    @Test
    public void copyNonexistentFileTest() {
        BaseCommand command = new cpCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute(List.of("nonexistent.txt", "destinationDir")).trim(), "Error: Source does not exist");
    }

    @Test
    public void copyToNonexistentDirectoryTest() throws IOException {
        File sourceFile = File.createTempFile("sourceFile", ".txt");
        sourceFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write("Hello, World!");
        }

        BaseCommand command = new cpCommand();
        BaseCommand.currentPath = sourceFile.getParentFile().toPath();
        Assert.assertEquals(command.execute(List.of(sourceFile.getName(), "nonexistentDir")).trim(), "Error: Destination does not exist");
    }

    @Test
    public void copyInvalidArgumentTest() {
        BaseCommand command = new cpCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute(List.of("-l")).trim(), "Invalid number of arguments Expected: 2 to 4\n" +
                "Got: 1\n" +
                "Use mv -h or --help for help");
    }
}