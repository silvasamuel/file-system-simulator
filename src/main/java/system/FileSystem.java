package system;

public interface FileSystem {
    void addFile(String fileName);
    void addDir(String dirName);
    void changeDir(String dirName);
    void dirUp();
    void pwd();
}
