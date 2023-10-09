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

    @Test
    public void testCd() {
        final String res = fileSystemModel.cd("\\A").getName();
        assertEquals("A", res);
    }

    @Test
    public void testConstructor() {
        final DirectoryModel root = fileSystemModel.getRoot();
        assertNotNull(root);
        // Todo: change '2' later, this is hardcoded (currently the constructor adds 2 subdirectories!)
        assertEquals(fileSystemModel.getRoot().getDir().size(), root.getDir().size());
    }

    @Test
    public void testSearch(){
        final String absolutePath = "\\B\\F";
        final String wrongAbsolutePath = "\\B\\X";
        final String relativePath = "B\\F";
        final DirectoryModel expectedF = new DirectoryModel("F");
        assertEquals(expectedF, fileSystemModel.search(absolutePath));
        assertEquals(expectedF, fileSystemModel.search(relativePath));
        assertThrows(DirectoryNotFound.class, () -> fileSystemModel.search(wrongAbsolutePath));
    }
}
