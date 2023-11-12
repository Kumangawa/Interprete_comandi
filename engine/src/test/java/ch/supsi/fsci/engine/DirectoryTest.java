package ch.supsi.fsci.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DirectoryTest {

    private DirectoryModel rootDir;
    private DirectoryModel subDir1;
    private DirectoryModel subDir2;

    @BeforeEach
    public void setUp() {
        rootDir = new DirectoryModel("root");
        subDir1 = new DirectoryModel("subDir1");
        subDir2 = new DirectoryModel("subDir2");

        rootDir.add(subDir1);
        rootDir.add(subDir2);
    }

    @Test
    public void getNameTest() {
        assertEquals("root", rootDir.getName());
        assertEquals("subDir1", subDir1.getName());
    }

    @Test
    public void getDirTest() {
        List<DirectoryModel> subDirs = rootDir.getDir();
        assertEquals(2, subDirs.size());
        assertTrue(subDirs.contains(subDir1));
        assertTrue(subDirs.contains(subDir2));
    }

    @Test
    public void equalsTest() {
        DirectoryModel rootDirCopy = new DirectoryModel("root");
        rootDirCopy.add(new DirectoryModel("subDir1"));
        rootDirCopy.add(new DirectoryModel("subDir2"));

        assertEquals(rootDir, rootDir);
        assertEquals(rootDir, rootDirCopy);
        assertEquals(rootDirCopy, rootDir);
        assertEquals(rootDir.hashCode(), rootDirCopy.hashCode());
        assertNotEquals(rootDir, subDir1);
        assertNotEquals(rootDir, null);
    }
}
