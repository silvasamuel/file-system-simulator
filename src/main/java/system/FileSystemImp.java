package system;

import com.google.common.annotations.VisibleForTesting;
import enums.PathType;
import models.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FileSystemImp implements FileSystem {

    private final HashMap<Node, List<Node>> tree = new HashMap<>();
    private Node currentNode;

    public FileSystemImp() {
        this.currentNode = new Node("/", null, PathType.DIRECTORY);
        this.tree.put(currentNode, new ArrayList<>());
    }

    @Override
    public void addFile(String fileName) {
        if(exists(fileName, PathType.FILE)) {
            System.out.println("file already exists");
        } else {
            tree.get(currentNode).add(new Node(fileName, currentNode, PathType.FILE));
        }
    }

    @Override
    public void addDir(String dirName) {
        if(exists(dirName, PathType.DIRECTORY)) {
            System.out.println("directory already exists");
        } else {
            Node newDir = new Node(dirName, currentNode, PathType.DIRECTORY);
            tree.get(currentNode).add(newDir);
            tree.put(newDir, new ArrayList<>());
        }
    }

    @Override
    public void changeDir(String dirName) {
        Optional<Node> node = tree.get(currentNode).stream().filter(u -> u.getName().equals(dirName) &&
                u.getType().equals(PathType.DIRECTORY)).findFirst();

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

        System.out.println(reverse(builder.toString().split("/"), PathType.DIRECTORY));
    }

    private boolean exists(String name, PathType type) {
        return tree.get(currentNode).stream().anyMatch(u -> u.getName().equals(name) &&
                u.getType().equals(type));
    }

    private String reverse(String[] arr, PathType type) {
        StringBuilder completePath = new StringBuilder();
        int endLoop = type.equals(PathType.DIRECTORY) ? 0 : 1;

        for (int j = arr.length - 1; j >= endLoop; j--) {
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
