package models;

import enums.PathTypeEnum;

public class Node {

    final String name;
    final Node parent;
    final PathTypeEnum type;

    public Node(String name, Node parent, PathTypeEnum type) {
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

    public PathTypeEnum getType() {
        return type;
    }
}
