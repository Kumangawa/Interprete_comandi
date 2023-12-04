package ch.supsi.fsci.engine.Model;

import ch.supsi.fsci.engine.Data.Directory;
import ch.supsi.fsci.engine.Exceptions.DirectoryNotFound;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;
import ch.supsi.fsci.engine.Localization;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTest {
    private FileSystemInterface fileSystemModelA;
    private FileSystemInterface sameFileSystemModelA;
    private FileSystemInterface fileSystemModelB;

    @BeforeEach
    public void setUp() {
        Directory A = new Directory("A");
        Directory B = new Directory("B");
        Directory C = new Directory("C");

        A.add(new Directory("D"));
        B.add(new Directory("E"));
        B.add(new Directory("F"));
        C.add(new Directory("G"));

        fileSystemModelA = new FileSystemModel();
        sameFileSystemModelA = new FileSystemModel();
        fileSystemModelA.add(A);
        fileSystemModelA.add(B);
        fileSystemModelA.add(C);

        sameFileSystemModelA.add(A);
        sameFileSystemModelA.add(B);
        sameFileSystemModelA.add(C);

        fileSystemModelB = new FileSystemModel();
    }

    @AfterEach
    public void clear(){
        fileSystemModelA = null;
        sameFileSystemModelA = null;
        fileSystemModelB = null;
    }

    @Test
    public void testEqualsWithSameInstance() {
        assertTrue(fileSystemModelA.equals(fileSystemModelA));
    }

    @Test
    public void testEqualsWithEqualInstances() {
        assertTrue(fileSystemModelA.equals(sameFileSystemModelA));
    }

    @Test
    public void testEqualsWithDifferentInstances() {
        assertFalse(fileSystemModelA.equals(fileSystemModelB));
    }

    @Test
    public void testIterate(){
        Directory N = new Directory("N");
        fileSystemModelA.add(N);

        Directory root = fileSystemModelA.getRoot();
        Directory cur = fileSystemModelA.getCur();

        //test absolute path corretto:
        String absolutePath = "/N";
        List<String> orderedAbsolutePath = fileSystemModelA.getOrderedAbsolutePath(absolutePath);
        assertEquals(N, fileSystemModelA.iterate(root, orderedAbsolutePath));

        //test relative path corretto:
        String relativePath = "N";
        List<String> orderedRelativePath = fileSystemModelA.getOrderedRelativePath(relativePath);
        assertEquals(N, fileSystemModelA.iterate(cur, orderedRelativePath));

        //test dell'exception, absolute path sbagliato:
        String wrongAbsolutePath = "/W";
        List<String> orderedWrongPath = fileSystemModelA.getOrderedAbsolutePath(wrongAbsolutePath);
        Exception ex = assertThrows(DirectoryNotFound.class, () -> fileSystemModelA.iterate(root, orderedWrongPath));
        assertEquals(String.format(Localization.getSingleton().localize("DirectoryNotFound"), orderedWrongPath.get(orderedWrongPath.size()-1), orderedWrongPath) , ex.getMessage());

        //test dell'exception, relative path sbagliato:
        String wrongRelativePath = "Y";
        List<String> orderedWrongRelativePath = fileSystemModelA.getOrderedRelativePath(wrongRelativePath);
        ex = assertThrows(DirectoryNotFound.class, () -> fileSystemModelA.iterate(cur, orderedWrongRelativePath));
        assertEquals(String.format(Localization.getSingleton().localize("DirectoryNotFound"), orderedWrongRelativePath.get(orderedWrongRelativePath.size()-1), orderedWrongRelativePath) , ex.getMessage());
    }

    //testare ancora
    @Test
    public void testCd() {
        final String res = fileSystemModelA.cd(fileSystemModelA.getSeparator() + "A").getName();
        assertEquals("A", res);

        //testare il lancio dell'exception nel caso non esista la cartella di destinazione
    }

    @Test
    public void testPwd() {
        // Initially pwd should return the root
        assertEquals("Current working directory: " + fileSystemModelA.getSeparator(), fileSystemModelA.pwd());
        fileSystemModelA.cd("A");
        assertEquals("Current working directory: " + fileSystemModelA.getSeparator()+ "A", fileSystemModelA.pwd());
        fileSystemModelA.cd("D");
        assertEquals("Current working directory: " + fileSystemModelA.getSeparator() + "A" + fileSystemModelA.getSeparator() + "D", fileSystemModelA.pwd());
    }

    @Test
    public void testConstructor() {
        final Directory root = fileSystemModelA.getRoot();
        assertNotNull(root);
        assertEquals(3, root.getDir().size());
        assertEquals(root.getDir().size(), sameFileSystemModelA.getRoot().getDir().size());
    }

    @Test
    public void testMkdir(){
        final String nomeCartella = "H";
        fileSystemModelA.cd(fileSystemModelA.getSeparator() + "C");
        fileSystemModelA.mkdir(nomeCartella);
        assertEquals((new Directory(nomeCartella)), fileSystemModelA.cd(fileSystemModelA.getSeparator() + "C" + fileSystemModelA.getSeparator() + "H"));
    }

    @Test
    public void testLs(){
        FileSystemInterface fileSystemToTest = new FileSystemModel();
        fileSystemToTest.mkdir("A");
        fileSystemToTest.mkdir("B");
        fileSystemToTest.mkdir("C");
        assertEquals("A B C ", fileSystemToTest.ls());
    }

    @Test
    public void testSize(){
        assertEquals(3, fileSystemModelA.getRoot().getDir().size());
    }

    @Test
    public void testSearch(){
        final String absolutePath = fileSystemModelA.getSeparator() + "B" + fileSystemModelA.getSeparator() + "F";
        final String wrongAbsolutePath = fileSystemModelA.getSeparator() + "B" + fileSystemModelA.getSeparator() + "X";
        final String relativePath = "B" + fileSystemModelA.getSeparator() + "F";
        final Directory expectedF = new Directory("F");
        assertEquals(expectedF, fileSystemModelA.search(absolutePath));
        assertEquals(expectedF, fileSystemModelA.search(relativePath));
        assertThrows(DirectoryNotFound.class, () -> fileSystemModelA.search(wrongAbsolutePath));
    }

    @Test
    public void testRm(){
        FileSystemInterface fileSystemToTest = new FileSystemModel();
        fileSystemToTest.mkdir("G");
        fileSystemToTest.mkdir("H");
        fileSystemToTest.mkdir("I");
        fileSystemToTest.rm(fileSystemToTest.getSeparator() + "G");
        assertEquals("H I ", fileSystemToTest.ls());

        fileSystemToTest.cd(fileSystemToTest.getSeparator() + "H");
        fileSystemToTest.mkdir("T");
        fileSystemToTest.rm("T");
        assertEquals("", fileSystemToTest.ls());

        fileSystemToTest.rm(fileSystemToTest.getSeparator() + "H");
        assertEquals("", fileSystemToTest.ls());

        fileSystemToTest.cd(fileSystemToTest.getSeparator());
        fileSystemToTest.rm(fileSystemToTest.getSeparator() + "H");
        assertEquals("I ", fileSystemToTest.ls());

        fileSystemToTest.cd(fileSystemToTest.getSeparator() + "I");
        fileSystemToTest.mkdir("O");
        assertEquals(String.format(Localization.getSingleton().localize("command.rm.failed")), fileSystemToTest.rm(fileSystemToTest.getSeparator() + "I"));
        assertEquals("O ", fileSystemToTest.ls());

        fileSystemToTest.cd(fileSystemToTest.getSeparator() + "I" + fileSystemToTest.getSeparator() + "O");
        fileSystemToTest.rm(fileSystemToTest.getSeparator() + "I");
        fileSystemToTest.cd(fileSystemToTest.getSeparator() + "I");
        assertEquals("O ", fileSystemToTest.ls());
    }

    @Test
    public void testMv() {

        FileSystemInterface fileSystem = new FileSystemModel();
        Directory A = new Directory("A");
        Directory B = new Directory("B");
        Directory C = new Directory("C");
        B.add(new Directory("E"));
        B.add(new Directory("F"));
        C.add(new Directory("D"));
        fileSystem = new FileSystemModel();
        fileSystem.add(A);
        fileSystem.add(B);
        fileSystem.add(C);

        String originPath = "/A/D";
        String destinationPath = "/C";

        assertEquals(String.format(Localization.getSingleton().localize("command.mv.success"), originPath, destinationPath), fileSystemModelA.mv(originPath, destinationPath));

    }
}
