package ch.supsi.fsci.engine.Data;

import ch.supsi.fsci.engine.Data.Directory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DirectoryTest {

    private Directory rootDir;
    private Directory subDir1;
    private Directory subDir2;

    @BeforeEach
    public void setUp() {
        rootDir = new Directory("root");
        subDir1 = new Directory("subDir1");
        subDir2 = new Directory("subDir2");

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
        List<Directory> subDirs = rootDir.getDir();
        assertEquals(2, subDirs.size());
        assertTrue(subDirs.contains(subDir1));
        assertTrue(subDirs.contains(subDir2));
    }

    @Test
    public void equalsTest() {
        Directory rootDirCopy = new Directory("root");
        rootDirCopy.add(new Directory("subDir1"));
        rootDirCopy.add(new Directory("subDir2"));

        assertEquals(rootDir, rootDir);
        assertEquals(rootDir, rootDirCopy);
        assertEquals(rootDirCopy, rootDir);
        assertEquals(rootDir.hashCode(), rootDirCopy.hashCode());
        assertNotEquals(rootDir, subDir1);
        assertNotEquals(rootDir, null);
    }
}
