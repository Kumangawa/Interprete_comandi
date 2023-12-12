package ch.supsi.fsci.engine.Model;

import ch.supsi.fsci.engine.Data.Directory;
import ch.supsi.fsci.engine.Exceptions.DirectoryNotFound;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;
import ch.supsi.fsci.engine.Localization;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTest {
    private FileSystemInterface fileSystem;
    private Directory A;
    @BeforeEach
    public void setUp() {
        fileSystem = new FileSystemModel();
        A = new Directory("A");
        A.add(new Directory("D"));
        fileSystem.add(A);
    }
    @AfterEach
    public void clear(){
        fileSystem = null;
        A = null;
    }
    @Test
    public void testEqualsWithSameInstance() {
        assertTrue(fileSystem.equals(fileSystem));
    }
    @Test
    public void testEqualsWithEqualInstances() {
        FileSystemInterface sameFileSystemModelA = new FileSystemModel();
        sameFileSystemModelA.add(A);
        assertTrue(fileSystem.equals(sameFileSystemModelA));
    }
    @Test
    public void testEqualsWithDifferentInstances() {
        FileSystemInterface fileSystemModelB = new FileSystemModel();
        assertFalse(fileSystem.equals(fileSystemModelB));
    }
    @Test
    public void testConstructor() {
        final Directory root = fileSystem.getRoot();
        assertNotNull(root);
        assertEquals(1, root.getDir().size());
        assertEquals(root.getDir().size(), fileSystem.getRoot().getDir().size());
    }
    @Test
    public void testCd() {
        final String message = String.format(Localization.getSingleton().localize("command.cd.success"),  "A");
        final String res = fileSystem.cd(fileSystem.getSeparator() + "A").localize();
        assertEquals(message, res);
    }
    @Test
    public void testPwd() {
        String message = String.format(Localization.getSingleton().localize("command.pwd"), fileSystem.getSeparator());
        assertEquals(message, fileSystem.pwd().localize());
        fileSystem.cd("A");
        message += "A";
        assertEquals(message, fileSystem.pwd().localize());
        fileSystem.cd("D");
        message += fileSystem.getSeparator() + "D";
        assertEquals(message, fileSystem.pwd().localize());
    }
    @Test
    public void testMkdir(){
        final String nomeCartella = "H";
        fileSystem.cd(fileSystem.getSeparator() + "A");
        assertEquals(String.format(Localization.getSingleton().localize("command.mkdir"),nomeCartella), fileSystem.mkdir(nomeCartella).localize());

        fileSystem.cd(fileSystem.getSeparator() + "A" + fileSystem.getSeparator() + nomeCartella);
        final String destinazion = fileSystem.getSeparator()+"A"+fileSystem.getSeparator()+nomeCartella+fileSystem.getSeparator()+"B";
        assertEquals(String.format(Localization.getSingleton().localize("command.mkdir"),"B"), fileSystem.mkdir(destinazion).localize());
    }
    @Test
    public void testLs(){
        final String message = String.format(Localization.getSingleton().localize("command.ls.success"),  "A B C");
        fileSystem.mkdir("B");
        fileSystem.mkdir("C");
        assertEquals(message, fileSystem.ls().localize());
    }
    @Test
    public void testSize(){
        assertEquals(1, fileSystem.getRoot().getDir().size());
    }
    @Test
    public void testSearch(){
        A.add(new Directory("F"));
        final String absolutePath = fileSystem.getSeparator() + "A" + fileSystem.getSeparator() + "F";
        final String wrongAbsolutePath = fileSystem.getSeparator() + "A" + fileSystem.getSeparator() + "X";
        final String relativePath = "A" + fileSystem.getSeparator() + "F";
        final Directory expectedF = new Directory("F");
        assertEquals(expectedF, fileSystem.search(absolutePath));
        assertEquals(expectedF, fileSystem.search(relativePath));
        assertThrows(DirectoryNotFound.class, () -> fileSystem.search(wrongAbsolutePath));
    }
    @Test
    public void testRm(){
        assertEquals(String.format(Localization.getSingleton().localize("command.rm.root"), fileSystem.getSeparator()), fileSystem.rm(fileSystem.getSeparator()).localize());

        fileSystem.mkdir("G");
        assertEquals(String.format(Localization.getSingleton().localize("command.rm.success"), "G"), fileSystem.rm(fileSystem.getSeparator() + "G").localize());

        fileSystem.mkdir("H");
        fileSystem.cd(fileSystem.getSeparator() + "H");
        assertEquals(Localization.getSingleton().localize("command.rm.failed"), fileSystem.rm(fileSystem.getSeparator() + "H").localize());

        fileSystem.mkdir("T");
        fileSystem.cd(fileSystem.getSeparator() + "H"+fileSystem.getSeparator() + "T");
        assertEquals(Localization.getSingleton().localize("command.rm.failed"), fileSystem.rm(fileSystem.getSeparator() + "H").localize());

    }
    @Test
    public void testMv() {
        Directory B = new Directory("B");
        Directory C = new Directory("C");
        fileSystem.add(B);
        fileSystem.add(C);
        String originPath = fileSystem.getSeparator();
        String destinationPath = fileSystem.getSeparator()+"C";

        assertEquals(Localization.getSingleton().localize("command.mv.root"), fileSystem.mv(originPath, destinationPath).localize());

        originPath = "B";
        assertEquals(String.format(Localization.getSingleton().localize("command.mv.success"),"B",destinationPath ), fileSystem.mv(originPath, destinationPath).localize());

        fileSystem.cd(fileSystem.getSeparator()+"A");
        originPath = fileSystem.getSeparator()+"A";
        assertEquals(Localization.getSingleton().localize("command.mv.failed.current"), fileSystem.mv(originPath, destinationPath).localize());

        fileSystem.cd(fileSystem.getSeparator()+"A"+fileSystem.getSeparator()+"D");
        assertEquals(Localization.getSingleton().localize("command.mv.failed.descendant"), fileSystem.mv(originPath, destinationPath).localize());

        fileSystem.cd(fileSystem.getSeparator()+"A");
        originPath = fileSystem.getSeparator()+"A"+fileSystem.getSeparator()+"D";
        assertEquals(String.format(Localization.getSingleton().localize("command.mv.success"),originPath,destinationPath ), fileSystem.mv(originPath, destinationPath).localize());
    }
}
