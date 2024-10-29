import Commands.BaseCommand;
import Commands.PwdCommand;
import Commands.DateCommand;
import Commands.HelpCommand;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class OutputRedirectionTest {

    @Test
    public void redirectPwdOutputToFileTest() throws IOException {
        BaseCommand command = new PwdCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        String outputFileName = "pwdOutput.txt";
        File outputFile = BaseCommand.currentPath.resolve(outputFileName).toFile();

        if (outputFile.exists()) {
            outputFile.delete();
        }

        Assert.assertEquals(command.execute(List.of(">", outputFileName)).trim(), BaseCommand.currentPath.toString().trim());
        Assert.assertTrue(outputFile.exists());
        Assert.assertEquals(Files.readString(outputFile.toPath()).trim(), BaseCommand.currentPath.toString().trim());

        outputFile.delete();
    }

    @Test
    public void appendPwdOutputToFileTest() throws IOException {
        BaseCommand command = new PwdCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        String outputFileName = "pwdOutput.txt";
        File outputFile = BaseCommand.currentPath.resolve(outputFileName).toFile();

        Files.writeString(outputFile.toPath(), "Initial Content\n");

        Assert.assertEquals(command.execute(List.of(">>", outputFileName)).trim(), BaseCommand.currentPath.toString().trim());
        Assert.assertTrue(outputFile.exists());
        Assert.assertEquals(Files.readString(outputFile.toPath()).trim(), "Initial Content\n" + BaseCommand.currentPath.toString().trim());

        outputFile.delete();
    }

    @Test
    public void redirectDateOutputToFileTest() throws IOException {
        BaseCommand command = new DateCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        String outputFileName = "dateOutput.txt";
        File outputFile = BaseCommand.currentPath.resolve(outputFileName).toFile();

        if (outputFile.exists()) {
            outputFile.delete();
        }

        Assert.assertEquals(command.execute(List.of(">", outputFileName)).trim(), java.time.LocalDate.now().toString().trim());
        Assert.assertTrue(outputFile.exists());
        Assert.assertEquals(Files.readString(outputFile.toPath()).trim(), java.time.LocalDate.now().toString().trim());

        outputFile.delete();
    }

    @Test
    public void appendDateOutputToFileTest() throws IOException {
        BaseCommand command = new DateCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        String outputFileName = "dateOutput.txt";
        File outputFile = BaseCommand.currentPath.resolve(outputFileName).toFile();

        Files.writeString(outputFile.toPath(), "Initial Content\n");

        Assert.assertEquals(command.execute(List.of(">>", outputFileName)).trim(), java.time.LocalDate.now().toString().trim());
        Assert.assertTrue(outputFile.exists());
        Assert.assertEquals(Files.readString(outputFile.toPath()).trim(), "Initial Content\n" + java.time.LocalDate.now().toString().trim());

        outputFile.delete();
    }

    @Test
    public void redirectHelpOutputToFileTest() throws IOException {
        BaseCommand command = new HelpCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));
        String outputFileName = "helpOutput.txt";
        File outputFile = BaseCommand.currentPath.resolve(outputFileName).toFile();

        if (outputFile.exists()) {
            outputFile.delete();
        }

        Assert.assertEquals(command.execute(List.of(">", outputFileName)).trim(), command.execute().trim());
        Assert.assertTrue(outputFile.exists());
        Assert.assertEquals(Files.readString(outputFile.toPath()).trim(), command.execute().trim());

        outputFile.delete();
    }

    @Test
    public void appendHelpOutputToFileTest() throws IOException {
        BaseCommand command = new HelpCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));

        String outputFileName = "helpOutput.txt";
        File outputFile = BaseCommand.currentPath.resolve(outputFileName).toFile();

        Files.writeString(outputFile.toPath(), "Initial Content\n");


        Assert.assertEquals(Files.readString(outputFile.toPath()).trim(), "Initial Content\n".trim());

        Assert.assertEquals(command.execute(List.of(">", outputFileName)).trim(), command.execute().trim());

        Assert.assertTrue(outputFile.exists());
        Assert.assertEquals(command.execute(List.of(">>", outputFileName)).trim(), command.execute().trim());
        outputFile.delete();
    }

    @Test
    public void invalidArgumentsTest() {
        BaseCommand command = new PwdCommand();
        BaseCommand.currentPath = Path.of(System.getProperty("user.dir"));

        Assert.assertEquals(command.execute(List.of(">", "output.txt")).trim(), BaseCommand.currentPath.toString().trim());
    }
}