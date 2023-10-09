package ch.supsi.fsci.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTest {
    private FileSystemModel fileSystemModel;
    private FileSystemModel sameFileSystemModel;
    private FileSystemModel differentFileSystemModel;

    @BeforeEach
    public void setUp() {
        fileSystemModel = new FileSystemModel();
        sameFileSystemModel = new FileSystemModel();
        differentFileSystemModel = new FileSystemModel();
        differentFileSystemModel.add("/E");
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
        final String res = fileSystemModel.cd("\\A");
        assertEquals("A", res);
    }

    @Test
    public void testConstructor() {
        final DirectoryModel root = fileSystemModel.getRoot();
        assertNotNull(root);
        // Todo: change '2' later, this is hardcoded (currently the constructor adds 2 subdirectories!)
        assertEquals(2, root.getDir().size());
    }
}
