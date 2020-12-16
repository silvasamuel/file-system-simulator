import system.FileSystemImp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Main class to execute the File System Simulator
 */
public class Main {

    public static void main(String []args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        FileSystemImp fileSystem = new FileSystemImp();

        String option;

        do {
            System.out.print(fileSystem.getCurrentPath() + ": ");

            option = in.readLine();
            String[] optArr = option.split(" ");

            switch (optArr[0]) {
                case "AddFile":
                    String fileName = getFileOrDirectoryName(optArr);
                    if(Objects.nonNull(fileName)) {
                        fileSystem.addFile(fileName);
                    } else {
                        System.out.println("usage: AddFile fileName");
                    }

                    break;

                case "AddDir":
                    String dirName = getFileOrDirectoryName(optArr);
                    if(Objects.nonNull(dirName)) {
                        fileSystem.addDir(dirName);
                    } else {
                        System.out.println("usage: AddDir dirName");
                    }

                    break;

                case "ChangeDir":
                    String dirNameNavigation = getFileOrDirectoryName(optArr);
                    if(Objects.nonNull(dirNameNavigation)) {
                        fileSystem.changeDir(dirNameNavigation);
                    } else {
                        System.out.println("usage: ChangeDir dirName");
                    }

                    break;

                case "DirUp":
                    fileSystem.dirUp();
                    break;

                case "Pwd":
                    fileSystem.pwd();
                    break;

                case "Ls":
                    fileSystem.listAll();
                    System.out.println();
                    break;
                default:
                    if(!option.equals("exit")) {
                        System.out.println("Command not found: " + option);
                    }
            }

        } while(!option.equals("exit"));
    }

    private static String getFileOrDirectoryName(String[] fileNameArr) {
        StringBuilder name = new StringBuilder("");

        for(int i = 1; i < fileNameArr.length; i++) {
            if(i > 1) {
                name.append(" ");
            }
            name.append(fileNameArr[i]);
        }

        return name.toString().equals("") ? null : name.toString();
    }
}
