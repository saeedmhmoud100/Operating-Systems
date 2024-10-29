import Commands.BaseCommand;
import Commands.catCommand;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CatCommandTest {

    @Test
    public void catFileTest() throws IOException {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("Hello, World!\nThis is a test file.");
        }

        BaseCommand command = new catCommand();
        BaseCommand.currentPath = tempFile.getParentFile().toPath();
        Assert.assertEquals(command.execute(tempFile.getName()).trim(), "Hello, World!\nThis is a test file.".trim());
    }

    @Test
    public void catNonexistentFileTest() {
        BaseCommand command = new catCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute("nonexistent.txt").trim(), "cat: nonexistent.txt: No such file or directory".trim());
    }

    @Test
    public void catDirectoryTest() {
        BaseCommand command = new catCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute(".").trim(), "cat: .: Is a directory".trim());
    }

    @Test
    public void catInvalidArgumentTest() {
        BaseCommand command = new catCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        Assert.assertEquals(command.execute(List.of("-l")).trim(), "cat: -l: No such file or directory".trim());
    }
}