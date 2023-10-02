package ch.supsi.fsci.engine.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FileSystemTest {

    /*
     * toDo: quando sarà disponibile add rivedere il test
     */
    @Test
    public void testEquals(){
        FileSystemModel fileSystemModel = new FileSystemModel();
        FileSystemModel fileSystemModel1 = new FileSystemModel();

        assertEquals(fileSystemModel, new FileSystemModel());
    }
}
