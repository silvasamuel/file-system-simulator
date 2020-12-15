package system;

import enums.PathType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileSystemImpTest {

    private FileSystemImp fileSystem;

    @Before
    public void setup() {
        fileSystem = new FileSystemImp();
    }

    @Test
    public void shouldCreateFile() {
        fileSystem.addFile("migo.txt");
        Assert.assertEquals(1, fileSystem.getNodeList().size());
        Assert.assertEquals("migo.txt", fileSystem.getNodeList().get(0).getName());
        Assert.assertEquals(PathType.FILE, fileSystem.getNodeList().get(0).getType());
    }

    @Test
    public void shouldCreateDirectory() {
        fileSystem.addDir("migo");
        Assert.assertEquals(1, fileSystem.getNodeList().size());
        Assert.assertEquals("migo", fileSystem.getNodeList().get(0).getName());
        Assert.assertEquals(PathType.DIRECTORY, fileSystem.getNodeList().get(0).getType());
    }

    @Test
    public void shouldChangeDirectory() {
        fileSystem.addDir("migo");
        fileSystem.changeDir("migo");

        Assert.assertEquals("/", fileSystem.getNodeParent().getName());
    }

    @Test
    public void shouldGoIntoParentDirectory() {
        fileSystem.addDir("migo");
        fileSystem.changeDir("migo");
        fileSystem.dirUp();

        Assert.assertNull(fileSystem.getNodeParent());
    }
}