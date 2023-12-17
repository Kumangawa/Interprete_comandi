package ch.supsi.fsci.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest {

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.forLanguageTag("en"));
    }


    @Test
    void testLocalizeDirectoryNotFound() {
        Localization.getSingleton().initialize("i18n.translations", Locale.forLanguageTag("en"));
        Response response = new Response("DirectoryNotFound", "test", "path/to/directory");
        assertEquals("The directory test has not been found with the path/to/directory path", response.localize());
    }

    @Test
    void testLocalizeWrongCommandNameException() {
        Localization.getSingleton().initialize("i18n.translations", Locale.forLanguageTag("en"));
        Response response = new Response("WrongCommand.NameException", "unknownCommand");
        assertEquals("Command not found: unknownCommand", response.localize());
    }


    @Test
    void testLocalizeWrongCommandArgumentNumberException() {
        Localization.getSingleton().initialize("i18n.translations", Locale.forLanguageTag("en"));
        Response response = new Response("WrongCommand.ArgumentNumberException", "commandName", "2", "1", "commandName <arg>");
        assertEquals("Wrong number of arguments for command: commandName [Expected: 2, provided: 1]! Correct usage: commandName <arg>", response.localize());
    }

    @Test
    void testLocalizeCommandHelp() {
        Response response = new Response("command.help");
        assertEquals("ls (list directory content): ls \nmkdir (make directory): mkdir <dir name> \npwd (print working directory): pwd \ncd (current directory): cd \nmv (move): mv <origin> <destination> \nrm (remove): rm <path> \nclear: clears the previous outputs", response.localize());
    }

    @Test
    void testLocalizeCommandMkdir() {
        Localization.getSingleton().initialize("i18n.translations", Locale.forLanguageTag("en"));
        Response response = new Response("command.mkdir", "newDir");
        assertEquals("New directory created: newDir", response.localize());
    }

    @Test
    void testLocalizeCommandMkdirFailedSameName() {
        Response response = new Response("command.mkdir.failed.samename");
        assertEquals("Cannot create the directory because the destination directory already contains one with the same name", response.localize());
    }

    @Test
    void testLocalizeCommandRmRoot() {
        Response response = new Response("command.rm.root", "rootDir");
        assertEquals("rootDir cannot be eliminated", response.localize());
    }

    @Test
    void testLocalizeCommandRmFailed() {
        Localization.getSingleton().initialize("i18n.translations", Locale.forLanguageTag("en"));
        Response response = new Response("command.rm.failed");
        assertEquals("Cannot remove current working directory or its parent/subdirectory", response.localize());
    }

    @Test
    void testLocalizeCommandRmSuccess() {
        Localization.getSingleton().initialize("i18n.translations", Locale.forLanguageTag("en"));
        Response response = new Response("command.rm.success", "removedDir");
        assertEquals("Removed directory: removedDir", response.localize());
    }

    @Test
    void testLocalizeCommandMvRoot() {
        Response response = new Response("command.mv.root");
        assertEquals("Cannot move the source directory", response.localize());
    }

    @Test
    void testLocalizeCommandMvFailedCurrent() {
        Response response = new Response("command.mv.failed.current");
        assertEquals("Cannot move the current working directory or its parent directory", response.localize());
    }

    @Test
    void testLocalizeCommandMvFailedDescendant() {
        Response response = new Response("command.mv.failed.descendant");
        assertEquals("Cannot move the directory into one of its subdirectories", response.localize());
    }

    @Test
    void testLocalizeCommandMvFailedSameName() {
        Response response = new Response("command.mv.failed.samename");
        assertEquals("Cannot move the directory because the destination directory already contains one with the same name", response.localize());
    }

    @Test
    void testLocalizeCommandMvSuccess() {
        Localization.getSingleton().initialize("i18n.translations", Locale.forLanguageTag("en"));
        Response response = new Response("command.mv.success", "sourceDir", "destinationDir");
        assertEquals("Move successful: sourceDir moved to destinationDir", response.localize());
    }

    @Test
    void testLocalizeCommandLsSuccess() {
        Localization.getSingleton().initialize("i18n.translations", Locale.forLanguageTag("en"));
        StringBuilder sb = new StringBuilder("dir1 dir2");
        Response response = new Response("command.ls.success", sb.toString().trim());
        assertEquals("Content of current directory: dir1 dir2", response.localize());
    }
}
