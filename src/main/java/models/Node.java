package models;

import enums.PathType;

public class Node {

    final String name;
    final Node parent;
    final PathType type;

    public Node(String name, Node parent, PathType type) {
        this.name = name;
        this.parent = parent;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Node getParent() {
        return parent;
    }

    public PathType getType() {
        return type;
    }
}
