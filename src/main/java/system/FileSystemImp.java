package system;

import com.google.common.annotations.VisibleForTesting;
import enums.PathTypeEnum;
import models.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * File System Control Class
 */
public class FileSystemImp implements FileSystem {

    private final HashMap<Node, List<Node>> tree = new HashMap<>();
    private Node currentNode;

    public FileSystemImp() {
        this.currentNode = new Node("/", null, PathTypeEnum.DIRECTORY);
        this.tree.put(currentNode, new ArrayList<>());
    }

    @Override
    public void addFile(String fileName) {
        if(exists(fileName, PathTypeEnum.FILE)) {
            System.out.println("file already exists");
        } else {
            tree.get(currentNode).add(new Node(fileName, currentNode, PathTypeEnum.FILE));
        }
    }

    @Override
    public void addDir(String dirName) {
        if(exists(dirName, PathTypeEnum.DIRECTORY)) {
            System.out.println("directory already exists");
        } else {
            Node newDir = new Node(dirName, currentNode, PathTypeEnum.DIRECTORY);
            tree.get(currentNode).add(newDir);
            tree.put(newDir, new ArrayList<>());
        }
    }

    @Override
    public void changeDir(String dirName) {
        Optional<Node> node = tree.get(currentNode).stream().filter(u -> u.getName().equals(dirName) &&
                u.getType().equals(PathTypeEnum.DIRECTORY)).findFirst();

        if(node.isPresent()) {
            currentNode = node.get();
        } else {
            System.out.println("directory not found: " + dirName);
        }
    }

    @Override
    public void dirUp() {
        if(!Objects.isNull(currentNode.getParent())) {
            currentNode = currentNode.getParent();
        }
    }

    @Override
    public void pwd() {
        Node auxNode = currentNode;
        StringBuilder builder = new StringBuilder();

        while (auxNode.getParent() != null) {
            builder.append("/").append(auxNode.getName());
            auxNode = auxNode.getParent();
        }

        System.out.println(reverse(builder.toString().split("/")));
    }

    private boolean exists(String name, PathTypeEnum type) {
        return tree.get(currentNode).stream().anyMatch(u -> u.getName().equals(name) &&
                u.getType().equals(type));
    }

    private String reverse(String[] arr) {
        StringBuilder completePath = new StringBuilder();

        for (int j = arr.length - 1; j >= 0; j--) {
            completePath.append("/").append(arr[j]);
        }

        return completePath.toString();
    }

    public void listAll() {
        tree.get(currentNode).forEach(n -> System.out.print(n.getName() + " "));
    }

    public String getCurrentPath() {
        return currentNode.getName();
    }

    @VisibleForTesting
    protected List<Node> getNodeList() {
        return this.tree.get(currentNode);
    }

    @VisibleForTesting
    protected Node getNodeParent() {
        return currentNode.getParent();
    }
}
