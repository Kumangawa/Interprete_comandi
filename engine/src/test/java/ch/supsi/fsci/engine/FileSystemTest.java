package ch.supsi.fsci.engine;

import ch.supsi.fsci.engine.Exceptions.DirectoryNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTest {
    private FileSystemModel fileSystemModel;
    private FileSystemModel sameFileSystemModel;
    private FileSystemModel differentFileSystemModel;

    @BeforeEach
    public void setUp() {
        DirectoryModel A = new DirectoryModel("A");
        DirectoryModel B = new DirectoryModel("B");
        DirectoryModel C = new DirectoryModel("C");
        A.add(new DirectoryModel("D"));
        B.add(new DirectoryModel("E"));
        B.add(new DirectoryModel("F"));
        C.add(new DirectoryModel("G"));
        fileSystemModel = new FileSystemModel();
        sameFileSystemModel = new FileSystemModel();
        differentFileSystemModel = new FileSystemModel();
        fileSystemModel.add(A);
        fileSystemModel.add(B);
        fileSystemModel.add(C);

        sameFileSystemModel.add(A);
        sameFileSystemModel.add(B);
        sameFileSystemModel.add(C);
    }

    @Test
    public void testEqualsWithSameInstance() {
        assertTrue(fileSystemModel.equals(fileSystemModel));
    }

    @Test
    public void testEqualsWithEqualInstances() {
        assertTrue(fileSystemModel.equals(sameFileSystemModel));
    }

    @Test
    public void testEqualsWithDifferentInstances() {
        assertFalse(fileSystemModel.equals(differentFileSystemModel));
    }

    //testare ancora
    @Test
    public void testCd() {
        final String res = fileSystemModel.cd("\\"+ "A").getName();
        assertEquals("A", res);
    }

    @Test
    public void testPwd() {
        // Initially pwd should return the root
        assertEquals("Current working directory: " + "\\", fileSystemModel.pwd());
        fileSystemModel.cd("A");
        assertEquals("Current working directory: " + "\\"+ "A", fileSystemModel.pwd());
        fileSystemModel.cd("D");
        assertEquals("Current working directory: " + "\\" + "A" + "\\" + "D", fileSystemModel.pwd());
    }

    @Test
    public void testConstructor() {
        final DirectoryModel root = fileSystemModel.getRoot();
        assertNotNull(root);
        assertEquals(3, root.getDir().size());
        assertEquals(root.getDir().size(), sameFileSystemModel.getRoot().getDir().size());
    }

    @Test
    public void testMkdir(){
        final String nomeCartella = "H";
        fileSystemModel.cd("\\" + "C");
        fileSystemModel.mkdir(nomeCartella);
        assertEquals((new DirectoryModel(nomeCartella)), fileSystemModel.cd("\\" + "C" + "\\" + "H"));
    }

    @Test
    public void testLs(){
        FileSystemModel fileSystemToTest = new FileSystemModel();
        fileSystemToTest.mkdir("A");
        fileSystemToTest.mkdir("B");
        fileSystemToTest.mkdir("C");
        assertEquals("A B C ", fileSystemToTest.ls());
    }

    @Test
    public void testSize(){
        assertEquals(3, fileSystemModel.getRoot().getDir().size());
    }

    @Test
    public void testSearch(){
        final String absolutePath = "\\" + "B" + "\\" + "F";
        final String wrongAbsolutePath = "\\" + "B" + "\\" + "X";
        final String relativePath = "B" + "\\" + "F";
        final DirectoryModel expectedF = new DirectoryModel("F");
        assertEquals(expectedF, fileSystemModel.search(absolutePath));
        assertEquals(expectedF, fileSystemModel.search(relativePath));
        assertThrows(DirectoryNotFound.class, () -> fileSystemModel.search(wrongAbsolutePath));
    }

    @Test
    public void testRm(){FileSystemModel fileSystemToTest = new FileSystemModel();
        fileSystemToTest.mkdir("G");
        fileSystemToTest.mkdir("H");
        fileSystemToTest.mkdir("I");
        fileSystemToTest.rm("\\" + "G");
        assertEquals("H I ", fileSystemToTest.ls());

        fileSystemToTest.cd("\\" + "H");
        fileSystemToTest.mkdir("T");
        fileSystemToTest.rm("T");
        assertEquals("", fileSystemToTest.ls());

        fileSystemToTest.rm("\\" + "H");
        assertEquals("", fileSystemToTest.ls());

        fileSystemToTest.cd("\\");
        fileSystemToTest.rm("\\" + "H");
        assertEquals("I ", fileSystemToTest.ls());

        fileSystemToTest.cd("\\" + "I");
        fileSystemToTest.mkdir("O");
        assertEquals(String.format(Localization.getSingleton().localize("command.rm.remove.failed")), fileSystemToTest.rm("\\" + "I"));
        assertEquals("O ", fileSystemToTest.ls());

        fileSystemToTest.cd("\\" + "I" + "\\" + "O");
        fileSystemToTest.rm("\\" + "I");
        fileSystemToTest.cd("\\" + "I");
        assertEquals("O ", fileSystemToTest.ls());
    }

    @Test
    public void testMv() {

        // TODO: da completare
    }
}
